package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.checkConnection.ConnectionDetector;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.Tracker;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private static final String NO_INTERNET_MESSAGE = "You don't have internet connection.";
    private static final String NO_INTERNET_CONNECTION = "No internet connection";

    private static final String INTERNET_MESSAGE = "You have internet connection.";
    private static final String INTERNET_CONNECTION = "Internet connection";

    Button buttonDiary;
    Button buttonAround;
    Button buttonAdd;

    Boolean isInternetPresent = false;

    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDiary = (Button)this.findViewById(R.id.button_diary);
        buttonAround = (Button)this.findViewById(R.id.button_what_is_around);
        buttonAdd = (Button)this.findViewById(R.id.button_add);
        buttonDiary.setOnClickListener(this);
        buttonAround.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);

        connectionDetector = new ConnectionDetector(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

            isInternetPresent = connectionDetector.isConnectingToInternet();

            // check for Internet status
            if (isInternetPresent) {
                showAlertDialog(MainActivity.this, INTERNET_CONNECTION,
                        INTERNET_MESSAGE, true);
            } else {
                // Internet connection is not present
                // Ask user to connect to Internet
                showAlertDialog(MainActivity.this, NO_INTERNET_CONNECTION,
                        NO_INTERNET_MESSAGE, false);
            }
            return true;
        }
        else if(id == R.id.app_info){
            Intent intent = new Intent(MainActivity.this, AppInfoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.button_diary){
            intent = new Intent(MainActivity.this, DiaryListActivity.class);
        }
        else if (v.getId() == R.id.button_what_is_around){
            intent = new Intent(MainActivity.this, LocationTrackerActivity.class);
        }
        else if (v.getId() == R.id.button_add){
            intent = new Intent(MainActivity.this, AddActivity.class);
        }

        startActivity(intent);
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);

        alertDialog.setMessage(message);

        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialog.show();
    }
}
