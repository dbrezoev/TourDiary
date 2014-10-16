package tourdiary.theroadrunner.com.tourdiary.activities.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.model.system.DownloadedFile;
import com.telerik.everlive.sdk.core.result.RequestResult;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 16.10.2014 г..
 */
public class MyAdapter extends BaseAdapter {

    public EverliveApp app = new EverliveApp("uDwdWIo61CYYVcha");
    private ImageView iv;
    List<String> images;
    private Context context;

    public MyAdapter(Context applicationContext, List<String> imgUrls) {
        context = applicationContext;
        this.images = imgUrls;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                    LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = inflater.inflate(R.layout.single_row, parent, false);

            TextView title = (TextView)row.findViewById(R.id.textView);
            TextView description = (TextView)row.findViewById(R.id.textView2);
            //ImageView image = (ImageView)row.findViewById(R.id.imageView);
//
//            Place temporaryPlace = places.get(position);
//
//            title.setText(temporaryPlace.getName());
//            description.setText(temporaryPlace.getDescription());
//            image.setImageURI(Uri.parse(temporaryPlace.getUri()));
//
//            return row;
        if (convertView != null) {
            iv = (ImageView) convertView;
        } else {
            iv = (ImageView)row.findViewById(R.id.imageView);//new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(10, -20, 10, -20);
        }

        new PictureAsyncDownloader(iv).execute(images.get(position));

        // iv.setImageResource(images[position]);
        return row;
    }

    public class PictureAsyncDownloader extends
            AsyncTask<String, Integer, Bitmap> {

        private final WeakReference imageViewReference;

        public PictureAsyncDownloader(ImageView imageView) {
            imageViewReference = new WeakReference(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
//            UUID uid = UUID.fromString("a8b3a770-456f-11e4-b1b6-87506291021f");
            UUID uid = UUID.fromString(params[0]);

            Bitmap tempImage;
            RequestResult result = app.workWith().files().download(uid)
                    .executeSync();

//            if (result.getSuccess()) {

            DownloadedFile file = (DownloadedFile) result.getValue();
            tempImage = BitmapFactory.decodeStream(file.getInputStream());
//            }
//            else{
//                tempImage = BitmapFactory.decodeResource(getResources(),R.drawable.error);
//            }

            return tempImage;
        }

        @Override
        protected void onPostExecute(Bitmap img) {
            // progressDialog.dismiss();
            if (isCancelled()) {
                img = null;
            }
            if (imageViewReference != null) {
                ImageView imageView = (ImageView) imageViewReference.get();
                if (imageView != null) {
                    if (img != null) {
                        imageView.setImageBitmap(img);
                    } else {
                        imageView.setImageDrawable(imageView.getContext()
                                .getResources()
                                .getDrawable(R.drawable.ic_launcher));
                    }
                }
            }
        }

    }
}
