package br.com.rodrigues.junior.otherclock;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainClock extends AppCompatActivity {

    private TextView myClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clock);

        myClock = (TextView) findViewById(R.id.tv_clock);

        final Handler upRealTime = new Handler();

        upRealTime.post(new Runnable() {
            @Override
            public void run() {
                myClock.setText(updateTime());
                upRealTime.postDelayed(this,100);
            }
        });

    }

    public MainClock() {
        super();
    }

    private String updateTime() {

        Calendar calendar = new GregorianCalendar();

        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);

        return h+":"+m+":"+s;

    }

}
