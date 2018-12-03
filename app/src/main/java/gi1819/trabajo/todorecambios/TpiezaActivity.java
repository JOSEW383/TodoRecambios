package gi1819.trabajo.todorecambios;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TpiezaActivity extends AppCompatActivity {
    private Usuario us;
    private ArrayList<String> datos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpieza);
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
