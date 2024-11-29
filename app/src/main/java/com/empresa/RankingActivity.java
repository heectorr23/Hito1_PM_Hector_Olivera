package com.empresa;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

// Actividad que muestra el ranking de puntuaciones
public class RankingActivity extends AppCompatActivity {
    private ScoreDAO scoreDAO; // DAO para acceder a las puntuaciones
    private ScoreAdapter scoreAdapter; // Adaptador para mostrar las puntuaciones en una lista
    private ListView listView; // Lista para mostrar las puntuaciones
    private EditText searchEditText; // Campo de texto para buscar puntuaciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking); // Establece el layout de la actividad

        scoreDAO = new ScoreDAO(this); // Inicializa el DAO
        listView = findViewById(R.id.listView); // Inicializa la lista
        searchEditText = findViewById(R.id.searchEditText); // Inicializa el campo de texto

        loadScores(""); // Carga todas las puntuaciones

        // Añade un listener para el campo de texto de búsqueda
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadScores(s.toString()); // Carga las puntuaciones que coinciden con la búsqueda
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Método para cargar las puntuaciones
    private void loadScores(String query) {
        List<Score> scores;
        if (query.isEmpty()) {
            scores = scoreDAO.getAllScores(); // Obtiene todas las puntuaciones si no hay búsqueda
        } else {
            scores = scoreDAO.getScoresByUsername(query); // Obtiene las puntuaciones que coinciden con la búsqueda
        }
        scoreAdapter = new ScoreAdapter(this, scores); // Inicializa el adaptador con las puntuaciones
        listView.setAdapter(scoreAdapter); // Establece el adaptador en la lista
    }
}