package com.aecc.aecc.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aecc.aecc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity{


    public static final String TAG = Login.class.getSimpleName();
    WebService webServicePaciente = new WebService("http://192.168.1.202:8888/aecc/android/prueba");


    @Bind(R.id.etUsuario) EditText mEditUsuario;
    @Bind(R.id.etPass) EditText mEditPass;
    @OnClick(R.id.bLogin) void onClick() {
        Call call = webServicePaciente.obtenerWebService();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    
                    Log.v(TAG, jsonData);

                } catch (IOException e) {
                    Log.e(TAG, "Exception caugth", e);
                }
            }
        });
        if(isEmpty(mEditPass) || isEmpty(mEditUsuario)) {
            Toast.makeText(this,"Debes introducir todos los campos.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Usuario: " + mEditUsuario.getText().toString()
                    + "   Pass: " + mEditPass.getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

}
