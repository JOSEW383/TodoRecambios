package gi1819.trabajo.todorecambios;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Usuario implements Serializable {
    public String nombre;
    public String contrasenia;
    public boolean administrador;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String nombre, String contrasenia, boolean administrador) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.administrador = administrador;
    }
}