package com.example.eggtrabacon.Models;

import android.os.CountDownTimer;
import android.os.Handler;

import com.example.eggtrabacon.Interfaces.EggTimerListener;

import java.util.ArrayList;
import java.util.List;

public class EggTimer extends Thread {
    // Fields
    private String RemainingTimeText;
    private Handler handler = new Handler();
    private boolean isRunning = false;
    private CountDownTimer countDownTimer;
    private long cookingTime;
    private List<EggTimerListener> listeners = new ArrayList<>();
    public long getCookingTime() {
        return cookingTime;
    }
    public String getRemainingTimeText(){
        return RemainingTimeText;
    }
    public void setCookingTime(long cookingTime) {
        this.cookingTime = cookingTime;
    }
    public boolean getIsRunning() {return isRunning;}

    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }
    public void removeListener(EggTimerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run(){
        handler.post(new Runnable(){
            @Override
            public void run() {
                if (!isRunning) {
                    CreateTimeText();
                    StartCount();
                } else {
                    StopCount();
                }
            }
        });
    }

    public void StartCount() {
        NotifyTimerChanged();
        countDownTimer = new CountDownTimer(cookingTime, 1000) {
            @Override
            public void onTick(long remainingTime) {
                NotifyTimerChanged();
                //CreateTimeText();
                cookingTime = remainingTime;
            }


            @Override
            public void onFinish() {

            }
        }.start();
        isRunning = true;
    }

    public void StopCount() {
        countDownTimer.cancel();
        //cookingTime = 0; // Uncomment to Reset completely
        isRunning = false;
    }

    public void NotifyTimerChanged(){
        for (EggTimerListener l : listeners) {
            l.onCountDown(CreateTimeText());
        }
    }

    /// Converts Cooking Time to a presentable string
    public String CreateTimeText() {
        int min = (int) cookingTime / 60000;
        int sec = (int) cookingTime % 60000 / 1000;

        RemainingTimeText = "";

        if (min < 10) RemainingTimeText += "0";

        RemainingTimeText += min + ":";

        if (sec < 10) RemainingTimeText += "0";

        RemainingTimeText += sec;
        return RemainingTimeText;
    }

}
