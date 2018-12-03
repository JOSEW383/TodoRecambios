package gi1819.trabajo.todorecambios.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import gi1819.trabajo.todorecambios.Adapters.PiezaAdapter;
import gi1819.trabajo.todorecambios.Class.Pieza;
import gi1819.trabajo.todorecambios.Class.Usuario;
import gi1819.trabajo.todorecambios.R;

public class PiezasActivity extends AppCompatActivity {

    private String Tpieza;
    private ArrayList<Pieza> datos = null;
    private Usuario us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piezas);

        us = (Usuario)getIntent().getExtras().getSerializable("usuario");
        Tpieza = (String)getIntent().getExtras().getSerializable("tipoPieza");
        datos = new ArrayList<>();

        inicializaArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insertar:
                if(us.rol.equals("administrador")||us.rol.equals("usuario")){
                    Intent intent = new Intent(getApplication(), InsertarActivity.class);
                    intent.putExtra("tipoPieza", Tpieza);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No tienes permisos para añadir piezas",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void inicializaArray() {

        DatabaseReference Tpiezas =
                FirebaseDatabase.getInstance().getReference()
                        .child("Tpiezas")
                        .child(Tpieza);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Pieza pi = dataSnapshot.getValue(Pieza.class);
                datos.add(pi);

                ArrayAdapter adaptador = new PiezaAdapter(getApplicationContext(),datos);

                ListView LstPiezas = findViewById(R.id.LstPiezas);

                LstPiezas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        String opcionSeleccionada = a.getItemAtPosition(position).toString();
                        Toast toast = Toast.makeText(getApplicationContext(), opcionSeleccionada, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                LstPiezas.setAdapter(adaptador);

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
