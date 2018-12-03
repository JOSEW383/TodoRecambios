package gi1819.trabajo.todorecambios.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import gi1819.trabajo.todorecambios.R;
import gi1819.trabajo.todorecambios.Class.Usuario;

public class TpiezasActivity extends AppCompatActivity {
    private Usuario us;
    private ArrayList<String> datos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpiezas);
        us = (Usuario)getIntent().getExtras().getSerializable("usuario");
        datos = new ArrayList<>();

        inicializaArray();
    }

    private void inicializaArray() {
        DatabaseReference Tpiezas =
                FirebaseDatabase.getInstance().getReference()
                        .child("Tpiezas");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                datos.add(dataSnapshot.getKey().toString());

                ArrayAdapter adaptador =
                        new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, datos);


                ListView lstOpciones = findViewById(R.id.LstOpciones);

                lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        String opcionSeleccionada = a.getItemAtPosition(position).toString();

                        if(us.rol.equals("administrador")||us.rol.equals("usuario")){
                        Intent intent = new Intent(getApplication(), PiezasActivity.class);
                        intent.putExtra("tipoPieza", opcionSeleccionada);
                        intent.putExtra("usuario", us);
                        startActivity(intent);
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "No tienes permisos para ver las piezas",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });

                lstOpciones.setAdapter(adaptador);

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };

        Tpiezas.addChildEventListener(childEventListener);
    }
}
