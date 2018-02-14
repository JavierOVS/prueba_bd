package com.example.javybatman.prueba_bd;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {

    Button btnAgregarMiembro;
    ListView lista;
    SQLC dbconeccion;
    TextView tv_tareaID, tv_titulo, tv_materia, tv_fecha, tv_descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        dbconeccion = new SQLC(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);

        //acción del boton agregar miembro
        btnAgregarMiembro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MyActivity.this, AgregarMiembro.class);
                startActivity(iagregar);
            }
        });

        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[] {
                DBh.TAREA_ID,
                DBh.TAREA_TITULO,
                DBh.TAREA_MATERIA,
                DBh.TAREA_FECHA,
                DBh.TAREA_DESCRIPCION,
        };
        int[] to = new int[] {
                R.id.tarea_id,
                R.id.titulo,
                R.id.materia,
                R.id.fecha,
                R.id.descripcion
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MyActivity.this, R.layout.formato_final, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_tareaID = (TextView) view.findViewById(R.id.tarea_id);
                tv_titulo = (TextView) view.findViewById(R.id.titulo);
                tv_materia = (TextView) view.findViewById(R.id.materia);
                tv_fecha = (TextView) view.findViewById(R.id.fecha);
                tv_descripcion = (TextView) view.findViewById(R.id.descripcion);

                String aux_tareaId = tv_tareaID.getText().toString();
                String aux_titulo = tv_titulo.getText().toString();
                String aux_materia = tv_materia.getText().toString();
                String aux_fecha = tv_fecha.getText().toString();
                String aux_descripcion= tv_descripcion.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModificarMiembro.class);
                modify_intent.putExtra("tareaId", aux_tareaId);
                modify_intent.putExtra("titulo", aux_titulo);
                modify_intent.putExtra("materia", aux_materia);
                modify_intent.putExtra("fecha", aux_fecha);
                modify_intent.putExtra("descripcion", aux_descripcion);
                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate
} //termina clase

