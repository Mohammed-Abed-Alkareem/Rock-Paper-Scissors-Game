package com.example.rock_paper_scissors;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private ImageView adversaryChoiceImageView;
    private int score;
    private Random random;
    private String feedback;
    private final String[] choices = {"rock", "paper", "scissors"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Random with a seed based on the current time
        random = new Random(System.currentTimeMillis());


        try {
            // Get the score from the intent
            Intent intent = getIntent();
            score = intent.getIntExtra("score", 0);
        } catch (Exception e) {
            // If the score is not passed, set it to 0
            score = 0;
        }



        // Initialize TextView and ImageView
        scoreTextView = findViewById(R.id.score);
        scoreTextView.setText("Score: " + score);

        adversaryChoiceImageView = findViewById(R.id.adversary_choice);



        ImageButton rockButton = findViewById(R.id.button_rock);
        ImageButton paperButton = findViewById(R.id.button_paper);
        ImageButton scissorsButton = findViewById(R.id.button_scissors);

        // ROCK BUTTON
        rockButton.setOnClickListener(v -> {
            String adversaryChoice = choices[random.nextInt(choices.length)];
            calculateScore("rock", adversaryChoice);
            updateAdversaryChoiceImage(adversaryChoice);
            deactivateButtons();

            Toast.makeText(this, "Please wait for the result", Toast.LENGTH_LONG).show();
            // Delay to show the result
            new Handler().postDelayed(this::showResult, 5000);


        });

        // PAPER BUTTON
        paperButton.setOnClickListener(v -> {
            String adversaryChoice = choices[random.nextInt(choices.length)];
            calculateScore("paper", adversaryChoice);
            updateAdversaryChoiceImage(adversaryChoice);
            deactivateButtons();

            Toast.makeText(this, "Please wait for the result", Toast.LENGTH_LONG).show();
            // Delay to show the result
            new Handler().postDelayed(this::showResult, 5000);

        });

        // SCISSORS BUTTON
        scissorsButton.setOnClickListener(v -> {
            String adversaryChoice = choices[random.nextInt(choices.length)];
            calculateScore("scissors", adversaryChoice);
            updateAdversaryChoiceImage(adversaryChoice);
            deactivateButtons();

            Toast.makeText(this, "Please wait for the result", Toast.LENGTH_LONG).show();
            // Delay to show the result
            new Handler().postDelayed(this::showResult, 5000);

        });
    }


    // Method to update the ImageView with the adversary's choice
    private void updateAdversaryChoiceImage(String adversaryChoice) {
        try {
            int imageResource = getResources().getIdentifier(adversaryChoice, "drawable", getPackageName());

            adversaryChoiceImageView.setImageResource(imageResource);
            adversaryChoiceImageView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
        }

    }

    // The cumulative score TextView should be “0” initially and change by:
    // − 3 for every win.
    // − 1 for every draw.
    // − -4 for every loss.
    public void calculateScore(String playerChoice, String adversaryChoice) {
        if (playerChoice.equals("rock")) {
            if (adversaryChoice.equals("rock")) {
                score -= 1;
                feedback = "Draw!";
            }
            else if (adversaryChoice.equals("paper")) {
                score -= 4;
                feedback = "You lose *_*";
            }
            else {
                score += 3;
                feedback = "You win ^_^";
            }
        }

        else if (playerChoice.equals("paper")) {
            if (adversaryChoice.equals("rock")) {
                score += 3;
                feedback = "You win ^_^";
            }
            else if (adversaryChoice.equals("paper")) {
                score -= 1;
                feedback = "Draw!";
            }
            else {
                score -= 4;
                feedback = "You lose *_*";
            }
        }

        else { // playerChoice.equals("scissors")
            if (adversaryChoice.equals("rock")) {
                score -= 4;
                feedback = "You lose *_*";
            }
            else if (adversaryChoice.equals("paper")) {
                score += 3;
                feedback = "You win ^_^";
            }
            else {
                score -= 1;
                feedback = "Draw!";
            }
        }

    }
    // Method to start ResultActivity and pass data
    private void showResult() {
        Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
        resultIntent.putExtra("feedback", feedback);
        resultIntent.putExtra("score", score);
        startActivity(resultIntent);
    }

    // Method to deactivate the buttons
    private void deactivateButtons() {
        findViewById(R.id.button_rock).setEnabled(false);
        findViewById(R.id.button_paper).setEnabled(false);
        findViewById(R.id.button_scissors).setEnabled(false);
    }

}