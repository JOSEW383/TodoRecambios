package gi1819.trabajo.todorecambios.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gi1819.trabajo.todorecambios.Activities.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getApplication(), LoginActivity.class);
        startActivity(intent);

        finish();
    }
}
