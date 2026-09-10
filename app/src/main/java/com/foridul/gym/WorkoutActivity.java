package com.foridul.gym;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;
import android.os.CountDownTimer;

public class WorkoutActivity extends Activity {

    LinearLayout root;
    TextView timerText;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.parseColor("#0B0F14"));

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(20), dp(18), dp(30));
        scroll.addView(root);

        addTitle("TODAY'S WORKOUT");
        addText("Complete each exercise and track your sets.");

        addExercise("Push Ups", "3 Sets × 12 Reps");
        addExercise("Squats", "3 Sets × 15 Reps");
        addExercise("Shoulder Press", "3 Sets × 10 Reps");
        addExercise("Bicep Curls", "3 Sets × 12 Reps");
        addExercise("Plank", "3 Sets × 30 Seconds");

        addTimer();

        Button finish = new Button(this);
        finish.setText("Finish Workout");
        finish.setTextSize(16);
        finish.setAllCaps(false);
        finish.setTextColor(Color.BLACK);
        finish.setBackgroundColor(Color.parseColor("#12D69B"));

        finish.setOnClickListener(v -> {
            Toast.makeText(
                WorkoutActivity.this,
                "Workout completed! Great job!",
                Toast.LENGTH_LONG
            ).show();
            finish();
        });

        LinearLayout.LayoutParams finishParams =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(55)
            );

        finishParams.setMargins(0, dp(20), 0, dp(10));
        root.addView(finish, finishParams);

        setContentView(scroll);
    }

    private void addExercise(String name, String reps) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(15), dp(12), dp(15), dp(12));
        card.setBackgroundColor(Color.parseColor("#17202B"));

        TextView title = new TextView(this);
        title.setText(name);
        title.setTextColor(Color.WHITE);
        title.setTextSize(18);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView info = new TextView(this);
        info.setText(reps);
        info.setTextColor(Color.parseColor("#A7B0BE"));
        info.setTextSize(14);
        info.setPadding(0, dp(5), 0, dp(8));

        CheckBox completed = new CheckBox(this);
        completed.setText("Completed");
        completed.setTextColor(Color.parseColor("#12D69B"));

        card.addView(title);
        card.addView(info);
        card.addView(completed);

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        params.setMargins(0, 0, 0, dp(12));
        root.addView(card, params);
    }

    private void addTimer() {

        TextView heading = new TextView(this);
        heading.setText("REST TIMER");
        heading.setTextColor(Color.WHITE);
        heading.setTextSize(20);
        heading.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        heading.setPadding(0, dp(15), 0, dp(10));
        root.addView(heading);

        timerText = new TextView(this);
        timerText.setText("01:00");
        timerText.setTextColor(Color.parseColor("#12D69B"));
        timerText.setTextSize(34);
        timerText.setGravity(Gravity.CENTER);
        root.addView(timerText);

        Button startTimer = new Button(this);
        startTimer.setText("Start 60 Sec Rest");
        startTimer.setAllCaps(false);

        startTimer.setOnClickListener(v -> startRestTimer());

        root.addView(startTimer);
    }

    private void startRestTimer() {

        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished / 1000;

                timerText.setText(
                    String.format("00:%02d", seconds)
                );
            }

            public void onFinish() {

                timerText.setText("00:00");

                Toast.makeText(
                    WorkoutActivity.this,
                    "Rest finished!",
                    Toast.LENGTH_SHORT
                ).show();
            }
        }.start();
    }

    private void addTitle(String text) {

        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.parseColor("#12D69B"));
        tv.setTextSize(27);
        tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        root.addView(tv);
    }

    private void addText(String text) {

        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.parseColor("#A7B0BE"));
        tv.setTextSize(15);
        tv.setPadding(0, dp(5), 0, dp(20));

        root.addView(tv);
    }

    private int dp(int value) {

        float density =
            getResources().getDisplayMetrics().density;

        return (int) (value * density + 0.5f);
    }
}
