package android.example.com.boguscode.data;

import android.example.com.boguscode.Constants;
import android.example.com.boguscode.api.ApiNetworkService;
import android.example.com.boguscode.api.NetworkClient;
import android.example.com.boguscode.presenter.MainPresenter;
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

    private MainPresenter mPresenter;

    private ArrayList<JSONObject> mVideosFromRetrofit = new ArrayList<>();

    public DataManagerImpl(MainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Observable<ResponseBody> getVideoList() {
        Log.d(TAG, "getVideoList: ");
        return getObservable();
//        getObservable().subscribeWith(getObserver());
    }

    public Observable<ResponseBody> getObservable() {
        return NetworkClient.getRetrofit()
                .create(ApiNetworkService.class)
                .getVideoList(Constants.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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

