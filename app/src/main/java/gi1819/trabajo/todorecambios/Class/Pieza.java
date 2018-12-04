package gi1819.trabajo.todorecambios.Class;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Pieza  implements Serializable {
    public String nombre;
    public String fabricante;
    public String tipo;

    public Pieza(){

    }

    public Pieza(String nombre, String fabricante, String tipo) {
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.tipo = tipo;
    }
}
