package com.empresa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Clase que ayuda a gestionar la base de datos SQLite
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "game.db"; // Nombre de la base de datos
    private static final int DATABASE_VERSION = 1; // Versión de la base de datos

    public static final String TABLE_SCORES = "scores"; // Nombre de la tabla
    public static final String COLUMN_ID = "id"; // Nombre de la columna ID
    public static final String COLUMN_USERNAME = "username"; // Nombre de la columna username
    public static final String COLUMN_SCORE = "score"; // Nombre de la columna score

    // Sentencia SQL para crear la tabla
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SCORES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_SCORE + " INTEGER);";

    // Constructor de la clase
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método llamado cuando la base de datos es creada por primera vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); // Ejecuta la sentencia SQL para crear la tabla
    }

    // Método llamado cuando la base de datos necesita ser actualizada
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES); // Elimina la tabla si existe
        onCreate(db); // Crea la tabla nuevamente
    }
}