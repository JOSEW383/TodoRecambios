package gi1819.trabajo.todorecambios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciaRegistro();

    }

    //---------------------------------------METODOS--------------------------------------------
    private void iniciaRegistro() {
        Button BiniciarSesion = findViewById(R.id.BiniciarSesion);

        BiniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText Tusuario = findViewById(R.id.Tusuario);
                final EditText Tcontraseniaa = findViewById(R.id.Tcontrasenia);

                DatabaseReference miBD = FirebaseDatabase.getInstance().getReference()
                                .child("Usuarios")
                                .child(Tusuario.getText().toString());

                miBD.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //String valor = (String) dataSnapshot.getValue(); //Para obtener el valor (si solo hubiese uno)
                        Usuario us = dataSnapshot.getValue(Usuario.class);

                        if(us==null){
                            Toast toast = Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_SHORT);
                            toast.show();
                        }else if(us.contrasenia.equals(Tcontraseniaa.getText().toString())){ //Usuario y contrasenia correcto
                            /*Toast toast = Toast.makeText(getApplicationContext(), "Login correcto", Toast.LENGTH_SHORT);
                            toast.show();*/

                            Intent intent = new Intent(getApplication(), TpiezaActivity.class);
                            intent.putExtra("usuario", us);
                            startActivity(intent);

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
