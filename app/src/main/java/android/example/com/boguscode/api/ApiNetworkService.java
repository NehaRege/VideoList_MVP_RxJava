package android.example.com.boguscode.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiNetworkService {
    @GET("/channels/staffpicks/videos")
    Observable<ResponseBody> getVideoList(@Header("Authorization") String auth);
}
