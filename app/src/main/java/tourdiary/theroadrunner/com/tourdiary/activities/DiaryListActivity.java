package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.result.RequestResult;

import java.util.ArrayList;

import models.Place;
import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class DiaryListActivity extends Activity implements View.OnClickListener{

    EverliveApp app;
    RequestResult<ArrayList<Place>> requestResult;
    ArrayList<Place> allPlaces;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        //app = Everlive.getEverliveObj();
        app = new EverliveApp("uDwdWIo61CYYVcha");
        // requestResult = app.workWith().data(Event.class).getAll().executeSync();
        new Thread(new Runnable() {

            @Override
            public void run() {
                requestResult = app.workWith().data(Place.class)
                        .getAll().executeSync();

                //Log.i("CREATE", "RRRRRRRRRRRRR");

                if (requestResult.getSuccess()){
                    allPlaces = requestResult.getValue();
                    //Place ev = (Place)requestResult.getValue();
                    //System.out.println(ev.getName());
                    for (Place book : allPlaces) {
                        Log.i("AAAAAAAAAAAAAAAAAAAApp_place", "retrieved place: " + book.getName() + "--->" +book.getPictureId() + "--->" + book.getDescription());
                    }
                }
                else{
                    //System.out.println(res.getError().toString());
                    Log.i("ERROR", "BAD THING HAPPENED");
                }
//                Event e = ((Event) res.getValue());
//                Log.d("NAME",e.getTitle());
            }
        }).start();


        //for (Event event : requestResult.getValue()) {
        //result = app.workWith().data(Event.class).getCount().executeSync();
        //Log.i("App_name", "retrieved count: " + event.getTitle());
        //}

    }
}
