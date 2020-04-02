package com.example.eggactivity;

import android.os.CountDownTimer;

import com.example.eggactivity.interfaces.EggTimerListener;

import java.util.ArrayList;
import java.util.List;

public class EggTimer extends Thread {
    public ArrayList<EggTimerListener> listeners;
    private CountDownTimer countDownTimer;
    private long cookingTime;

    @Override
    public void run(){
        for (EggTimerListener l : listeners){
            l.onCountDown(cookingTime);
        }

    }


    public void addListener(EggTimerListener listener){
        listeners.add(listener);
    }

    public void removeListener(EggTimerListener listener){
        listeners.remove(listener);
    }

}
