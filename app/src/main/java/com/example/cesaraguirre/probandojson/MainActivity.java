package com.example.cesaraguirre.probandojson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    private String url1 = "https://lawebdecesar.000webhostapp.com/probando/prob3.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



  //      String json = getJSON("https://lawebdecesar.000webhostapp.com/probando/prob3.php");
   //     TextView label = (TextView) findViewById(R.id.label);
   //     if (json != null){
    //        label.setText(json);
     //   }
   /*     try {
            probando();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final TextView mTextView = (TextView) findViewById(R.id.text);
        // ...

        // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="http://www.google.com";

        // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }



    public void probando () throws IOException {
  //      ConnectivityManager connMgr = (ConnectivityManager)
  //              getSystemService(Context.CONNECTIVITY_SERVICE);
  //      NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
   //     if (networkInfo != null && networkInfo.isConnected()) {
            // Operaciones http

        URL url = new URL("https://lawebdecesar.000webhostapp.com/probando/prob3.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();
        InputStream stream = urlConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        TextView label = (TextView) findViewById(R.id.label);
        label.setText(buffer.toString());
      /*  try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        }
            // Acciones a realizar con el flujo de datos}
        finally{
                urlConnection.disconnect();
            }
        }
*/
    }

    public void aBoton (View view){
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


    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }



    // intento nuevo



}
