package android.example.com.boguscode.data.dataSource.remote;

import android.example.com.boguscode.api.ApiNetworkService;
import android.example.com.boguscode.api.NetworkClient;
import android.example.com.boguscode.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RemoteDataSourceImpl implements RemoteDataSource {
    private static String TAG = "RemoteDataSourceImpl";

    @Override
    public Observable<ResponseBody> getVideoListFromRemoteSource() {
        return getObservable();
    }

    public Observable<ResponseBody> getObservable() {
        return NetworkClient.getRetrofit()
                .create(ApiNetworkService.class)
                .getVideoList(Constants.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
