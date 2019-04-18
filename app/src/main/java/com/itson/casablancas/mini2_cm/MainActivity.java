package com.itson.casablancas.mini2_cm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button emisor, receptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emisor = (Button) findViewById(R.id.btnEmisor);
        receptor = (Button) findViewById(R.id.btnReceptor);

        emisor.setOnClickListener(this);
        receptor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnEmisor:
                startActivity(new Intent(this, Emisor.class));
                finish();
                break;
            case R.id.btnReceptor:
                startActivity(new Intent(this, Emisor.class));
                finish();
                break;
        }
    }
}
