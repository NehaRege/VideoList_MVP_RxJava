//package android.example.com.boguscode.dagger;
//
//import android.app.Activity;
//import android.app.Application;
//import android.example.com.boguscode.Constants;
//import android.example.com.boguscode.api.ApiNetworkService;
//
//import org.jetbrains.annotations.NotNull;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import timber.log.Timber;
//
//public class ApiNetworkApplication extends Application {
//
//    public static ApiNetworkApplication get(Activity activity) {
//        return (ApiNetworkApplication) activity.getApplication();
//    }
//
//    private ApiNetworkService apiNetworkService;
//
//    /**
//     *  Activity
//     *
//     *
//     *
//     *
//     *
//     */
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        Timber.plant(new Timber.DebugTree());
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(@NotNull String message) {
//                Timber.i(message);
//            }
//        });
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.VIMEO_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(okHttpClient)
//                .build();
//
//        apiNetworkService = retrofit.create(ApiNetworkService.class);
//    }
//
//    public ApiNetworkService getApiNetworkService() {
//        return apiNetworkService;
//    }
//}
