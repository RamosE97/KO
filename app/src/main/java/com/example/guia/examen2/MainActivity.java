package com.example.guia.examen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    public static final int NIVEL=2;
    public static int nivel=50;
    public static int tiemposegundos=60;

    public static ArrayList<Jugador> listaJugadores;
    Button btnJuego, btnPuntajes, btnConfi;
    Jugador jug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJuego = findViewById(R.id.btnJuego);
        btnPuntajes = findViewById(R.id.btnPuntajes);
        btnConfi = findViewById(R.id.btnConfi);
        listaJugadores=new ArrayList<>();
        if(savedInstanceState!=null) {
            if (savedInstanceState.getParcelableArrayList("Lista") != null) {
                this.listaJugadores = savedInstanceState.getParcelableArrayList("Lista");
            }
        }
    }

    public int buscar(Jugador j){
        int index = -1;
        int bound = MainActivity.listaJugadores.size();
        for (int userInd = 0; userInd < bound; userInd++) {
            if (MainActivity.listaJugadores.get(userInd).Id==(j.Id)) {
                index = userInd;
                break;
            }
        }
        return index;
    }
    public void OnClickJuego(View v){
        if(listaJugadores.size()==0){
            Toast.makeText(this, "No hay ningun jugador registrado", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(MainActivity.this,Juego.class);
            intent.putExtra("Jugador",jug);
            intent.putExtra("Posicion",buscar(jug));
            startActivity(intent);
        }
    }

    public void OnClickPuntajes(View v){
        if(listaJugadores.size()==0){
            Toast.makeText(this, "No hay ningun jugador registrado", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, Lista.class);
            startActivity(intent);
        }
    }

    public void OnClickConfi(View v){
        Intent intent = new Intent(MainActivity.this,Configuracion.class);
        startActivityForResult(intent,NIVEL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case NIVEL:
                if (data == null) return;
                String n=data.getStringExtra("NICK");
                nivel = data.getIntExtra("NIVEL",50);
                tiemposegundos = data.getIntExtra("TIEMPO",60000);
                jug=new Jugador(listaJugadores.size()+1,n,0,nivel, tiemposegundos);
                listaJugadores.add(jug);
                Toast.makeText(this, ""+ nivel+tiemposegundos, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Lista",listaJugadores);
    }

}
