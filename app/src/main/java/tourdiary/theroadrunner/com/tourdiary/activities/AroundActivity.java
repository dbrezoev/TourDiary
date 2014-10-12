package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueConditionOperator;
import com.telerik.everlive.sdk.core.result.RequestResult;

import java.util.ArrayList;
import java.util.UUID;

import models.Place;
import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class AroundActivity extends Activity {

    static final int DEFAULT_DISTANCE = 5;
    static final String API_KEY = "uDwdWIo61CYYVcha";
    EverliveApp app ;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.around_activity);
        Log.d("In on create","AAAAAAAAAAAA");

        app = new EverliveApp(API_KEY);



//        RequestResult<ArrayList<Place>> requestResult;
//        requestResult = app.workWith().data(Place.class).getAll().executeSync();
//        for (Place book : requestResult.getValue()) {
//            Log.i("App_name", "retrieved book: " + book.getName());
//        }

        RequestResult<Integer> placeCountResult = app.workWith().data(Place.class).getCount().executeSync();
        if (placeCountResult.getSuccess()) {
            Log.d("Success", String.valueOf(placeCountResult.getValue()));
        }
        else{
            Log.d("FAIL", "No");
        }

//        RequestResult<ArrayList<Place>> requestResult;
//        requestResult = app.workWith().data(Place.class).getAll().executeSync();
//        for (Place place : requestResult.getValue()) {
//            Log.i("App_name", "retrieved place: " + place.getName());
//        }

//        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new MyLocationListener();
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        locationManager = null;
        locationListener = null;
    }

    class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

            //TODO implement logic to find distance between two points

            double longt = location.getLongitude();
            double lat = location.getLatitude();





            //Log.i("POSITION", "" + String.valueOf(longt) + "; " + String.valueOf(lat) + ";");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

//        private void getObjectsAround(EverliveApp db, int distance){
//            RequestResult<ArrayList<Place>> booksFromAuthorResult = db.workWith().data(Place.class).get()
//                    .where(new ValueCondition(), author, ValueConditionOperator.EqualTo).executeSync();
//        }
    }
}
