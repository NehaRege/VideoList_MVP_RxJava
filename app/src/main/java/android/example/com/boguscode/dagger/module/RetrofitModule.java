//package android.example.com.boguscode.dagger.module;
//
//import android.example.com.boguscode.utils.Constants;
//import android.example.com.boguscode.api.ApiNetworkService;
//import android.example.com.boguscode.dagger.scope.ApplicationScope;
//
//import org.jetbrains.annotations.NotNull;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import timber.log.Timber;
//
//@Module
//public class RetrofitModule {
//
//    @Provides
//    @ApplicationScope
//    public Retrofit getRetrofit(OkHttpClient okHttpClient, String baseUrl) {
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(okHttpClient)
//                .build();
//    }
//
//    @Provides
//    @ApplicationScope
//    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
//        return new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//    }
//
//    @Provides
//    @ApplicationScope
//    public String getBaseUrl() {
//        return Constants.VIMEO_BASE_URL;
//    }
//
//    @Provides
//    @ApplicationScope
//    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(@NotNull String message) {
//                Timber.i(message);
//            }
//        });
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//
//        return interceptor;
//    }
//
//    @Provides
//    @ApplicationScope
//    public ApiNetworkService provideApiNetworkService(Retrofit retrofit) {
//        return retrofit.create(ApiNetworkService.class);
//    }
//
//}
