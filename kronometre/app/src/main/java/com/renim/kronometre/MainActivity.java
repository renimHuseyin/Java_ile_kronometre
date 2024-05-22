package com.renim.kronometre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Chronometer kronometre;
    Button basladur, yenile, ayir;

    TextView textView;

    int deger = 0;
    long fark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kronometre = findViewById(R.id.kronometre);
        basladur = findViewById(R.id.basladur);
        yenile = findViewById(R.id.yenidenbasla);
        ayir = findViewById(R.id.ayir);
        textView = findViewById(R.id.kopy);

        basladur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deger == 0){
                    kronometre.setBase(SystemClock.elapsedRealtime());
                    kronometre.start();
                    basladur.setText("DURDUR");
                    deger = 1;
                } else if (deger == 1) {
                    fark = SystemClock.elapsedRealtime();
                    kronometre.stop();
                    basladur.setText("DEVAM ETTİR");
                    deger = 2;
                }else {
                    kronometre.setBase(kronometre.getBase() + SystemClock.elapsedRealtime() - fark);
                    kronometre.start();
                    basladur.setText("DURDUR");
                    deger = 1;
                }
            }
        });

        yenile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kronometre.setBase(SystemClock.elapsedRealtime());
                kronometre.stop();
                basladur.setText("BAŞLA");
                deger = 0;
            }
        });

        ayir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kronometreDegeri = kronometre.getText().toString();
                textView.setText(kronometreDegeri);
            }
        });
    }
}