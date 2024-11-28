package com.empresa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RankingActivity extends AppCompatActivity {
    private ScoreDAO scoreDAO;
    private ScoreAdapter scoreAdapter;
    private ListView listView;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        scoreDAO = new ScoreDAO(this);
        listView = findViewById(R.id.listView);
        searchEditText = findViewById(R.id.searchEditText);

        loadScores("");

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadScores(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadScores(String query) {
        List<Score> scores;
        if (query.isEmpty()) {
            scores = scoreDAO.getAllScores();
        } else {
            scores = scoreDAO.getScoresByUsername(query);
        }
        scoreAdapter = new ScoreAdapter(this, scores);
        listView.setAdapter(scoreAdapter);
    }
}