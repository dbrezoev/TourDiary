package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.facades.special.DownloadFileAsStreamFacade;
import com.telerik.everlive.sdk.core.model.system.File;
import com.telerik.everlive.sdk.core.result.RequestResult;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;

import models.Place;
import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.MyAdapter;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class DiaryListActivity extends Activity implements View.OnClickListener{

//    public interface CallBack {
//        void onResult(ArrayList<Place> data);
//    }

    ListView listView;
    EverliveApp app;

    RequestResult<ArrayList<Place>> requestResult;
    private ArrayList<Place> allPlaces;

    @Override
    public void onClick(View v) {
    }

    public String getDownloadLink(UUID id){
        DownloadFileAsStreamFacade file = app.workWith().files().download(id);

        String downloadPath = file.getDownloadPath();

        return downloadPath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        app = new EverliveApp("uDwdWIo61CYYVcha");
        listView = (ListView)this.findViewById(R.id.listViewDiary);

        new Thread(new Runnable() {

            @Override
            public void run() {
                requestResult = app.workWith().data(Place.class)
                        .getAll().executeSync();

                if (requestResult.getSuccess()){
                    allPlaces = requestResult.getValue();

                    for(int i = 0; i < allPlaces.size(); i++){
                        String currentUri = getDownloadLink(allPlaces.get(i).getPictureId());
                        allPlaces.get(i).setUri(currentUri);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //stuff that updates ui
                            listView = (ListView)DiaryListActivity.this.findViewById(R.id.listViewDiary);
                            listView.setAdapter(new CustomAdapter(DiaryListActivity.this));
                        }
                    });
                }
                else{
                    Log.i("ERROR", "BAD THING HAPPENED");
                }

            }
        }).start();

    }

    class CustomAdapter extends BaseAdapter{

        Context context;
        ArrayList<Place> places = requestResult.getValue();

        CustomAdapter(Context c){
            this.context = c;
        }

        @Override
        public int getCount() {
            return places.size();
        }

        @Override
        public Object getItem(int position) {
            return places.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = inflater.inflate(R.layout.single_row, parent, false);

            TextView title = (TextView)row.findViewById(R.id.textView);
            TextView description = (TextView)row.findViewById(R.id.textView2);
            //ImageView image = (ImageView)row.findViewById(R.id.imageView);

            Place temporaryPlace = places.get(position);

            title.setText(temporaryPlace.getName());
            description.setText(temporaryPlace.getDescription());
            description.setMovementMethod(new ScrollingMovementMethod());

                        new DownloadImageTask((ImageView)row.findViewById(R.id.imageView))
            .execute(temporaryPlace.getUri());

           // image.setImageURI(Uri.parse(temporaryPlace.getUri()));

            return row;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
