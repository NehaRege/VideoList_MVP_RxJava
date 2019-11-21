package android.example.com.boguscode.presenter;

import android.example.com.boguscode.data.DataManager;
import android.example.com.boguscode.data.DataManagerImpl;
import android.example.com.boguscode.view.MainView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import okhttp3.ResponseBody;

public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = "MainPresenterImpl";

    private MainView mMainView;
    private DataManager mDataManager;

    private Observable<ResponseBody> observable;
    private ArrayList<JSONObject> mVideosFromRetrofit = new ArrayList<>();
    private final PublishSubject<Object> retrySubject = PublishSubject.create();

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mDataManager = new DataManagerImpl(this);
    }

    /**
     *
     * This is for Dagger
     *
     * @param mainView
     * @param dataManager
     */
    public MainPresenterImpl(MainView mainView, DataManager dataManager) {
        mMainView = mainView;
        mDataManager = dataManager;
    }

    @Override
    public void getVideoList() {
        observable = mDataManager.getVideoList();

//        .retryWhen(observable -> observable.zipWith(retrySubject, (o, o2) -> o))
//        .retryWhen(retryHandler ->
//                retryHandler.flatMap(nothing -> retrySubject.asObservable()))
//                .subscribe()

//        observable.retryWhen(observable -> observable.zipWith(retrySubject, (o, o2) -> o))
//        observable.retryWhen(observable -> observable.zipWith(retrySubject, (o, o2) -> o))
        observable.subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Log.d(TAG, "onNext: response body = " + responseBody);
                try {
                    String result = responseBody.string();
                    JSONObject object = new JSONObject(result);
                    JSONArray data = object.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        mVideosFromRetrofit.add(data.getJSONObject(i));
                    }
                    Log.d(TAG, "onNext: response body list size = " + mVideosFromRetrofit.size());

                    showListData();

                } catch (Exception e) {
                    Log.d(TAG, "onNext: Exception ");

                    showErrorView();
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                showErrorView();
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    @Override
    public void onListItemClick(int position) {
        mMainView.showToast(mMainView.getRecyclerItemClickMessage(position));
    }

    @Override
    public void onErrorViewCardClick() {
        mMainView.hideErrorViewCard();
        mMainView.showProgressBar();

        getVideoList();
        retrySubject.onNext(new Object());

//        retrySubject.onNext(true);
//        getVideoList();
    }

    private void showListData() {
        mMainView.hideErrorViewCard();
        mMainView.hideProgressBar();
        mMainView.showVideoListView();
        mMainView.setData(mVideosFromRetrofit);
    }

    private void showErrorView() {
        mMainView.hideProgressBar();
        mMainView.hideVideoListView();
        mMainView.showErrorViewCard();
    }


}
