package com.itson.casablancas.mini2_cm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;

public class Advertiser extends AppCompatActivity {

    //client's name that's visible to other devices when connecting
    public static final String CLIENT_NAME = "dj";

    /**
     * service id. discoverer and advertiser can use this to id to
     * verify each other before connecting
     */
    public static final String SERVICE_ID = "party";

    private static final String TAG = "MinProj 2";

    /**
     * Connection stategy we'll use
     * P2P start denotes there's 1 ad and n discoverers
     */
    public static final Strategy STRATEGY = Strategy.P2P_STAR;

    //our handler for nearby connections
    private ConnectionsClient connectionsClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertiser);
        //////test //////////
    }

    private void startAdvertising() {
        AdvertisingOptions advertisingOptions =
                new AdvertisingOptions.Builder().setStrategy(STRATEGY).build();

        connectionsClient.startAdvertising(
                CLIENT_NAME,
                SERVICE_ID,
                connectionLifecycleCallback,
                advertisingOptions
        );
//        Nearby.getConnectionsClient(this)
//                .startAdvertising(
//                        CLIENT_NAME,
//                        SERVICE_ID,
//                        mConnectionLifecycleCallback,
//                        advertisingOptions
//                );
    }

    private final ConnectionLifecycleCallback connectionLifecycleCallback =
            new ConnectionLifecycleCallback() {

                @Override
                public void onConnectionInitiated(@NonNull String endPointId, @NonNull ConnectionInfo connectionInfo) {
                    //automatically accept the connection on both sides.
                    Log.i(TAG, "onConnectionInitiated: Accepting Connection");

                    connectionsClient.acceptConnection(endPointId, mPayloadCallback);


                }

                @Override
                public void onConnectionResult(@NonNull String endPointId, @NonNull ConnectionResolution result) {
                    if (result.getStatus().isSuccess()) {

                        Log.i(TAG, "onConnectionResult: connection successful");

                    } else {
                        Log.i(TAG, "onConnectionResult: connection failed");
                    }

                }

                @Override
                public void onDisconnected(@NonNull String s) {
                    Log.i(TAG, "onDisconnected: disconnected fromt he opponent");
                }

                ;

                private final PayloadCallback mPayloadCallback =
                        new PayloadCallback() {
                            @Override
                            public void onPayloadReceived(@NonNull String s, @NonNull Payload payload) {
                                //a new payload is being sent over
                            }

                            @Override
                            public void onPayloadTransferUpdate(@NonNull String s, @NonNull PayloadTransferUpdate payloadTransferUpdate) {
                                //payload progress has updated
                            }
                        };
            };
}