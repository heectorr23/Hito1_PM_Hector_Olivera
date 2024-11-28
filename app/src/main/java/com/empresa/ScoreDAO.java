package com.empresa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ScoreDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void saveScore(Score score) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, score.getUsername());
        values.put(DatabaseHelper.COLUMN_SCORE, score.getScore());

        long newRowId = database.insert(DatabaseHelper.TABLE_SCORES, null, values);
        if (newRowId == -1) {
            System.err.println("Error inserting score");
        } else {
            System.out.println("Score inserted with row id: " + newRowId);
        }
    }

    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_SCORES, null, null, null, null, null, DatabaseHelper.COLUMN_SCORE + " DESC");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SCORE));
                scores.add(new Score(id, username, score));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return scores;
    }

    public List<Score> getScoresByUsername(String username) {
        List<Score> scores = new ArrayList<>();
        String selection = DatabaseHelper.COLUMN_USERNAME + " LIKE ?";
        String[] selectionArgs = { "%" + username + "%" };
        Cursor cursor = database.query(DatabaseHelper.TABLE_SCORES, null, selection, selectionArgs, null, null, DatabaseHelper.COLUMN_SCORE + " DESC");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String user = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SCORE));
                scores.add(new Score(id, user, score));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return scores;
    }

}