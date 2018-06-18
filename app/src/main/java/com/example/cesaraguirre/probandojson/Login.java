package com.example.cesaraguirre.probandojson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Login extends AppCompatActivity {
    private String url = "https://lawebdecesar.000webhostapp.com/probando/logUsr.php?usr=";
    public String token_usuario;
    public ProgressBar progressBar;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        progressBar = findViewById(R.id.progressBar_login);
    }
    public void logearte (View view){
        final EditText usuario = findViewById(R.id.txb_usuario);
        final EditText pass = findViewById(R.id.tbx_password);
        progressBar.setVisibility(View.VISIBLE);
        String compleUrl = url + usuario.getText() + "&pass=" + pass.getText();
        RequestQueue res = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, compleUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (!response.isNull("error")){
                        JSONObject responceJson = response.getJSONObject("error");
                        String repuesta = responceJson.get("id_usuario").toString();
                        if (repuesta.equals("")){
                            Toast.makeText(context,"Error Vuelva a Intentar",Toast.LENGTH_SHORT).show();
                            usuario.setText("");
                            pass.setText("");
                            progressBar.setVisibility(View.INVISIBLE);
                        }else {
                            Toast.makeText(context,"Logueado",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context,"Usuario o Contraseña Incorrectas",Toast.LENGTH_SHORT).show();
                        usuario.setText("");
                        pass.setText("");
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error Vuelva a Intentar Mas Tarde",Toast.LENGTH_SHORT).show();
             }
        });
        res.add(jsonObjectRequest);
    }

}
