//package android.example.com.boguscode.dagger.module.app;
//
//import android.example.com.boguscode.dagger.ApiNetworkApplication;
//import android.example.com.boguscode.dagger.scope.ActivityScope;
//import android.example.com.boguscode.data.DataManagerImpl;
//import android.example.com.boguscode.presenter.MainPresenter;
//
//import dagger.Module;
//import dagger.Provides;
//import retrofit2.Retrofit;
//
//@Module
//public class DataManagerModule {
//
//    ApiNetworkApplication mApiNetworkApplication;
//
//    public DataManagerModule(ApiNetworkApplication mApiNetworkApplication) {
//        this.mApiNetworkApplication = mApiNetworkApplication;
//    }
//
//    @Provides
//    @ActivityScope
//    public DataManagerImpl provideDataManager(MainPresenter mainPresenter, Retrofit retrofit) {
//        return new DataManagerImpl(mainPresenter, retrofit);
//    }
//
//}
