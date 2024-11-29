package com.empresa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Adaptador para mostrar las puntuaciones en una lista
public class ScoreAdapter extends BaseAdapter {
    private Context context; // Contexto de la aplicación
    private List<Score> scores; // Lista de puntuaciones

    // Constructor del adaptador
    public ScoreAdapter(Context context, List<Score> scores) {
        this.context = context;
        this.scores = scores;
    }

    @Override
    public int getCount() {
        return scores.size(); // Devuelve el número de puntuaciones
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position); // Devuelve la puntuación en la posición especificada
    }

    @Override
    public long getItemId(int position) {
        return scores.get(position).getId(); // Devuelve el ID de la puntuación en la posición especificada
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_score, parent, false); // Infla el layout para cada ítem de la lista
        }

        TextView usernameTextView = convertView.findViewById(R.id.usernameTextView); // Inicializa el TextView para el nombre de usuario
        TextView scoreTextView = convertView.findViewById(R.id.scoreTextView); // Inicializa el TextView para la puntuación

        Score score = scores.get(position); // Obtiene la puntuación en la posición especificada
        usernameTextView.setText(score.getUsername()); // Establece el nombre de usuario en el TextView
        scoreTextView.setText(String.valueOf(score.getScore())); // Establece la puntuación en el TextView

        return convertView; // Devuelve la vista para el ítem de la lista
    }
}