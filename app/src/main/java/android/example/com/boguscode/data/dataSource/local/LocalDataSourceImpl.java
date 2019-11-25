package android.example.com.boguscode.data.dataSource.local;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public class LocalDataSourceImpl implements LocalDataSource {
    private static String TAG = "LocalDataSourceImpl";
    private static String LOCAL_DATA_KEY = "LocalDataSourceImpl";

    private SharedPreferences mSharedPreferences;

    public LocalDataSourceImpl(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    @Override
    public void saveVideoListToSharedPrefs(ResponseBody videoListData) {
        Log.d(TAG, "saveVideoListToSharedPrefs: ");
        Type type = new TypeToken<ResponseBody>() {
        }.getType();

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(videoListData); // myObject - instance of MyObject
        editor.putString(LOCAL_DATA_KEY, json);
        editor.apply();

//        editor.putString(LOCAL_DATA_KEY, new Gson().toJson(videoListData, type));
//        editor.apply();
    }

    @Override
    public ResponseBody getVideoListFromLocalSource() {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(LOCAL_DATA_KEY, "");
        return gson.fromJson(json, ResponseBody.class);

//        Type type = new TypeToken<ResponseBody>() {
//        }.getType();

//        return new Gson().fromJson(json, type);
    }
}
