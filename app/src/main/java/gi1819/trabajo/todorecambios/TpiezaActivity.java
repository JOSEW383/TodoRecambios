package gi1819.trabajo.todorecambios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TpiezaActivity extends AppCompatActivity {
    private Usuario us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpieza);
        us = (Usuario)getIntent().getExtras().getSerializable("usuario");

        final String[] datos =
                new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, datos);

        ListView lstOpciones = findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);

    }
}
