package br.com.rodrigues.junior.otherclock;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SelectTimeZone extends AppCompatActivity {
    private static final int TIME_ZONE_ID = 0;

    private TimeZoneAdapter mtimeZoneAdapter;
    private RecyclerView myDisplayTimeZone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_zone);

        myDisplayTimeZone = (RecyclerView) findViewById(R.id.rv_display_time_zone);

        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myDisplayTimeZone.setLayoutManager(layoutManager);
        myDisplayTimeZone.setHasFixedSize(true);
        mtimeZoneAdapter = new TimeZoneAdapter(TIME_ZONE_ID);
        myDisplayTimeZone.setAdapter(mtimeZoneAdapter);

        //pegando fusorario
        String [] ids = TimeZone.getAvailableIDs();
        for(String id: ids){
            //aqui onde passaremos os id para la
            //myDisplayTimeZone.append(displayTimeZones(TimeZone.getTimeZone(id)));
        }
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
            fusorario = String.format("GMT%d:%02d) %s", hours, minutes, timeZone.getID());
        }

        return fusorario;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
