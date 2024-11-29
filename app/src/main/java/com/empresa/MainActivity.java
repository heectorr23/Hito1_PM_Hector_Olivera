package com.empresa;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Actividad principal de la aplicación
public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText; // Campo de texto para el nombre de usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el layout de la actividad

        usernameEditText = findViewById(R.id.usernameEditText); // Inicializa el campo de texto
        Button startGameButton = findViewById(R.id.startGameButton); // Inicializa el botón de iniciar juego

        // Establece el listener para el botón de iniciar juego
        startGameButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString(); // Obtiene el texto del campo de usuario
            if (username.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show(); // Muestra un mensaje si el campo está vacío
            } else {
                Intent intent = new Intent(MainActivity.this, GameActivity.class); // Crea un intent para iniciar GameActivity
                intent.putExtra("USERNAME", username); // Añade el nombre de usuario al intent
                startActivity(intent); // Inicia la actividad
            }
        });

        Button viewRankingButton = findViewById(R.id.viewRankingButton); // Inicializa el botón de ver ranking
        // Establece el listener para el botón de ver ranking
        viewRankingButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RankingActivity.class); // Crea un intent para iniciar RankingActivity
            startActivity(intent); // Inicia la actividad
        });
    }
}