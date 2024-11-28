package com.empresa;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private int score = 0;
    private int level = 1;
    private int scoreIncrement = 1;
    private TextView scoreTextView;
    private TextView levelTextView;
    private Button tapButton;
    private Button saveScoreButton;
    private String username;
    private ScoreDAO scoreDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        username = getIntent().getStringExtra("USERNAME");
        scoreDAO = new ScoreDAO(this);

        scoreTextView = findViewById(R.id.scoreTextView);
        levelTextView = findViewById(R.id.levelTextView);
        tapButton = findViewById(R.id.tapButton);
        saveScoreButton = findViewById(R.id.saveScoreButton);

        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level < 10) {
                    score += scoreIncrement;
                    updateScoreAndLevel();
                }
            }
        });

        saveScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore(new Score(username, score));
            }
        });
    }

    private void updateScoreAndLevel() {
        scoreTextView.setText("Score: " + score);
        int newLevel = (score / 20) + 1; // Cambia de nivel cada 20 puntos
        if (newLevel != level) {
            level = newLevel;
            scoreIncrement = level; // Increase score increment with each level
            levelTextView.setText("Level: " + level);
            changeVisualsForLevel(level);
        }
    }

    private void changeVisualsForLevel(int level) {
        int backgroundColor;
        int textColor = getResources().getColor(R.color.black); // Default text color

        switch (level) {
            case 2:
                backgroundColor = getResources().getColor(R.color.level2);
                break;
            case 3:
                backgroundColor = getResources().getColor(R.color.level3);
                break;
            case 4:
                backgroundColor = getResources().getColor(R.color.level4);
                break;
            case 5:
                backgroundColor = getResources().getColor(R.color.level5);
                break;
            case 6:
                backgroundColor = getResources().getColor(R.color.level6);
                break;
            case 7:
                backgroundColor = getResources().getColor(R.color.level7);
                break;
            case 8:
                backgroundColor = getResources().getColor(R.color.level8);
                break;
            case 9:
                backgroundColor = getResources().getColor(R.color.level9);
                break;
            case 10:
                backgroundColor = getResources().getColor(R.color.level10);
                break;
            default:
                backgroundColor = getResources().getColor(R.color.level1);
                break;
        }

        findViewById(R.id.gameLayout).setBackgroundColor(backgroundColor);
        scoreTextView.setTextColor(textColor);
        levelTextView.setTextColor(textColor);
    }

    private void saveScore(Score score) {
        new SaveScoreTask().execute(score);
    }

    private class SaveScoreTask extends AsyncTask<Score, Void, Void> {
        @Override
        protected Void doInBackground(Score... scores) {
            scoreDAO.saveScore(scores[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(GameActivity.this, "Score saved successfully", Toast.LENGTH_SHORT).show();
        }
    }
}