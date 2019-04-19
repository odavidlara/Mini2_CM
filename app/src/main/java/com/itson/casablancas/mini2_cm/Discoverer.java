package com.itson.casablancas.mini2_cm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;

public class Discoverer extends AppCompatActivity {

    private ConnectionsClient connectionsClient;

    private static final Strategy STRATEGY = Strategy.P2P_STAR;

    private static final String TAG = "MinProj 2";

    public static final String SERVICE_ID = "party";

    public static final String CLIENT_NAME = "client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discoverer);

        startDiscovery();
    }

    private void startDiscovery(){

        DiscoveryOptions discoveryOptions =
                new DiscoveryOptions.Builder().setStrategy(STRATEGY).build();

        connectionsClient.startDiscovery(
          CLIENT_NAME,
                endpointDiscoveryCallback,
                discoveryOptions
        );
    }

    private final EndpointDiscoveryCallback endpointDiscoveryCallback =
            new EndpointDiscoveryCallback() {
                @Override
                public void onEndpointFound(@NonNull String endpointId, @NonNull DiscoveredEndpointInfo info) {
                    Log.i(TAG, "onEndpointFound: endpoint found, connecting");

                    connectionsClient.requestConnection(
                            SERVICE_ID,
                            endpointId,
                            connectionLifecycleCallback
                    );
                }

                @Override
                public void onEndpointLost(@NonNull String s) {

                }

                private final ConnectionLifecycleCallback connectionLifecycleCallback =
                        new ConnectionLifecycleCallback() {
                            @Override
                            public void onConnectionInitiated(@NonNull String endpointId, @NonNull ConnectionInfo connectionInfo) {
                                Log.i(TAG, "onConnectionInitiated: accepting Connection");

                                connectionsClient.acceptConnection(endpointId, payloadCallback);
                            }

                            @Override
                            public void onConnectionResult(@NonNull String endpointId, @NonNull ConnectionResolution result) {
                                if(result.getStatus().isSuccess()){
                                    Log.i(TAG, "onConnectionResult: Connection Successful");

                                }else{
                                    Log.i(TAG, "onConnectionResult: Hubo un pedo jefe");
                                }

                            }

                            @Override
                            public void onDisconnected(@NonNull String s) {

                            }
                        };

                private final PayloadCallback payloadCallback =
                        new PayloadCallback() {
                            @Override
                            public void onPayloadReceived(@NonNull String s, @NonNull Payload payload) {
                                Log.i(TAG, "onPayloadReceived: que llego algo dice ");
                            }

                            @Override
                            public void onPayloadTransferUpdate(@NonNull String s, @NonNull PayloadTransferUpdate payloadTransferUpdate) {
                                Log.i(TAG, "onPayloadReceived: que se mando algo dice ");

                            }
                        };

            };
}
