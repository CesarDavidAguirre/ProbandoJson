package com.example.cesaraguirre.probandojson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private RequestQueue res;
    private String url = "https://lawebdecesar.000webhostapp.com/probando/prob3.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    //probando git

   /* public void aBoton (View view){
        EditText entrada = (EditText) findViewById(R.id.entrada);
        String url = url1+"?usr="+ entrada.getText().toString();
        final TextView txtShowTextResult1 = (TextView) findViewById(R.id.label);
        res = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    StringBuilder formato = new StringBuilder();
                    JSONArray responceJson = response.getJSONArray("hola");
                    for (int i = 0 ; i < responceJson.length();i++){
                        formato.append(responceJson.getJSONObject(i).get("descripcion")+"\n");
                    }
                    if (responceJson.length() == 0) {
                        txtShowTextResult1.setText("Usuario sin Comidas Cargadas");
                    } else {
                        txtShowTextResult1.setText("Comidas: " + formato);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtShowTextResult1.setText("An Error occured while making the request");
            }
        });
        res.add(jsonObjectRequest);
    }
*/
}
