package com.example.javybatman.prueba_bd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AgregarMiembro extends Activity implements OnClickListener {
    EditText ti, tt, tm, tf, td;
    Button btnAgregar, read_bt;
    SQLC dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_miembro);
        tt = (EditText) findViewById(R.id.et_titulo_id);
        tm = (EditText) findViewById(R.id.et_materia_id);
        tf = (EditText) findViewById(R.id.et_fecha_id);
        td = (EditText) findViewById(R.id.et_descripcion_id);
        btnAgregar = (Button) findViewById(R.id.btnAgregarId);

        dbconeccion = new SQLC(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAgregarId:
                String titulo = tt.getText().toString();
                String materia = tm.getText().toString();
                String fecha = tf.getText().toString();
                String descripcion = td.getText().toString();
                dbconeccion.insertarDatos(titulo,materia, fecha, descripcion);
                Intent main = new Intent(AgregarMiembro.this, MyActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }
}