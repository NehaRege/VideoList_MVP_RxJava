//package android.example.com.boguscode.dagger.component;
//
//import android.content.Context;
//import android.example.com.boguscode.api.ApiNetworkService;
//import android.example.com.boguscode.dagger.ApiNetworkApplication;
//import android.example.com.boguscode.dagger.context.ApplicationContext;
//import android.example.com.boguscode.dagger.scope.ApplicationScope;
//import android.example.com.boguscode.dagger.module.ContextModule;
//import android.example.com.boguscode.dagger.module.RetrofitModule;
//
//import dagger.Component;
//
//@ApplicationScope
//@Component(modules = {ContextModule.class, RetrofitModule.class})
//public interface ApplicationComponent {
//
//    ApiNetworkService getApiService();
//
//    @ApplicationContext
//    Context getContext();
//
//    void injectApplication(ApiNetworkApplication application);
//
//}
