package com.example.eggactivity;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eggactivity.interfaces.EggTimerListener;

import java.io.Writer;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements EggTimerListener {
    private CountDownTimer countDownTimer;
    private long cookingTime;
    TextView timerTextView;
    private Button start;
    private boolean isRunning;
    private long lastChosenTime;
    private CharSequence doneText = "Your egg is done!";
    private EggTimer eggTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.StartButton);
        start.setText("Start");
        timerTextView = findViewById(R.id.TimerText);

        eggTimer = new EggTimer();
    }


    public void StartTime(View view){
        if(!isRunning) {
            countDownTimer = new CountDownTimer(cookingTime, 1000) {
                @Override
                public void onTick(long timeRemaining) {
                    cookingTime = timeRemaining;
                    TimerRemainingText();
                }
                @Override
                public void onFinish() {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), doneText, duration);
                    toast.show();

                }
            };
            countDownTimer.start();
            isRunning = true;
            start.setText("Stop");
            start.setBackgroundColor(Color.RED);
        }
        else {
            countDownTimer.cancel();
            isRunning = false;
            start.setText("Start");
            cookingTime = lastChosenTime;
            TimerRemainingText();
            start.setBackgroundColor(Color.GREEN);
        }
    }

    public void TimerRemainingText(){
        int min = (int)cookingTime / 60000;
        int sec = (int)cookingTime % 60000 / 1000;

        String remainingText = "";

        if(min < 10) remainingText += "0";

        remainingText += min + ":";

        if(sec < 10) remainingText += "0";

        remainingText += sec;
        timerTextView.setText(remainingText);
    }

    public void OnButtonEggSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.Soft:
                ActivateStartButton(view);
                LinearLayout bgImageSoft = findViewById(R.id.linearLayout);
                bgImageSoft.setBackgroundResource(R.drawable.soft);
                cookingTime = 300000;
                lastChosenTime = cookingTime;
                TimerRemainingText();
                break;
            case R.id.Smiling:
                ActivateStartButton(view);
                LinearLayout bgImageSmiling = findViewById(R.id.linearLayout);
                bgImageSmiling.setBackgroundResource(R.drawable.medium);
                cookingTime = 420000;
                lastChosenTime = cookingTime;
                TimerRemainingText();
                break;
            case R.id.HardBoiled:
                ActivateStartButton(view);
                LinearLayout bgImageHard = findViewById(R.id.linearLayout);
                bgImageHard.setBackgroundResource(R.drawable.hard);
                cookingTime = 600000;
                lastChosenTime = cookingTime;
                TimerRemainingText();
                break;
            default:
                throw new RuntimeException("Unknown Button ID");
        }
    }

    public void ActivateStartButton(View view) {
        Button start = findViewById(R.id.StartButton);
        start.setEnabled(true);
    }

    @Override
    public void onCountDown(long timeLeft) {

    }

    @Override
    public void onEggTimerStopped() {

    }
}
