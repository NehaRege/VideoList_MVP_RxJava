package android.example.com.boguscode.data;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface DataManager {
    Observable<ResponseBody> getVideoList();
}
