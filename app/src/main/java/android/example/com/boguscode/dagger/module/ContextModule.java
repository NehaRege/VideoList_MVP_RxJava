//package android.example.com.boguscode.dagger.module;
//
//import android.content.Context;
//import android.example.com.boguscode.dagger.context.ApplicationContext;
//import android.example.com.boguscode.dagger.scope.ApplicationScope;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class ContextModule {
//    private Context context;
//
//    public ContextModule(Context context) {
//        this.context = context;
//    }
//
//    @Provides
//    @ApplicationScope
//    @ApplicationContext
//    public Context providesContext() {
//        return context;
//    }
//}
