package com.example.eggscusemev20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Main Activity is low coupled with View - with a 1 - 1 Relationship
 */
public class MainActivity extends AppCompatActivity implements EggTimerView {

    //Bindings to View
    SeekBar seekBar; // Slider presented left.
    TextView textView; // Text to view value from slider
    double min = 300000; // 5 Seconds
    double max = 420000; // 7 Seconds
    double current = 600000; // 9 Seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.TimeChooser);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onCountDown() {

    }

    @Override
    public void onEggTimerStopped() {

    }
}
