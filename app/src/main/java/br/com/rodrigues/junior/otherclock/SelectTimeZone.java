package br.com.rodrigues.junior.otherclock;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SelectTimeZone extends AppCompatActivity {

    private TimeZoneAdapter mTimeZoneAdapter;
    private RecyclerView myDisplayTimeZone;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_zone);

        Log.d("WTF", String.valueOf(loadTimeZone()));

        myDisplayTimeZone = (RecyclerView) findViewById(R.id.rv_display_time_zone);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        myDisplayTimeZone.setLayoutManager(layoutManager);
        myDisplayTimeZone.setHasFixedSize(true);
        mTimeZoneAdapter = new TimeZoneAdapter(loadTimeZone());
        myDisplayTimeZone.setAdapter(mTimeZoneAdapter);



        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    private String[] loadTimeZone(){
        //pegando fusorario
        String [] ids = TimeZone.getAvailableIDs();
        String [] zone = new String[ids.length];

        for(int i=0; i<=ids.length; i++){
            //aqui onde passaremos os id para la
            zone[i] = displayTimeZones(TimeZone.getTimeZone(ids[i]));
        }

        return zone;
    }

    private String displayTimeZones(TimeZone timeZone){
        long hours = TimeUnit.MILLISECONDS.toHours(timeZone.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeZone.getRawOffset())
                                    - TimeUnit.HOURS.toMinutes(hours);
        // evitando problema -4:30
        minutes = Math.abs(minutes);

        String fusorario = "";
        if(hours >0){
            fusorario = String.format("(GMT+%d:%02d) %s",hours,minutes, timeZone.getID());
        } else{
            fusorario = String.format("(GMT%d:%02d) %s", hours, minutes, timeZone.getID());
        }

        return fusorario;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }else if(id ==R.id.action_settings){
            mTimeZoneAdapter.setTimeZones(loadTimeZone());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
