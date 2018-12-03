package gi1819.trabajo.todorecambios.Class;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Usuario implements Serializable {
    public String nombre;
    public String contrasenia;
    public String rol;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String nombre, String contrasenia, String rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
}