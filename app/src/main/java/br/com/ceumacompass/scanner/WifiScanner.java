package br.com.ceumacompass.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.com.ceumacompass.principal.Principal;

/**
 * Created by Marcos on 30/04/2016.
 */
public class WifiScanner extends BroadcastReceiver {
    private static final String TAG = "WifiScanReceiver";
    Principal main;

    public WifiScanner(Principal main){
        super();
        this.main = main;
    }
    public void onReceive(Context c, Intent i){
        List<ScanResult> results = main.wifi.getScanResults();
        ScanResult bestSignal = null;
        for(ScanResult result: results){

            if(bestSignal==null || WifiManager.compareSignalLevel(bestSignal.level,result.level) < 0){

                if(result.BSSID.equals("2c:5d:93:07:89:68") || result.BSSID.equals("2c:5d:93:07:bc:98") || result.BSSID.equals("2c:5d:93:07:0c:f8")) {
                    if (result.BSSID.equals("2c:5d:93:07:89:68")) {
                        //MAC DO LABORATORIO
                        if (Math.abs(result.level) <= 60 && Math.abs(result.level) >= 30) {
                            main.destino.setText("ESTÁ NA ÁREA DOS LABORATÓRIO");
                            main.imgUser.setX(main.imgPlace.getX());
                            main.imgUser.setY(main.imgPlace.getY());
                        } else {
                            main.destino.setText("");
                        }
                        result.BSSID = "Laboratório 3 - CEUMA";
                    } else if (result.BSSID.equals("2c:5d:93:07:bc:98")) {

                        result.BSSID = "Ceuma II";
                    } else if (result.BSSID.equals("2c:5d:93:07:0c:f8")) {
                        if(Math.abs(result.level) <= 60 && Math.abs(result.level) >= 50){
                            main.destino.setText("ESTÁ NA ÁREA DE RECURSOS HUMANOS");
                            main.imgUser.setX(main.rh.getX());
                            main.imgUser.setY(main.rh.getY());
                        }else{
                            main.destino.setText("");
                        }
                        result.BSSID = "Escadaria";
                    }
                    bestSignal = result;
                    main.text.setText(""+bestSignal.SSID+"\n"+bestSignal.level+"\n"+bestSignal.BSSID.toString());
                }else{
                    bestSignal = null;
                }
            }

        }

        // Toast.makeText(main, message,Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onReceive() message: "+ main.text);
    }
}
