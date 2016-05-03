package br.com.ceumacompass.principal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.ceumacompass.asynctask.*;

import java.util.ArrayList;

import br.com.ceumacompass.R;
import br.com.ceumacompass.scanner.WifiScaner;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    /**
     * DECLARAÇÃO DE VARIÁVEIS PARA USOS DIVERSOS
     */
    private static final String TAG = "WifiDemo";
    public WifiManager wifi;
    BroadcastReceiver receiver;
    public TextView text, destino;
    private Button btn;

    /**
     * FIM DAS DECLARAÇÕES DE VARIÁVEIS
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_principal);

        /**
         * RECUPERAÇÃO DOS COMPONENTES DE VISUALIZAÇÃO DENTRO DO LAYOUT
         */
        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        destino = (TextView) findViewById(R.id.destino);

        /**
         * FIM DA RECUPERAÇÃO DE COMPONENTES
         */

        /**
         * EVENTOS DIVERSOS EM CLICK / TOUCH
         */
            btn.setOnClickListener(this);
        /**
         * FIM DOS EVENTOS DIVERSOS EM CLICK / TOUCH
         */

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        /**
         * TRECHO RESPONSÁVEL POR PEGAR TODOS OS PONTOS DE WIFI DISPONÍVEIS
         */
        if (receiver == null)
            receiver = new WifiScaner(this);
        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");
        /**
         * FIM DA RECUPERAÇÃO DE PONTOS DE WIFIS
         */
    }

    @Override
    protected void onStop(){ super.onStop(); }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override

    public void onClick(View v) {
        if(v.getId() == R.id.btn){
            Log.d(TAG, "onCreate() wifi.startScan()");
            //wifi.startScan();
            TaskRefreshWIFI task = new TaskRefreshWIFI(btn, this);
            task.execute(true);
        }
    }
}
