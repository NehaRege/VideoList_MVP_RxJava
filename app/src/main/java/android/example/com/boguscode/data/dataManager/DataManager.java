package android.example.com.boguscode.data.dataManager;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface DataManager {
    Observable<ResponseBody> getVideoList();

    Observable<ResponseBody> getVideoList_FromDataSource();

    void saveVideoListData(ResponseBody videoListData);
}
