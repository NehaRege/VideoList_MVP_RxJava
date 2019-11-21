package android.example.com.boguscode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<JSONObject> mVideoList;
    private static OnRecyclerViewItemClickListener onItemClickListener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView videoThumbNail;
        TextView videoName;
        TextView artistName;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoThumbNail = (ImageView) itemView.findViewById(R.id.videoThumbnail);
            videoName = (TextView) itemView.findViewById(R.id.videoNameTextView);
            artistName = (TextView) itemView.findViewById(R.id.artistTextView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickListener.onItemClick(getLayoutPosition());
//                }
//            });
        }

        public void click(final OnRecyclerViewItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public VideoAdapter(List<JSONObject> videoList, OnRecyclerViewItemClickListener listener) {
        onItemClickListener = listener;
        mVideoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItemLayout = inflater.inflate(R.layout.list_item_video, parent, false);
        return new VideoViewHolder(listItemLayout);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        JSONObject video = mVideoList.get(position);

        String uri = null;
        try {
            uri = video.optJSONObject("pictures").optJSONArray("sizes")
                    .getJSONObject(video.optJSONObject("pictures").optJSONArray("sizes").length() - 1)
                    .optString("link", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (uri != null) {
            new VideoAdapter.DownloadImageTask(holder.videoThumbNail).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
        }
        holder.videoName.setText(video.optString("name", ""));
        holder.artistName.setText(video.optJSONObject("user").optString("name", ""));
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                bmImage.setImageBitmap(result);
            }
        }
    }
}


