package com.example.javybatman.prueba_bd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModificarMiembro extends Activity implements OnClickListener {

    EditText tt, tm, tf, td;
    Button btnActualizar, btnEliminar;

    long tarea_id;

    SQLC dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_miembro);

        dbcon = new SQLC(this);
        dbcon.abrirBaseDeDatos();

        tt = (EditText) findViewById(R.id.ete_titulo);
        tm = (EditText) findViewById(R.id.ete_materia);
        tf = (EditText) findViewById(R.id.ete_fecha);
        td = (EditText) findViewById(R.id.ete_descripcion);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
        String tareaID = i.getStringExtra("tareaId");
        String titulot = i.getStringExtra("titulo");
        String materiat = i.getStringExtra("materia");
        String fechat = i.getStringExtra("fecha");
        String descripciont = i.getStringExtra("descripcion");

        tarea_id = Long.parseLong(tareaID);

        tt.setText(titulot);
        tm.setText(materiat);
        tf.setText(fechat);
        tf.setText(descripciont);

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnActualizar:
                String titulo_upd = tt.getText().toString();
                String meateria_upd = tm.getText().toString();
                String fecha_upd = tf.getText().toString();
                String descripcion_upd = td.getText().toString();
                dbcon.actualizarDatos(tarea_id,titulo_upd,meateria_upd,fecha_upd,descripcion_upd);
                this.returnHome();
                break;

            case R.id.btnEliminar:
                dbcon.deleteData(tarea_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MyActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}
