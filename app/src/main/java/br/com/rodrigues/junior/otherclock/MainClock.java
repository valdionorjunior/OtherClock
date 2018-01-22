package br.com.rodrigues.junior.otherclock;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        DateFormat df;

        df = new SimpleDateFormat(simpleFormat);

        //int h = calendar.get(Calendar.HOUR_OF_DAY);
        //int m = calendar.get(Calendar.MINUTE);
        //int s = calendar.get(Calendar.SECOND);

        //return h+":"+m+":"+s;

        return String.valueOf(df.format(calendar.getTime()));


    }

    @Override
    public void onClick(View v) {
        mySelectTimeZone.setText("Anda não é o que eu quero");
    }
}
