package com.example.javybatman.prueba_bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JavyBatman on 11/02/2018.
 */

public class DBh extends SQLiteOpenHelper{

    public static final String TABLE_TAREAS = "tareas";
    public static final String TAREA_ID = "_id";
    public static final String TAREA_TITULO = "titulo";
    public static final String TAREA_MATERIA = "materia";
    public static final String TAREA_FECHA = "fecha";
    public static final String TAREA_DESCRIPCION = "descripcion";

    static final String DB_NAME ="DBTAREAS";
    static final int DB_VERSION =1;

/*    public static final String CREATE_TABLE ="create table "
            +TABLE_TAREAS + "(" + TAREA_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TAREA_TITULO + "TEXT NOT NULL, "
            + TAREA_MATERIA + "TEXT NOT NULL, "
            + TAREA_FECHA + "TEXT NOT NULL, "
            + TAREA_DESCRIPCION + "TEXT NOT NULL);"; */

public static final String CREATE_TABLE = " create table tareas (_id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, materia TEXT NOT NULL, fecha TEXT NOT NULL, descripcion TEXT NOT NULL);";

    public DBh(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase DT) {
        DT.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase DT, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        DT.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREAS);
        onCreate(DT);
    }

}
