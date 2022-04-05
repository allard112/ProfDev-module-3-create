package com.example.therapieapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button settingsButton;
    private Button enterMoodButton;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView=(GraphView) findViewById(R.id.graphid);
        series=new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return  sdf.format(new Date((long) value));
                }else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        enterMoodButton = (Button) findViewById(R.id.enterMood);
        enterMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnterMood();
            }
        });

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
                new DataPoint(new Date().getTime(),5),
        };
        return dp;
    }

    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openEnterMood() {
        Intent intent = new Intent(this, EnterMood.class);
        startActivity(intent);
    }
}

