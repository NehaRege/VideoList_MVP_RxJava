package android.example.com.boguscode.data.dataSource.local;

import okhttp3.ResponseBody;

public interface LocalDataSource {

    void saveVideoListToSharedPrefs(ResponseBody videoListData);

    ResponseBody getVideoListFromLocalSource();
}
