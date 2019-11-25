package android.example.com.boguscode.data.dataSource.remote;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface RemoteDataSource {
    Observable<ResponseBody> getVideoListFromRemoteSource();
}
