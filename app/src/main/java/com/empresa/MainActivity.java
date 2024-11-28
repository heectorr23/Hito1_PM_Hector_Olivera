package com.empresa;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        Button startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(v -> { // FUNCIÓN LAMBDA
            String username = usernameEditText.getText().toString();
            if (username.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });
        Button viewRankingButton = findViewById(R.id.viewRankingButton);
        viewRankingButton.setOnClickListener(v -> { // FUNCIÓN LAMBDA
            Intent intent = new Intent(MainActivity.this, RankingActivity.class);
            startActivity(intent);
        });
    }
}