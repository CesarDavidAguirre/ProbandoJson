package com.example.cesaraguirre.probandojson;

import android.content.Context;
import android.content.Intent;
import android.support.v4.net.ConnectivityManagerCompat;
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
import com.example.cesaraguirre.probandojson.retrofit.BackgroundSincrono;
import com.example.cesaraguirre.probandojson.retrofit.MyApiAdapter;
import com.example.cesaraguirre.probandojson.retrofit.io.ResponceError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class UsrRegistActivity extends AppCompatActivity {

    private String urlExt = "https://lawebdecesar.000webhostapp.com/probando/extUsr.php?usr=";
    private String urlExtID = "https://lawebdecesar.000webhostapp.com/probando/extIDUsr.php?IDusr=";
    private String token_usuario, usrNew , passNew;
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
    boolean paso = false;

    public void prob (View view) throws InterruptedException {
         EditText newusr = findViewById(R.id.newUser);

         //Call<ResponceError> call =MyApiAdapter.getApiService().getIdExist(newusr.getText().toString());


      /*   try {
             Call<ResponceError> call = MyApiAdapter.getApiService().getIdExist(newusr.getText().toString());
             ResponceError error = call.execute().body();

             if (error.isError()){
                 Toast.makeText(context,"True",Toast.LENGTH_LONG).show();
             }else{
                 Toast.makeText(context,"False",Toast.LENGTH_LONG).show();
             }
             paso = true;

         }catch (IOException e){
            e.printStackTrace();
         }

        consulta1 = false;
        Intent intent = new Intent(this, BackgroundSincrono.class);
        intent.putExtra("nombre",newusr.getText().toString());
        startService(intent);



             Toast.makeText(context, "paso", Toast.LENGTH_LONG).show();
           // call.enqueue(new ErrorCallbak());
 */


    }
    class ErrorCallbak implements Callback<ResponceError> {

        @Override
        public void onResponse(Call<ResponceError> call, retrofit2.Response<ResponceError> response) {
            if (response.isSuccessful()){
                ResponceError error = response.body();
                if (error.isError() == 0){
                    Toast.makeText(context,"True",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"False",Toast.LENGTH_LONG).show();
                }
                paso = true;
            }
        }

        @Override
        public void onFailure(Call<ResponceError> call, Throwable t) {
            Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
        }
    }

    public void probando (View view) throws InterruptedException {
        EditText pass1 = findViewById(R.id.newContra);
        EditText pass2 = findViewById(R.id.confContra);
        EditText newusr = findViewById(R.id.newUser);
        if (!pass1.getText().toString().equals(pass2.getText().toString())) {
            Toast.makeText(this, "Contraseñas No Coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
        passNew = pass1.getText().toString();
        usrNew = newusr.getText().toString();
        regUsr();
       /* existeidToken(token_usuario);
        existeUsuario(newusr.getText().toString());
        Thread.sleep(5000);
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
        */

      //  Call<ResponceError> call =MyApiAdapter.getApiService().getRegUsr(newusr.getText().toString());

        //Toast.makeText(this,"Se Creo Usuario",Toast.LENGTH_LONG).show();

    }
    public void regUsr (){
        token_usuario = crearToken();
       // token_usuario = probtoken();
        Call<ResponceError> call =MyApiAdapter.getApiService().getRegUsr(passNew,token_usuario,usrNew);
        call.enqueue(new Callback<ResponceError>() {
            @Override
            public void onResponse(Call<ResponceError> call, retrofit2.Response<ResponceError> response) {
                if (response.isSuccessful()) {
                    ResponceError respuesta = response.body();
                    switch (respuesta.isError()) {
                        case 0:
                            Toast.makeText(context,"Se Registro usuario",Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            regUsr();
                            break;
                        case 2:
                            Toast.makeText(context,"Usuario Existente",Toast.LENGTH_LONG).show();
                            break;
                        case 3:
                            Toast.makeText(context,"ERROR: Vuelva a Intentar Mas Tarde",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponceError> call, Throwable t) {
                Toast.makeText(context,"ERROR: Vuelva a Intentar",Toast.LENGTH_LONG).show();
            }
        });

    }
    int cont =0;
    public String probtoken (){
        if (cont == 3){
            cont = 0;
            return "abcdefg";
        }
        cont ++;
        return "aaaaa";
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
