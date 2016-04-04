package com.aecc.aecc.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aecc.aecc.Alertas.AlertPinDialog;
import com.aecc.aecc.Modelo.Patient;
import com.aecc.aecc.Modelo.WebService;
import com.aecc.aecc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity{


    private final String TAG = Login.class.getSimpleName();
    private WebService mWebServicePaciente;
    private Patient mPatient;

    @Bind(R.id.etUsuario) EditText mEditUsuario;
    @Bind(R.id.etPass) EditText mEditPass;
    @Bind(R.id.tvRecuperarPass) TextView mRecuperarPass;
    @Bind(R.id.cbConectado) CheckBox mConectado;
    @OnClick(R.id.bLogin) void onClick() {

        if(isEmpty(mEditPass) || isEmpty(mEditUsuario)) {
            Toast.makeText(this, R.string.error_faltan_campos, Toast.LENGTH_SHORT).show();
        }else{
            String json = "{\"nombre_usuario\":\"" + mEditUsuario.getText().toString() +
                    "\",\"password\":\"" + mEditPass.getText().toString()+ "\"}";
            JSONObject datos_recibidos = mWebServicePaciente.post(getString(R.string.metodo_validar), json, " ");
            if(datos_recibidos.has("error")){
                Log.e(TAG, "Ha ocurrido un error al recibir los datos");
            }else{
                mPatient = new Patient();
                login(datos_recibidos);
            }
        }
    }

    private void login(JSONObject datos_recibidos) {

        try {
            mPatient.setUsuario(datos_recibidos.getString("nombre_usuario"));
            mPatient.setNombre(datos_recibidos.getString("nombre"));
            mPatient.setApellido1(datos_recibidos.getString("apellido1"));
            mPatient.setApellido2(datos_recibidos.getString("apellido2"));
            mPatient.setDni(datos_recibidos.getString("dni"));
            mPatient.setEmail(datos_recibidos.getString("email"));
            mPatient.setIdTerapeuta(datos_recibidos.getInt("idTerapeuta"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_home);
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("datos_usuario", (Serializable) mPatient);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mWebServicePaciente = new WebService(getString(R.string.url_pacientes));
        ButterKnife.bind(this);

    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    private void alertUserAboutPin(){
        AlertPinDialog dialog = new AlertPinDialog();
        dialog.show(getFragmentManager(), "dialog");
    }

}
