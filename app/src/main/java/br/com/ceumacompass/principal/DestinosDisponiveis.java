package br.com.ceumacompass.principal;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Marcos on 03/05/2016.
 */
public class DestinosDisponiveis{

    private Spinner spn;
    private Principal prc;
    private String destinos[] = {"CEUMATEC", "RH", "LABORATÓRIOS", "SUPORTE"};
    int lugar;

    public DestinosDisponiveis(Spinner spn, Principal prc){
        this.spn = spn;
        this.prc = prc;
    }

    public void opcoes() {
        ArrayAdapter opcoes = new ArrayAdapter<String>(prc, android.R.layout.simple_expandable_list_item_1, destinos);
        this.spn.setAdapter(opcoes);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        lugar = spn.getSelectedItemPosition();
                        break;
                    case 1:
                        lugar = spn.getSelectedItemPosition();
                        break;
                    case 2:
                        lugar = spn.getSelectedItemPosition();
                        break;
                    case 3:
                        lugar = spn.getSelectedItemPosition();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public int getDestination(){
        return lugar;
    }
}
