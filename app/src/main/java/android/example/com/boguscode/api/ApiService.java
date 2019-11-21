package android.example.com.boguscode.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 *
 *  This is without RxJava
 */

public interface ApiService {
    @GET("/channels/staffpicks/videos")
    Call<ResponseBody> getVideoList(@Header("Authorization") String auth);
}
