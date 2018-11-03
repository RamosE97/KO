package com.example.guia.examen2;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Parcelable {
    int Id;
    String nick;
    int puntaje;
    int nivel;
    int tiempo;

    public Jugador(int i, String nick, int puntaje, int nivel, int tiempo) {
        this.Id=i;
        this.nick = nick;
        this.puntaje = puntaje;
        this.nivel = nivel;
        this.tiempo = tiempo;
    }
    private Jugador(Parcel in) {
        this.Id=in.readInt();
        this.nick = in.readString();
        this.puntaje = in.readInt();
        this.nivel = in.readInt();
        this.tiempo=in.readInt();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(nick);
        parcel.writeInt(puntaje);
        parcel.writeInt(nivel);
        parcel.writeInt(tiempo);

    }
    public static ArrayList<Jugador> quickSortPun(ArrayList<Jugador> list){
        if(list.size() <=1)
            return  list;

        ArrayList<Jugador> lesser = new ArrayList<>();
        ArrayList<Jugador> greater = new ArrayList<>();

        Jugador pivot = list.get(list.size()-1);
        for(int i=0; i<list.size()-1; i++){
            if (list.get(i).puntaje<pivot.puntaje){
                lesser.add(list.get(i));
            }else{
                greater.add(list.get(i));
            }
        }
        lesser = quickSortPun(lesser);
        greater = quickSortPun(greater);
        lesser.add(pivot);
        lesser.addAll(greater);
        list=lesser;
        return list;
    }

    public static ArrayList<Jugador> quickSortNiv(ArrayList<Jugador> list){
        if(list.size() <=1)
            return  list;

        ArrayList<Jugador> lesser = new ArrayList<>();
        ArrayList<Jugador> greater = new ArrayList<>();

        Jugador pivot = list.get(list.size()-1);
        for(int i=0; i<list.size()-1; i++){
            if (list.get(i).nivel<pivot.nivel){
                lesser.add(list.get(i));
            }else{
                greater.add(list.get(i));
            }
        }
        lesser = quickSortPun(lesser);
        greater = quickSortPun(greater);
        lesser.add(pivot);
        lesser.addAll(greater);
        list=lesser;
        return list;
    }

}
