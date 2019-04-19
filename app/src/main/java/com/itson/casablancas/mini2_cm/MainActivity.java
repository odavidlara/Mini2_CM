package com.itson.casablancas.mini2_cm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button emisor, receptor;
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emisor = (Button) findViewById(R.id.btnEmisor);
        receptor = (Button) findViewById(R.id.btnReceptor);

        emisor.setOnClickListener(this);
        receptor.setOnClickListener(this);

        this.requestPermission();
    }

    public void requestPermission(){
        //checking if we have permission

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){

            //request the permission

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_COARSE_LOCATION);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                             String permissions[],
                                             int[] grantResults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION: {
                //if request is cancelled, the result array is empty
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                }else{
                    //permission denied
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnEmisor:
                startActivity(new Intent(this, Discoverer.class));
                finish();
                break;
            case R.id.btnReceptor:
                startActivity(new Intent(this, Discoverer.class));
                finish();
                break;
        }
    }
}
