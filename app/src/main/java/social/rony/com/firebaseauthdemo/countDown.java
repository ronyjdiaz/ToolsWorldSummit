package social.rony.com.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import com.todddavies.components.progressbar.ProgressWheel;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;

import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.NOVEMBER;


public class countDown extends AppCompatActivity {



    private ProgressWheel mDaysWheel;
    @BindView(R.id.activity_countdown_timer_days_text)  TextView mDaysLabel;
    private ProgressWheel mHoursWheel;
    @BindView(R.id.activity_countdown_timer_hours_text)  TextView mHoursLabel;
    private ProgressWheel mMinutesWheel;
    @BindView(R.id.activity_countdown_timer_minutes_text)  TextView mMinutesLabel;
    private ProgressWheel mSecondsWheel;
    @BindView(R.id.activity_countdown_timer_seconds_text)  TextView mSecondsLabel;



    // Timer setup
   // Time conferenceTime;// = new Time();
    Calendar conferenceTime  = Calendar.getInstance(TimeZone.getTimeZone("GMT+04:00"));
    int hour = 4;
    int minute = 0;
    int second = 0;
    int monthDay = 12;
    // month is zero based...7 == August
    int month = FEBRUARY;
    int year = 2017;

    // Values displayed by the timer
    private int mDisplayDays;
    private int mDisplayHours;
    private int mDisplayMinutes;
    private int mDisplaySeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        configureViews();
        configureConferenceDate();
    }

    private void configureViews() {

        this.mDaysWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_days);
        this.mHoursWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_hours);
        this.mMinutesWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_minutes);
        this.mSecondsWheel = (ProgressWheel) findViewById(R.id.activity_countdown_timer_seconds);

    }

    private void configureConferenceDate() {


        conferenceTime.set(year, month, monthDay, hour, minute, second);
       // conferenceTime.normalize(true);
        long confMillis = conferenceTime.getTimeInMillis();
        Calendar nowTime  = Calendar.getInstance();


       // nowTime.normalize(true);
        long nowMillis = nowTime.getTimeInMillis();

        long milliDiff = confMillis - nowMillis;
        int dias = (int) (milliDiff/1000)/86400;



        new CountDownTimer(milliDiff, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // decompose difference into days, hours, minutes and seconds
                countDown.this.mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
                countDown.this.mDisplayHours = (int) (((millisUntilFinished / 1000) - (countDown.this.mDisplayDays * 86400)) / 3600);
                countDown.this.mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((countDown.this.mDisplayDays * 86400) + (countDown.this.mDisplayHours * 3600))) / 60);
                countDown.this.mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);

                countDown.this.mDaysWheel.setText(String.valueOf(countDown.this.mDisplayDays));
                countDown.this.mDaysWheel.setProgress(countDown.this.mDisplayDays);

                countDown.this.mHoursWheel.setText(String.valueOf(countDown.this.mDisplayHours));
                countDown.this.mHoursWheel.setProgress(countDown.this.mDisplayHours * 15);

                countDown.this.mMinutesWheel.setText(String.valueOf(countDown.this.mDisplayMinutes));
                countDown.this.mMinutesWheel.setProgress(countDown.this.mDisplayMinutes * 6);

                Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
                an.setFillAfter(true);

                countDown.this.mSecondsWheel.setText(String.valueOf(countDown.this.mDisplaySeconds));
                countDown.this.mSecondsWheel.setProgress(countDown.this.mDisplaySeconds * 6);
            }

            @Override
            public void onFinish() {
                //TODO: this is where you would launch a subsequent activity if you'd like.  I'm currently just setting the seconds to zero
                // Logger.d(TAG, "Timer Finished...");
                countDown.this.mSecondsWheel.setText("0");
                countDown.this.mSecondsWheel.setProgress(0);
                finish();
                startActivity(new Intent( getApplicationContext(), MainActivity.class));
            }
        }.start();
    }
}
