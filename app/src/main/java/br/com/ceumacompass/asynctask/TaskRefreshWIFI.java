package br.com.ceumacompass.asynctask;

import android.os.AsyncTask;
import android.widget.Button;

import br.com.ceumacompass.principal.Principal;

/**
 * Created by Marcos on 30/04/2016.
 */
public class TaskRefreshWIFI extends AsyncTask<Boolean, Integer, Void> {
    private Button botao;
    private Principal main;

    public TaskRefreshWIFI(Button botao, Principal main){
        this.main = main;
        this.botao = botao;
    }
    @Override
    protected Void doInBackground(Boolean... params) {
        boolean b = params[0];
        while(b){
            try{
                Thread.sleep(2500);
                main.wifi.startScan();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        botao.setEnabled(true);
    }


    @Override
    protected void onPreExecute(){
        botao.setEnabled(false);
    }
}
