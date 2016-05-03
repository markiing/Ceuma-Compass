package br.com.ceumacompass.asynctask;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;

import br.com.ceumacompass.principal.Principal;

/**
 * Created by Marcus on 03/05/2016.
 */
public class BitmapLoaderTask extends AsyncTask<String, Void, Bitmap> {

    private Principal p;

    public BitmapLoaderTask(Principal p){
        this.p = p;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        AssetManager assets = p.getAssets();
        Bitmap bmp = null;
        try{
            bmp = BitmapFactory.decodeStream(assets.open(params[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        p.setImageBitmap(result);
    }
}
