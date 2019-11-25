//package android.example.com.boguscode.dagger.module.app;
//
//import android.example.com.boguscode.dagger.scope.ActivityScope;
//import android.example.com.boguscode.data.dataManager.DataManagerImpl;
//import android.example.com.boguscode.presenter.MainPresenterImpl;
//import android.example.com.boguscode.view.MainView;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class MainPresenterModule {
//    MainView mainView;
//
//    public MainPresenterModule(MainView mainView) {
//        this.mainView = mainView;
//    }
//
//    @Provides
//    @ActivityScope
//    public MainView provideMainView() {
//        return mainView;
//    }
//
//    @Provides
//    @ActivityScope
//    public MainPresenterImpl providePresenter(MainView mainView, DataManagerImpl dataManager) {
//        return new MainPresenterImpl(mainView, dataManager);
//    }
//}
