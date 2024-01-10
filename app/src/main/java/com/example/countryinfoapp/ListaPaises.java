package com.example.countryinfoapp;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaPaises {
    String Pais;
    String Pic;
  public String getPais() {
            return Pais;
        }
        public void setPais(String pais) {Pais = pais;}

        public String getPic() {
            return Pic;
        }
        public void setPic(String pic) {
            Pic = pic;
        }

    public ListaPaises(JSONObject a) throws JSONException {
        Pais =  a.getString("Name").toString();
       // Pic = "http://www.geognos.com/api/en/countries/flag/{alpha2code}.png" + a.getString("iso2").toString();
        Pic = "http://www.geognos.com/api/en/countries/flag/" + a.getString("iso2").toString() + ".png";
    }

    public static ArrayList<ListaPaises> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<ListaPaises> lstListaPaises = new ArrayList<>();

        for (int i = 0; i < datos.length() && i<20; i++) {
            lstListaPaises.add(new ListaPaises(datos.getJSONObject(i)));
        }
        return lstListaPaises;
    }

}
