package android.example.com.boguscode.data.dataManager;

import android.content.SharedPreferences;
import android.example.com.boguscode.data.dataSource.local.LocalDataSource;
import android.example.com.boguscode.data.dataSource.local.LocalDataSourceImpl;
import android.example.com.boguscode.data.dataSource.remote.RemoteDataSource;
import android.example.com.boguscode.data.dataSource.remote.RemoteDataSourceImpl;
import android.example.com.boguscode.utils.Constants;
import android.example.com.boguscode.api.ApiNetworkService;
import android.example.com.boguscode.api.NetworkClient;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DataManagerImpl implements DataManager {
    private static String TAG = "DataManagerImpl";

    private ArrayList<JSONObject> mVideosFromRetrofit = new ArrayList<>();

    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;

    private NetworkInfo networkInfo;

    private ResponseBody videoListData;


    public DataManagerImpl(NetworkInfo networkInfo, SharedPreferences sharedPreferences) {
        this.networkInfo = networkInfo;
        remoteDataSource = new RemoteDataSourceImpl();
        localDataSource = new LocalDataSourceImpl(sharedPreferences);
    }

    @Override
    public Observable<ResponseBody> getVideoList() {
        Log.d(TAG, "getVideoList: ");
        return getObservable();
//        getObservable().subscribeWith(getObserver());
    }

    @Override
    public Observable<ResponseBody> getVideoList_FromDataSource() {
        if (isNetworkAvailable()) {
            return remoteDataSource.getVideoListFromRemoteSource();
        } else {
            videoListData = localDataSource.getVideoListFromLocalSource();

            Log.d(TAG, "getVideoList_FromDataSource: video data list from local source ---------------> "+videoListData);
            return getObservable_localSource()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    @Override
    public void saveVideoListData(ResponseBody videoListData) {
        Log.d(TAG, "saveVideoListData: ");
        localDataSource.saveVideoListToSharedPrefs(videoListData);
    }

    private Observable<ResponseBody> getObservable_localSource() {
        return Observable.just(videoListData);
    }

    public Observable<ResponseBody> getObservable() {
        return NetworkClient.getRetrofit()
                .create(ApiNetworkService.class)
                .getVideoList(Constants.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Boolean isNetworkAvailable() {
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /**
     * This is without RxJava
     * <p>
     * Call --> getObservable().subscribeWith(getObserver()); from getVideoList() method
     *
     * @return
     */
    public DisposableObserver<ResponseBody> getObserver() {
        return new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    JSONObject object = new JSONObject(result);
                    JSONArray data = object.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        mVideosFromRetrofit.add(data.getJSONObject(i));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }
}

