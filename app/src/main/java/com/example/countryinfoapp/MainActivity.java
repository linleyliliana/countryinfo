package com.example.countryinfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.countryinfoapp.WebServices.Asynchtask;
import com.example.countryinfoapp.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        //WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/all.json",
          //      datos, MainActivity.this, MainActivity.this);
        //ws.execute("GET");

        try {
            // Código de la solicitud HTTP aquí
            WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/all.json",
                    datos, MainActivity.this, MainActivity.this);
            ws.execute("GET");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("JsonResponse", "Error de E/S al realizar la solicitud HTTP: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JsonResponse", "Error general al realizar la solicitud HTTP: " + e.getMessage());
        }





    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.d("JsonResponse", result);
        ArrayList<ListaPaises> lstListaPaises = new ArrayList<ListaPaises>();

        JSONObject lista = new JSONObject(result);
        JSONArray JSONlista = lista.getJSONArray("data");

        lstListaPaises = ListaPaises.JsonObjectsBuild(JSONlista);
        Adaptador adaptadorLugaresTuristicos
                = new Adaptador(this, lstListaPaises);
        ListView lstOpciones = (ListView)findViewById(R.id.lstListaPaises);
        lstOpciones.setAdapter(adaptadorLugaresTuristicos);
    }
}

