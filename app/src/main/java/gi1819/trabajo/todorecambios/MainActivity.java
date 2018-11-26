package gi1819.trabajo.todorecambios;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BiniciarSesion = findViewById(R.id.BiniciarSesion);

        BiniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Tusuario = findViewById(R.id.Tusuario);
                EditText Tcontraseniaa = findViewById(R.id.Tcontrasenia);

                Toast toast = Toast.makeText(getApplicationContext(), "Usuario "+Tusuario.getText()+" incorrecto", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
