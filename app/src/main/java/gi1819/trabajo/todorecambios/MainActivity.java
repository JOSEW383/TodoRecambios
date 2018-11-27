package gi1819.trabajo.todorecambios;

import android.content.Context;
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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("users").child("usuario1").setValue("contraseña1"); //AÑADIR DATO EJEMPLO
        iniciaRegistro();



    }

    private void iniciaRegistro() {


        Button BiniciarSesion = findViewById(R.id.BiniciarSesion);

        BiniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Tusuario = findViewById(R.id.Tusuario);
                EditText Tcontraseniaa = findViewById(R.id.Tcontrasenia);

                //Toast toast = Toast.makeText(getApplicationContext(), "Usuario "+Tusuario.getText()+" incorrecto", Toast.LENGTH_SHORT);
                //toast.show();
                //mDatabase = FirebaseDatabase.getInstance().getReference();

                /*final String value = "";
                Toast toast = Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT);
                toast.show();*/



                /*ValueEventListener UserListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            User us = dataSnapshot.getValue(User.class);

                            Toast toast = Toast.makeText(getApplicationContext(),"Usuario: "+us.name+" : "+us.pass, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast toast = Toast.makeText(getApplicationContext(),"ERROR DE LECTURA", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                };*/





            }
        });
    }
}
