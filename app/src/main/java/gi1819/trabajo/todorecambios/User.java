package gi1819.trabajo.todorecambios;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String name;
    public String pass;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String nombre, String contrasenia) {
        this.name = nombre;
        this.pass = contrasenia;
    }
}
