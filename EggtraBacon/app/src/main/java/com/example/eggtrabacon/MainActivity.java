package com.example.eggtrabacon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.eggtrabacon.Presenters.EggTimerPresenter;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements EggTimerPresenter.View {

    private EggTimerPresenter eggTimerPresenter;
    private ToggleButton startBtn;
    private ConstraintLayout layout;
    private TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);
        startBtn = findViewById(R.id.StartButton);
        eggTimerPresenter = new EggTimerPresenter(this);
        timerText = findViewById(R.id.TimerText);
    }

    // Handles Egg Boiling time
    public void OnButtonEggSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.Soft:
                EnableSetTime(300000);
                break;
            case R.id.Smiling:
                EnableSetTime(420000);
                break;
            case R.id.Hard:
                EnableSetTime(600000);
                break;
            default:
                throw new RuntimeException("Where does this magic button come from?");
        }
    }


    public void EnableSetTime(long time){
        startBtn.setEnabled(true);
        eggTimerPresenter.SetCookingTime(time);
        timerText.setText(eggTimerPresenter.CreateTimeText());
    }

    public void OnBtnStart(View view) {
        boolean isRunning = eggTimerPresenter.GetIsRunning();
        if (isRunning) {
            eggTimerPresenter.stop();
        } else {
            eggTimerPresenter.start();
        }
    }

    @Override
    public void onCountDown(String timeText) {
        timerText.setText(timeText);
    }

    @Override
    public void onEggTimerStopped() {
        // TODO Animation to come
    }

    // For fun
    public void setRandomColor() {
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        layout.setBackgroundColor(color);
    }
}
