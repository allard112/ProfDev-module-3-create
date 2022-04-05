package com.example.therapieapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button settingsButton;
    private Button enterMoodButton;
    private Button moodThermometerButton;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView=(GraphView) findViewById(R.id.graphid);
        series=new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);

        enterMoodButton = (Button) findViewById(R.id.enterMood);
        enterMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnterMood();
            }
        });

        moodThermometerButton = (Button) findViewById(R.id.mood_thermometer);
        moodThermometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMoodThermometer();
            }
        });

        //----------------------------------------------------\\
        settingsButton = (Button) findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });
    }

    private DataPoint[] getDataPoint() {
        DataPoint[] dp=new DataPoint[] {
                new DataPoint(new Date().getTime(),1),
                new DataPoint(new Date().getTime(),22),
                new DataPoint(new Date().getTime(),3),
                new DataPoint(new Date().getTime(),14),
                new DataPoint(new Date().getTime(),9),
                new DataPoint(new Date().getTime(),16),
                new DataPoint(new Date().getTime(),8),
                new DataPoint(new Date().getTime(),2),
                new DataPoint(new Date().getTime(),11),
                new DataPoint(new Date().getTime(),18),
                new DataPoint(new Date().getTime(),17),

        };
        return dp;
    }

    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
        //------------------------------------------------------\\

    public void openEnterMood() {
        Intent intent = new Intent(this, enterMood.class);
        startActivity(intent);
    }

    public void openMoodThermometer() {
        Intent intent = new Intent(this, moodThermometer.class);
        startActivity(intent);
    }
}

