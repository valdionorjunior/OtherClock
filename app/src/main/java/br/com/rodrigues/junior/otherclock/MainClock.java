package br.com.rodrigues.junior.otherclock;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainClock extends AppCompatActivity implements View.OnClickListener{

    private TextView myClock;
    private TextView myDate;
    private TextView mySelectTimeZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clock);

        myClock = (TextView) findViewById(R.id.tv_clock);
        myDate = (TextView) findViewById(R.id.tv_date);
        mySelectTimeZone = (TextView) findViewById(R.id.tv_select_time_zone);

        final Handler upRealTime = new Handler();

        upRealTime.post(new Runnable() {
            @Override
            public void run() {
                myClock.setText(updateTime("HH:mm:ss"));
                myDate.setText(updateTime("E. dd 'de' MMMM 'de' yyyy"));
                upRealTime.postDelayed(this,100);
            }
        });


        mySelectTimeZone.setOnClickListener(this);

    }

    public MainClock() {
        super();
    }

    private String updateTime(String simpleFormat) {

        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(simpleFormat);

        return String.valueOf(df.format(calendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        mySelectTimeZone.setText("Anda não é o que eu quero");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemTheWasSelected = item.getItemId();
        if(menuItemTheWasSelected == R.id.action_settings){
            Intent intent = new Intent(MainClock.this,SelectTimeZone.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
