package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.facades.special.DownloadFileAsStreamFacade;
import com.telerik.everlive.sdk.core.result.RequestResult;

import java.util.ArrayList;
import java.util.UUID;

import models.Place;
import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class DiaryListActivity extends Activity implements View.OnClickListener{

    ListView listView;
    EverliveApp app;
    RequestResult<ArrayList<Place>> requestResult;
    ArrayList<Place> allPlaces;

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


        new Thread(new Runnable() {

            @Override
            public void run() {
                requestResult = app.workWith().data(Place.class)
                        .getAll().executeSync();

                if (requestResult.getSuccess()){
                    allPlaces = requestResult.getValue();

                    for(int i = 0; i < allPlaces.size(); i++){
                        String currentUri = getDownloadLink(allPlaces.get(i).getId());
                        allPlaces.get(i).setUri(currentUri);
                    }

                    for (Place place : allPlaces) {
                        Log.i("SUCCESS", "retrieved place: " + place.getName() + "--->" +place.getPictureId() + "--->" + place.getDescription());
                    }
                }
                else{
                    Log.i("ERROR", "BAD THING HAPPENED");
                }

            }
        }).start();

        listView = (ListView)this.findViewById(R.id.listViewDiary);
        listView.setAdapter(new CustomAdapter(this));
    }

    class CustomAdapter extends BaseAdapter{

        Context context;

        CustomAdapter(Context c){
            this.context = c;
        }

        @Override
        public int getCount() {
            return allPlaces.size();
        }

        @Override
        public Object getItem(int position) {
            return allPlaces.get(position);
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
            ImageView image = (ImageView)row.findViewById(R.id.imageView);

            Place temporaryPlace = allPlaces.get(position);

            title.setText(temporaryPlace.getName());
            description.setText(temporaryPlace.getDescription());
            image.setImageURI(Uri.parse(temporaryPlace.getUri()));

            return row;
        }
    }
}
