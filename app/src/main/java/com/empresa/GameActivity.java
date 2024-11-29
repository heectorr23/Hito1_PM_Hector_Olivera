package com.empresa;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Actividad que maneja el juego
public class GameActivity extends AppCompatActivity {
    private int score = 0; // Puntuación inicial
    private int level = 1; // Nivel inicial
    private int scoreIncrement = 1; // Incremento de puntuación inicial
    private TextView scoreTextView; // TextView para mostrar la puntuación
    private TextView levelTextView; // TextView para mostrar el nivel
    private Button tapButton; // Botón para incrementar la puntuación
    private Button saveScoreButton; // Botón para guardar la puntuación
    private String username; // Nombre de usuario
    private ScoreDAO scoreDAO; // DAO para acceder a las puntuaciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game); // Establece el layout de la actividad

        username = getIntent().getStringExtra("USERNAME"); // Obtiene el nombre de usuario del intent
        scoreDAO = new ScoreDAO(this); // Inicializa el DAO

        scoreTextView = findViewById(R.id.scoreTextView); // Inicializa el TextView de la puntuación
        levelTextView = findViewById(R.id.levelTextView); // Inicializa el TextView del nivel
        tapButton = findViewById(R.id.tapButton); // Inicializa el botón de incrementar puntuación
        saveScoreButton = findViewById(R.id.saveScoreButton); // Inicializa el botón de guardar puntuación

        // Establece el listener para el botón de incrementar puntuación
        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level < 10) {
                    score += scoreIncrement; // Incrementa la puntuación
                    updateScoreAndLevel(); // Actualiza la puntuación y el nivel
                }
            }
        });

        // Establece el listener para el botón de guardar puntuación
        saveScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore(new Score(username, score)); // Guarda la puntuación
            }
        });
    }

    // Método para actualizar la puntuación y el nivel
    private void updateScoreAndLevel() {
        scoreTextView.setText("Score: " + score); // Actualiza el TextView de la puntuación
        int newLevel = (score / 20) + 1; // Cambia de nivel cada 20 puntos
        if (newLevel != level) {
            level = newLevel;
            scoreIncrement = level; // Incrementa la puntuación con cada nivel
            levelTextView.setText("Level: " + level); // Actualiza el TextView del nivel
            changeVisualsForLevel(level); // Cambia los visuales para el nivel
        }
    }

    // Método para cambiar los visuales según el nivel
    private void changeVisualsForLevel(int level) {
        int backgroundColor;
        int textColor = getResources().getColor(R.color.black); // Color de texto por defecto

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

        findViewById(R.id.gameLayout).setBackgroundColor(backgroundColor); // Cambia el color de fondo
        scoreTextView.setTextColor(textColor); // Cambia el color del texto de la puntuación
        levelTextView.setTextColor(textColor); // Cambia el color del texto del nivel
    }

    // Método para guardar la puntuación
    private void saveScore(Score score) {
        new SaveScoreTask().execute(score); // Ejecuta la tarea de guardar puntuación en segundo plano
    }

    // Clase interna para guardar la puntuación en segundo plano
    private class SaveScoreTask extends AsyncTask<Score, Void, Void> {
        @Override
        protected Void doInBackground(Score... scores) {
            scoreDAO.saveScore(scores[0]); // Guarda la puntuación en la base de datos
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(GameActivity.this, "Score saved successfully", Toast.LENGTH_SHORT).show(); // Muestra un mensaje de éxito
        }
    }
}