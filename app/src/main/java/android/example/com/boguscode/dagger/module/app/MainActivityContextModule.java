//package android.example.com.boguscode.dagger.module.app;
//
//import android.content.Context;
//import android.example.com.boguscode.dagger.context.ActivityContext;
//import android.example.com.boguscode.dagger.scope.ActivityScope;
//import android.example.com.boguscode.view.MainActivity;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class MainActivityContextModule {
//    private MainActivity mainActivity;
//    private Context context;
//
//    public MainActivityContextModule(MainActivity mainActivity) {
//        this.mainActivity = mainActivity;
//        this.context = mainActivity;
//    }
//
//    @Provides
//    @ActivityScope
//    public MainActivity providesMainActivity() {
//        return mainActivity;
//    }
//
//    @Provides
//    @ActivityScope
//    @ActivityContext
//    public Context providesContext() {
//        return context;
//    }
//}
