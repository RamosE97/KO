package com.example.guia.examen2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class Lista extends AppCompatActivity {

    private ArrayAdapter adapter;
    ListView listView;
    ArrayList<Jugador> listDificil;
    ArrayList<Jugador> listFacil;
    ArrayList<Jugador> listMedio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView = findViewById(R.id.list);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MainActivity.listaJugadores= Jugador.quickSortNiv(MainActivity.listaJugadores);
        listDificil=new ArrayList<>();
        listMedio=new ArrayList<>();
        listFacil=new ArrayList<>();
        DivLista(MainActivity.listaJugadores);
        listFacil = Jugador.quickSortPun(listFacil);
        listMedio = Jugador.quickSortPun(listMedio);
        listDificil = Jugador.quickSortPun(listDificil);

        MainActivity.listaJugadores.clear();
        MainActivity.listaJugadores.addAll(listDificil);
        MainActivity.listaJugadores.addAll(listMedio);
        MainActivity.listaJugadores.addAll(listFacil);

        adapter = new AdaptadorJugador(Lista.this,MainActivity.listaJugadores);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void DivLista(ArrayList<Jugador> lst){
        Iterator<Jugador> iterator = lst.iterator();
        while (iterator.hasNext()){
            Jugador jugador=iterator.next();
            if(jugador.nivel==3){
                listFacil.add(jugador);
            }else{
                if(jugador.nivel==2){
                    listMedio.add(jugador);
                }else{
                    listDificil.add(jugador);
                }
            }
        }
    }
}
