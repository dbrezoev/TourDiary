package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.telerik.everlive.sdk.core.EverliveApp;

import fragments.FragmentDescription;
import interfaces.Communicator;
import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 13.10.2014 г..
 */
public class AppInfoActivity extends Activity implements Communicator{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_info_activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void respond(int index) {
        FragmentManager manager = getFragmentManager();
        FragmentDescription f2 = (FragmentDescription) manager.findFragmentById(R.id.fragmentDescription);
        f2.changeData(index);
    }
}
