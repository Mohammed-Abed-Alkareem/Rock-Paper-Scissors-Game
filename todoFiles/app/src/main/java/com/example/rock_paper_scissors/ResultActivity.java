package com.example.rock_paper_scissors;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView feedbackTextView;
    private TextView scoreTextView;
    private Button resetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        feedbackTextView = findViewById(R.id.feedback_text);
        scoreTextView = findViewById(R.id.score_text);
        resetButton = findViewById(R.id.reset_button);

        Intent intent = getIntent();
        String feedback = intent.getStringExtra("feedback");
        int score = intent.getIntExtra("score", 0);

        // Set data to TextViews
        feedbackTextView.setText(feedback);
        scoreTextView.setText("Score: " + score);

        if(score<0)
            scoreTextView.setTextColor(getResources().getColor(R.color.red));
        else if(score>0)
            scoreTextView.setTextColor(getResources().getColor(R.color.green));
        else
            scoreTextView.setTextColor(getResources().getColor(R.color.orange));

        if(feedback.contains("Draw"))
            feedbackTextView.setTextColor(getResources().getColor(R.color.orange));
        else if(feedback.contains("win"))
            feedbackTextView.setTextColor(getResources().getColor(R.color.green));
        else
            feedbackTextView.setTextColor(getResources().getColor(R.color.red));

        // Reset button click listener
        resetButton.setOnClickListener(v -> {
            Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
            //send score
            mainIntent.putExtra("score", score);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear previous activity stack
            startActivity(mainIntent);
            finish(); // Close this activity
        });

    }
}
