package com.example.eggtrabacon.Presenters;

import android.os.Handler;

import com.example.eggtrabacon.Interfaces.EggTimerListener;
import com.example.eggtrabacon.Models.EggTimer;

public class EggTimerPresenter implements EggTimerListener {

    private View view;
    private EggTimer eggTimer;

    // Constructor
    public EggTimerPresenter(View view){
        Handler handler = new Handler();
        handler.post(new EggTimer());
        eggTimer = new EggTimer();
        this.view = view;
    }

    public void SetCookingTime(long cookingTime) {
        eggTimer.setCookingTime(cookingTime);
    }


    public boolean GetIsRunning(){
       return eggTimer.getIsRunning();
    }

    public String CreateTimeText(){
        return eggTimer.CreateTimeText();
    }

    public void start()
    {
        eggTimer.addListener(this);
        eggTimer.start();
    }

    public void stop(){
        eggTimer.removeListener(this);
        eggTimer.StopCount();
    }

    @Override
    public void onCountDown(String timeText) {
        view.onCountDown(timeText);
    }

    @Override
    public void onEggTimerStopped() {
        view.onEggTimerStopped();
    }

    public interface View {
        public void onCountDown(String timeText);
        public void onEggTimerStopped();
    }
}

