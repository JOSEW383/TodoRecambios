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

public class InsertarActivity extends AppCompatActivity {
    private String Tpieza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        Tpieza = (String)getIntent().getExtras().getSerializable("tipoPieza");
        TextView TituloPieza = (TextView)findViewById(R.id.tipoPieza);
        TituloPieza.setText(Tpieza);
        insertapieza();
    }

    private void insertapieza() {
        Button Binsertar = findViewById(R.id.Binsertar);
        Binsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference dbRef =
                        FirebaseDatabase.getInstance().getReference()
                                .child("Tpiezas")
                                .child(Tpieza);

                final EditText nombre = findViewById(R.id.Tnombre);
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference()
                                    .child("Tpiezas").child(Tpieza);
                    rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (!snapshot.hasChild(nombre.getText().toString())) {

                                EditText fabricante = findViewById(R.id.Tfabricante);
                                Pieza pi = new Pieza(nombre.getText().toString(), fabricante.getText().toString(), Tpieza);
                                dbRef.child(nombre.getText().toString()).setValue(pi);
                                finish();

                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "La pieza ya existe",
                                        Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }@Override
                        public void onCancelled(@NonNull DatabaseError databaseError){
                            Toast toast = Toast.makeText(getApplicationContext(), "Error de Firebase", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
            }
        });
    }
}
