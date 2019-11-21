package android.example.com.boguscode.view;

import org.json.JSONObject;

import java.util.ArrayList;

public interface MainView {

    void setData(ArrayList<JSONObject> dataList);

    void showToast(String message);

    void showProgressBar();

    void hideProgressBar();

    void showErrorViewCard();

    void hideErrorViewCard();

    void showVideoListView();

    void hideVideoListView();

    String getRecyclerItemClickMessage(int position);

}
