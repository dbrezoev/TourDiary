package tourdiary.theroadrunner.com.tourdiary;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button buttonDiary;
    Button buttonAround;
    Button buttonAdd;

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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
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
            intent = new Intent(MainActivity.this, AroundActivity.class);
        }
        else if (v.getId() == R.id.button_add){
            intent = new Intent(MainActivity.this, AddActivity.class);
        }
        startActivity(intent);
    }
}
