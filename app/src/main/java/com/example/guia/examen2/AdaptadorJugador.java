package com.example.guia.examen2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorJugador extends ArrayAdapter<Jugador> {
    public AdaptadorJugador(@NonNull Context context, List<Jugador> object) {
        super(context, 0,object);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obteniendo el dato
        Jugador jugador = getItem(position);
        if (convertView == null) {
            //al adaptador le mando el xml de la vista personalizada
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jugador, parent, false);
        }
        TextView lblNombre =  convertView.findViewById(R.id.lblNombre);
        TextView lblPuntaje =  convertView.findViewById(R.id.lblPuntaje);
        TextView lblNivel =  convertView.findViewById(R.id.lblNivel);
        TextView lblTiempo =  convertView.findViewById(R.id.lblTiempo);

        // mostrar los datos
       // img.setImageResource(R.drawable.ic_user);
        lblNombre.setText(jugador.getNick());
        lblPuntaje.setText(String.valueOf(jugador.getPuntaje()));
        if(jugador.getNivel()==1){
            lblNivel.setText("Dificil");
        }else{
            if(jugador.getNivel()==2){
                lblNivel.setText("Medio");
            }else{
                lblNivel.setText("Facil");
            }
        }
        lblTiempo.setText(String.valueOf(jugador.getTiempo())+" s");

        // Return la convertView ya con los datos
        return convertView;
    }

}
