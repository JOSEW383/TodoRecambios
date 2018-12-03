package gi1819.trabajo.todorecambios.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gi1819.trabajo.todorecambios.Class.Pieza;
import gi1819.trabajo.todorecambios.R;

public class PiezaAdapter extends ArrayAdapter<Pieza> {

    public List<Pieza> listaPieza;

    public PiezaAdapter(@NonNull Context context, @NonNull ArrayList<Pieza> listaPieza) {
        super(context,R.layout.listitem_pieza,listaPieza);
        this.listaPieza=listaPieza;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listitem_pieza, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.Pnombre);
        lblNombre.setText(listaPieza.get(position).nombre);

        TextView lblTipo = (TextView)item.findViewById(R.id.Ptipo);
        lblTipo.setText(listaPieza.get(position).tipo);

        TextView lblFabricante = (TextView)item.findViewById(R.id.Pfabricante);
        lblFabricante.setText(listaPieza.get(position).fabricante);

        return(item);
    }
}
