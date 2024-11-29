package com.empresa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// DAO para acceder a las puntuaciones en la base de datos
public class ScoreDAO {
    private SQLiteDatabase database; // Base de datos SQLite
    private DatabaseHelper dbHelper; // Helper para gestionar la base de datos

    // Constructor del DAO
    public ScoreDAO(Context context) {
        dbHelper = new DatabaseHelper(context); // Inicializa el helper
        database = dbHelper.getWritableDatabase(); // Obtiene la base de datos en modo escritura
    }

    // Método para guardar una puntuación en la base de datos
    public void saveScore(Score score) {
        ContentValues values = new ContentValues(); // Crea un objeto ContentValues para los valores
        values.put(DatabaseHelper.COLUMN_USERNAME, score.getUsername()); // Añade el nombre de usuario
        values.put(DatabaseHelper.COLUMN_SCORE, score.getScore()); // Añade la puntuación

        long newRowId = database.insert(DatabaseHelper.TABLE_SCORES, null, values); // Inserta los valores en la tabla
        if (newRowId == -1) {
            Log.e("ScoreDAO", "Error inserting score"); // Log de error si la inserción falla
        } else {
            Log.d("ScoreDAO", "Score inserted with row id: " + newRowId); // Log de éxito si la inserción es correcta
        }
    }

    // Método para obtener todas las puntuaciones de la base de datos
    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>(); // Lista para almacenar las puntuaciones
        try (Cursor cursor = database.query(DatabaseHelper.TABLE_SCORES, null, null, null, null, null, DatabaseHelper.COLUMN_SCORE + " DESC")) {
            // Consulta la tabla y ordena por puntuación descendente
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)); // Obtiene el ID
                String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)); // Obtiene el nombre de usuario
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SCORE)); // Obtiene la puntuación
                scores.add(new Score(id, username, score)); // Añade la puntuación a la lista
            }
        }
        return scores; // Devuelve la lista de puntuaciones
    }

    // Método para obtener las puntuaciones por nombre de usuario
    public List<Score> getScoresByUsername(String username) {
        List<Score> scores = new ArrayList<>(); // Lista para almacenar las puntuaciones
        String selection = DatabaseHelper.COLUMN_USERNAME + " LIKE ?"; // Condición de selección
        String[] selectionArgs = { "%" + username + "%" }; // Argumentos de selección
        try (Cursor cursor = database.query(DatabaseHelper.TABLE_SCORES, null, selection, selectionArgs, null, null, DatabaseHelper.COLUMN_SCORE + " DESC")) {
            // Consulta la tabla con la condición y ordena por puntuación descendente
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)); // Obtiene el ID
                String user = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)); // Obtiene el nombre de usuario
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SCORE)); // Obtiene la puntuación
                scores.add(new Score(id, user, score)); // Añade la puntuación a la lista
            }
        }
        return scores; // Devuelve la lista de puntuaciones
    }
}