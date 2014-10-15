package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.model.system.DownloadedFile;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueCondition;
import com.telerik.everlive.sdk.core.query.definition.filtering.simple.ValueConditionOperator;
import com.telerik.everlive.sdk.core.result.RequestResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import models.Place;
import tourdiary.theroadrunner.com.tourdiary.R;


/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class AroundActivity extends Activity {

    static final String API_KEY = "uDwdWIo61CYYVcha";
    final static String MY_ACTION = "MY_ACTION";

    EverliveApp app ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_info_activity);

        app = new EverliveApp("uDwdWIo61CYYVcha");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
