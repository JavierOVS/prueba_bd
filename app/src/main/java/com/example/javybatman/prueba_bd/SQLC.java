package com.example.javybatman.prueba_bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by JavyBatman on 11/02/2018.
 */

public class SQLC {

    private DBh dBh;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLC(Context c) {
        ourcontext = c;
    }

    public SQLC abrirBaseDeDatos() throws SQLException {
        dBh = new DBh(ourcontext);
        database= dBh.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dBh.close();
    }

    public void insertarDatos(String titulo, String materia, String fecha, String descripcion){
        ContentValues cv= new ContentValues();
        cv.put(DBh.TAREA_TITULO, titulo);
        cv.put(DBh.TAREA_MATERIA, materia);
        cv.put(DBh.TAREA_FECHA, fecha);
        cv.put(DBh.TAREA_DESCRIPCION, descripcion);
        database.insert(DBh.TABLE_TAREAS, null, cv);
    }
    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[]{
                DBh.TAREA_ID,
                DBh.TAREA_TITULO,
                DBh.TAREA_MATERIA,
                DBh.TAREA_FECHA,
                DBh.TAREA_DESCRIPCION
        };
        Cursor c = database.query(DBh.TABLE_TAREAS, todasLasColumnas, null, null,
                null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public int actualizarDatos(long memberID, String memberTitulo, String memberMateria, String memberFecha, String memberDescripcion){
        ContentValues cvActualizar= new ContentValues();

        cvActualizar.put(DBh.TAREA_DESCRIPCION, memberDescripcion);
        cvActualizar.put(DBh.TAREA_FECHA, memberFecha);
        cvActualizar.put(DBh.TAREA_MATERIA, memberMateria);
        cvActualizar.put(DBh.TAREA_TITULO, memberTitulo);
        int i = database.update(DBh.TABLE_TAREAS, cvActualizar,
                DBh.TAREA_ID + " = " + memberID, null);
        return i;
    }
    public void deleteData(long memberID) {
        database.delete(DBh.TABLE_TAREAS, DBh.TAREA_ID + "="
                + memberID, null);
    }
}
