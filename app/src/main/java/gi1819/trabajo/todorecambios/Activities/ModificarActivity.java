package gi1819.trabajo.todorecambios.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gi1819.trabajo.todorecambios.Class.Pieza;
import gi1819.trabajo.todorecambios.R;

public class ModificarActivity extends AppCompatActivity {
    private Pieza pieza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        pieza = (Pieza)getIntent().getExtras().getSerializable("pieza");
        iniciaProceso();
    }

    private void iniciaProceso() {
        TextView tituloPieza = findViewById(R.id.tipoPiezaMod);
        tituloPieza.setText(pieza.tipo);

        TextView nombrePieza = findViewById(R.id.Tnombre);
        nombrePieza.setText(pieza.nombre);

        TextView fabricantePieza = findViewById(R.id.Tfabricante);
        fabricantePieza.setText(pieza.fabricante);

        Button Beliminar = findViewById(R.id.Beliminar);
        Beliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminaPieza();
                finish();
            }
        });

        Button Bmodificar = findViewById(R.id.Bmodificar);
        Bmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminaPieza();
                insertaPieza();
                finish();
            }
        });
    }

    private void eliminaPieza(){

        final DatabaseReference miBD = FirebaseDatabase.getInstance().getReference()
                .child("Tpiezas")
                .child(pieza.tipo);

                miBD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numHijos = dataSnapshot.getChildrenCount();
                if(numHijos<2){
                    miBD.setValue("");
                }else{
                    miBD.child(pieza.nombre).removeValue();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error de Firebase", Toast.LENGTH_SHORT);
                toast.show();
            }});
    }

    private void insertaPieza(){

        TextView nombrePieza = findViewById(R.id.Tnombre);
        TextView fabricantePieza = findViewById(R.id.Tfabricante);

        Pieza pi = new Pieza(nombrePieza.getText().toString(),fabricantePieza.getText().toString(), pieza.tipo);
        FirebaseDatabase.getInstance().getReference()
                .child("Tpiezas")
                .child(pieza.tipo)
                .child(nombrePieza.getText().toString())
                .setValue(pi);
    }

}
