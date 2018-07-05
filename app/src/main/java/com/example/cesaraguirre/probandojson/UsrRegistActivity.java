package com.example.cesaraguirre.probandojson;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UsrRegistActivity extends AppCompatActivity {

    private String urlExt = "https://lawebdecesar.000webhostapp.com/probando/extUsr.php?usr=";
    private String urlExtID = "https://lawebdecesar.000webhostapp.com/probando/extUsr.php?IDusr=";
    private String token_usuario;
    private ProgressBar progressBar;
    private Context context;
    public boolean consulta1 , consulta2;
    private boolean libreNombre, libreIdUsr , error , error2;
    private String respuesta1 ="", respuesta2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usr_regist);
        progressBar = findViewById(R.id.progressBarReg);
        context = this;
    }

    private String crearToken (){
        String token = "";
        char [] caracteres = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9'};
        for ( int i = 0; i < 10;i++){
            token += caracteres[(int)(Math.random()*caracteres.length)];
        }
        return token;
    }

    public void probando (View view) throws InterruptedException {
        token_usuario = crearToken();
        Toast.makeText(this,token_usuario,Toast.LENGTH_SHORT).show();
        EditText pass1 = findViewById(R.id.newContra);
        EditText pass2 = findViewById(R.id.confContra);
        EditText newusr = findViewById(R.id.newUser);
        if (!pass1.getText().toString().equals(pass2.getText().toString())) {
            Toast.makeText(this, "Contraseñas No Coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
        existeidToken(token_usuario);
        existeUsuario(newusr.getText().toString());
        while (!consulta1 && !consulta2){

        }
        if (error || error2){
            pass1.setText("");
            pass2.setText("");
            newusr.setText("");
            return;
        }
        if (!libreNombre){
            pass1.setText("");
            pass2.setText("");
            newusr.setText("");
            return;
        }
        while (libreIdUsr){
            token_usuario = crearToken();
            existeidToken(token_usuario);
            while (consulta2){
                Thread.sleep(1000);
            }
        }
        Toast.makeText(this,"Se Creo Usuario",Toast.LENGTH_LONG).show();
    }

    public void existeUsuario (String usuario){
        this.libreNombre = false ;
        consulta1 = false;
        String compleUrl = urlExt + usuario;
        RequestQueue res = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, compleUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (!response.isNull("error")){
                        JSONObject responceJson = response.getJSONObject("error");
                        String repuesta = responceJson.get("nick").toString();
                        if (repuesta.equals("libre")){
                            libreNombre = true;
                            Toast.makeText(context,"Error Vuelva a Intentar",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Usuario Ya existente",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show();
                        error = true;
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    error = true;
                }
                consulta1 = true;
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError errorVolley) {
                Toast.makeText(context,"Error Vuelva a Intentar Mas Tarde",Toast.LENGTH_SHORT).show();
                consulta1 = true;
                error = true;
            }
        });
        res.add(jsonObjectRequest);
    }
    public void existeidToken (String idToken){
        this.libreNombre = false ;
        consulta2 = false;
        String compleUrl = urlExtID + idToken;
        RequestQueue res = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, compleUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (!response.isNull("error")){
                        JSONObject responceJson = response.getJSONObject("error");
                        String repuesta = responceJson.get("id_usuario").toString();
                        if (repuesta.equals("libre")){
                            libreIdUsr = true;
                            Toast.makeText(context,"Error Vuelva a Intentar",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show();
                        error2 = true;
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    error2 = true;
                }
                consulta2 = true;
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError errorVolley) {
                Toast.makeText(context,"Error Vuelva a Intentar Mas Tarde",Toast.LENGTH_SHORT).show();
                consulta2 = true;
                error2 = true;
            }
        });
        res.add(jsonObjectRequest);
    }
}
