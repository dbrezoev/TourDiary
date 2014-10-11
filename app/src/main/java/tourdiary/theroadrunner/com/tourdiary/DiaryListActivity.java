package tourdiary.theroadrunner.com.tourdiary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
