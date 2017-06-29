package com.gmail.carmonespi.background;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FullscreenActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        imagen      =    (ImageView) findViewById(R.id.imagen);
        texto       =    (TextView) findViewById(R.id.texto);

        final Runnable updateTask = new Runnable() {
            @Override
            public void run() {

                Date hora = new Date();

                StringBuffer sb = new StringBuffer();

                sb.append(hora.getHours() / 2 );
                sb.append(" : ");
                sb.append(hora.getMinutes());
                sb.append(" : ");
                sb.append( String.format( " %02d" , hora.getSeconds()));
                sb.append( ( hora.getHours() < 12 ) ? " AM" : " PM" );

                texto.setText(sb);

                Drawable drawableImager = null ;

                if( hora.getSeconds() >= 0 && hora.getSeconds() < 20){
                    drawableImager = getDrawable(R.drawable.dia);
                }else if( hora.getSeconds() >= 20 && hora.getSeconds() < 40){
                    drawableImager = getDrawable(R.drawable.tarde);
                }else if( hora.getSeconds() >= 40 && hora.getSeconds() <= 60 ){
                    drawableImager = getDrawable(R.drawable.noche);
                }

                imagen.setBackground( drawableImager );
            }
        };

        Timer timer = new Timer("DigitalClock");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(updateTask);
            }
        }, 1 , 1000 );

    }
}
