package android.example.com.boguscode.api;

import android.example.com.boguscode.Constants;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class NetworkClient {
    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            Timber.plant(new Timber.DebugTree());

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NotNull String message) {
                    Timber.i(message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.VIMEO_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            OkHttpClient okHttpClient = builder.build();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.VIMEO_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(okHttpClient)
//                    .build();

        }

        return retrofit;
    }
}
