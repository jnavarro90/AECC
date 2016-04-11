package com.aecc.aecc.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity{


    private final String TAG = Login.class.getSimpleName();
    private AsyncLogin mAuthLogin;
    private Patient mPatient;
    private Clock mClock;

    @Bind(R.id.etUsuario) EditText mEditUsuario;
    @Bind(R.id.etPass) EditText mEditPass;
    @Bind(R.id.tvRecuperarPass) TextView mRecuperarPass;
    @Bind(R.id.cbConectado) CheckBox mConectado;

    @OnClick(R.id.bLogin) void onClick() {

        if(isEmpty(mEditPass) || isEmpty(mEditUsuario)) {
            Toast.makeText(this, R.string.error_faltan_campos, Toast.LENGTH_SHORT).show();
        }else{
            //LLamar a metodo asincrono para que haga el login

            mAuthLogin = new AsyncLogin(mEditUsuario.getText().toString(),mEditPass.getText().toString());
            mAuthLogin.execute((Void) null);

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

        SharedPreferences prefs =
                getSharedPreferences("Settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Patient", mPatient.serializeUser());
        if(mConectado.isChecked()) {
            editor.putLong("LastLogin", mClock.getNowMillis());
            editor.putBoolean("RememberMe", true);
        }else{
            editor.putBoolean("RememberMe", false);
        }
        editor.commit();

        setContentView(R.layout.activity_home);
        startActivity(new Intent(this, Home.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mClock = new Clock();
        SharedPreferences preferencesUser = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        long lastLogin = preferencesUser.getLong("LastLogin", -1);
        Log.d(TAG, String.valueOf(mClock.diferenceInMinutes(lastLogin, mClock.getNowMillis())));
        Log.d(TAG, String.valueOf(preferencesUser.getBoolean("RememberMe", false)));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(preferencesUser.getBoolean("RememberMe", false)){
            mConectado.setChecked(true);
            if(mClock.diferenceInMinutes(lastLogin, mClock.getNowMillis()) < 1){
                SharedPreferences.Editor editor = preferencesUser.edit();
                editor.putLong("LastLogin", mClock.getNowMillis());
                editor.commit();
                setContentView(R.layout.activity_home);
                startActivity(new Intent(this, Home.class));
            }
        }
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    private void alertUserAboutPin(){
        AlertPinDialog dialog = new AlertPinDialog();
        dialog.show(getFragmentManager(), "dialog");
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class AsyncLogin extends AsyncTask<Void, Void, Boolean> {

        private final String mUser;
        private final String mPassword;
        private JSONObject datos_recibidos;

        AsyncLogin(String user, String password) {
            mUser = user;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            WebService auth = new WebService(getString(R.string.url_pacientes));
            String json = "{\"nombre_usuario\":\"" + mUser +
                    "\",\"password\":\"" + mPassword + "\"}";

            datos_recibidos = auth.post(getString(R.string.metodo_validar), json, " ");

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthLogin = null;
            if(datos_recibidos.has("error")){
                Log.e(TAG, "Ha ocurrido un error al recibir los datos");
            }else{
                mPatient = new Patient();
                login(datos_recibidos);
            }

        }

        @Override
        protected void onCancelled() {
            mAuthLogin = null;
        }
    }

}
