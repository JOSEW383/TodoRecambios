package gi1819.trabajo.todorecambios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciaRegistro();
    }

    private void iniciaRegistro() {
        Button BiniciarSesion = findViewById(R.id.BiniciarSesion);

        BiniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText Tusuario = findViewById(R.id.Tusuario);
                final EditText Tcontraseniaa = findViewById(R.id.Tcontrasenia);

                DatabaseReference miBD = FirebaseDatabase.getInstance().getReference()
                                .child("usuarios")
                                .child(Tusuario.getText().toString());

                miBD.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String valor = (String) dataSnapshot.getValue();

                        if(valor==null){
                            Toast toast = Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT);
                            toast.show();
                        }else if(valor.equals(Tcontraseniaa.getText().toString())){ //Usuario y contrasenia correcto
                            Toast toast = Toast.makeText(getApplicationContext(), "Login correcto", Toast.LENGTH_SHORT);
                            toast.show();
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Error en la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }
}
