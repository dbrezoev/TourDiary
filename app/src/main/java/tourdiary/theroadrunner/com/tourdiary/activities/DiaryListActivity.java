package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class DiaryListActivity extends Activity implements View.OnClickListener{

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
    }
}
