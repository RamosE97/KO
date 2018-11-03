package com.example.guia.examen2;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    Button btnAdivinar;
    int numeroIngresado,numAleatorio = 0, nivelGenerar = 50,intentos = 0;
    TextView txtNivel, txtIntentos, txtNick, lblNumero,lblAdivina,lblTiempo,lblTiempodispo;
    EditText txtNumIngre;
    private ProgressBar pgbarHorizontal;
    int contprogress=0;
    CountDownTimer mCountDownTimer;

    private Handler manejarprocesos;
    int time=60000;
    String num = "";
    public static Jugador j;
    public static int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        txtNick = findViewById(R.id.txtNickJug);
        txtIntentos = findViewById(R.id.txtIntentos);
        txtNivel = findViewById(R.id.txtNivel);
        lblNumero = findViewById(R.id.txtNumero);
        txtNumIngre = findViewById(R.id.txtNumeroIngresado);
        lblAdivina = findViewById(R.id.lblAdivina);
        lblTiempo = findViewById(R.id.lblTiempo);
        lblTiempodispo = findViewById(R.id.lblTiempoDispo);
        pgbarHorizontal = findViewById(R.id.progressBar);
        manejarprocesos= new Handler();
        j=getIntent().getParcelableExtra("Jugador");
        posicion=getIntent().getIntExtra("Posicion",0);
        nivelGenerar = (j.nivel);
        time = MainActivity.tiemposegundos;
        //this.pgbarHorizontal.setMax(100);
        pgbarHorizontal.setProgress(0);
        txtNick.setText(j.getNick());
        txtIntentos.setText(intentos+"");
        if (nivelGenerar == 3) {
            txtNivel.setText("Fácil");
            lblAdivina.setText("Adivina el numero entre 1 y 50");
            lblTiempodispo.setText("1 minuto");
        } else {
            if (nivelGenerar == 2) {
                txtNivel.setText("Medio");
                lblAdivina.setText("Adivina el numero entre 1 y 100");
                lblTiempodispo.setText("2 minutos");
            } else {
                if (nivelGenerar == 1) {
                    txtNivel.setText("Difícil");
                    lblAdivina.setText("Adivina el numero entre 1 y 150");
                    lblTiempodispo.setText("3 minutos");
                }
            }
        }
        numAleatorio = (int) (Math.random() * nivelGenerar) + 1;
        mCountDownTimer=new CountDownTimer(time,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                contprogress++;
                j.setTiempo(j.getTiempo()+1);
                lblTiempo.setText(contprogress+" s");
                pgbarHorizontal.setProgress(contprogress*100/(time/1000));
            }

            @Override
            public void onFinish() {
                //Do what you want
                contprogress++;
                pgbarHorizontal.setProgress(100);
                mCountDownTimer.cancel();
                //Toast.makeText(getApplicationContext(), "Intento terminado", Toast.LENGTH_SHORT).show();
                finish();
            }

        };
        mCountDownTimer.start();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
        MainActivity.listaJugadores.set(posicion, j);
    }

    public void OnClickPrueba(View v){
       //num = txtNumIngre.getText().toString();
        //Toast.makeText(this, ""+num, Toast.LENGTH_SHORT).show();
        String nume = txtNumIngre.getText().toString();
        if(nume.isEmpty()){
           Toast.makeText(this, "Ingresa un numero", Toast.LENGTH_SHORT).show();
       }else {
            numeroIngresado = Integer.parseInt(txtNumIngre.getText().toString());
           // new Thread(new HiloSecundario()).start();
           if (numeroIngresado == numAleatorio) {
               lblNumero.setText(numAleatorio + "");
               Toast.makeText(this, "Adivinaste, felicidades", Toast.LENGTH_SHORT).show();
               finish();
           }
           if (numeroIngresado < numAleatorio) {
               intentos++;
               txtIntentos.setText(intentos+"");
               j.setPuntaje(intentos);
               Toast.makeText(this, "Prueba con numero mayor", Toast.LENGTH_SHORT).show();
           }
           if (numeroIngresado > numAleatorio) {
               intentos++;
               txtIntentos.setText(intentos+"");
               j.setPuntaje(intentos);
               Toast.makeText(this, "Prueba con numero menor", Toast.LENGTH_SHORT).show();
           }
       }
   }

    final class HiloSecundario implements Runnable{
        @Override
        public void run(){
            while (contprogress<time){
                MetodoEspera();
                //ESTABLECEMOS MANEJADOR CON LA PARTE VISUAL
                manejarprocesos.post(new Runnable() {
                    @Override
                    public void run() {
                        pgbarHorizontal.setProgress(contprogress);

                        if(contprogress==time){
                            //Toast.makeText(Juego.this,"Tiempo agotado",Toast.LENGTH_LONG).show();
                            contprogress=0;
                            finish();
                            //pgbarHorizontal.setProgress(0);
                        }

                    }
                });
            }
        }

        private void MetodoEspera(){
            try{
                Thread.sleep(3000);
                contprogress++;
                lblTiempo.setText(contprogress+" s");
            }catch (Exception e){

            }
        }
    }

}


