package com.example.cesaraguirre.probandojson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class UsrRegistActivity extends AppCompatActivity {

    private String url = "https://lawebdecesar.000webhostapp.com/probando/logUsr.php?usr=";
    public String token_usuario;
    private ProgressBar progressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usr_regist);
    }


}
