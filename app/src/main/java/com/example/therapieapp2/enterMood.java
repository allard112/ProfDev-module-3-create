package com.example.therapieapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;

public class EnterMood extends AppCompatActivity  {

    Button inputMood;
    Button setTime;
    TextView timeDisplay;
    TextView dateDisplay;

    private moodDataViewModel moodData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_mood);

        inputMood = findViewById(R.id.input_mood);
        setTime = findViewById(R.id.setTime);
        timeDisplay = findViewById(R.id.timeDisplay);
        dateDisplay = findViewById(R.id.dateDisplay);
        moodData = new ViewModelProvider(this).get(moodDataViewModel.class);
//        timeDisplay.setText(moodData.getTime().toString());
        inputMood.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {
               openMoodInput();
           }
        });
        // Create the observer which updates the UI.
        final Observer<Time> TimeObserver = new Observer<Time>() {
            @Override
            public void onChanged(@Nullable final Time newTime) {
                // Update the UI, in this case, a TextView.
                System.out.println("Time has changed.");
                timeDisplay.setText(newTime.toString());
            }
        };
        final Observer<DateObject> DateObserver = new Observer<DateObject>() {
            @Override
            public void onChanged(DateObject dateObject) {
                System.out.println("Time has changed.");
                dateDisplay.setText(dateObject.toString());
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        moodData.getTime().observe(this, TimeObserver);
        moodData.getDate().observe(this, DateObserver);
        final Calendar c = Calendar.getInstance();
        Time temptime = new Time(millis(true,c.get(Calendar.HOUR_OF_DAY)-1)+millis(false,c.get(Calendar.MINUTE)));
        DateObject tempdate = new DateObject(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        moodData.setTime(temptime);
        moodData.setDate(tempdate);

    }
    void openMoodInput() {
        final Dialog dialog = new Dialog(EnterMood.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.mood_dialog);

        //Initializing the dialog's views
        final ImageButton bad_button = dialog.findViewById(R.id.bad_button);
        final ImageButton meh_button = dialog.findViewById(R.id.meh_button);
        final ImageButton okay_button = dialog.findViewById(R.id.okay_button);
        final ImageButton good_button = dialog.findViewById(R.id.good_button);
        final ImageButton great_button = dialog.findViewById(R.id.great_button);
        final Button submit_button = dialog.findViewById(R.id.submit_button);
        final EditText number_entry = dialog.findViewById(R.id.mood_number);

        final float[] MoodValue = {0};

        bad_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MoodValue[0] = 0;
                submitMood(MoodValue[0]);
                dialog.dismiss();
            }
        });

        meh_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MoodValue[0] = 2.5f;
                submitMood(MoodValue[0]);
                dialog.dismiss();
            }
        });

        okay_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MoodValue[0] = 5;
                submitMood(MoodValue[0]);
                dialog.dismiss();
            }
        });

        good_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MoodValue[0] = 7.5f;
                submitMood(MoodValue[0]);
                dialog.dismiss();
            }
        });

        great_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MoodValue[0] = 10;
                submitMood(MoodValue[0]);
                dialog.dismiss();
            }

        });

        submit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MoodValue[0] = Float.valueOf(number_entry.getText().toString());
                submitMood(MoodValue[0]);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void submitMood(float mood) {
        if(mood>10){
            mood=10;
        }else if(mood<0){
            mood=0;
        }
        System.out.println(mood);
        //outputMood.setText(Float.toString(mood));
    }

    public void showTimePickerDialog(View v) {

        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    private long millis(boolean isHour, int time){
        if(isHour){
            return time * 3600000;
        }else{
            return time * 60000;
        }
    }
}