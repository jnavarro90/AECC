package com.aecc.aecc.Controlador.PreguntasEncuestas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aecc.aecc.Controlador.Login;
import com.aecc.aecc.Controlador.Surveys;
import com.aecc.aecc.Modelo.RegistroEncuesta;
import com.aecc.aecc.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EncuestaEstadoFisico extends AppCompatActivity {

    private final String TAG = EncuestaEstadoFisico.class.getSimpleName();
    private int tipo_encuesta;
    private RegistroEncuesta mRegEncuesta;

    @OnClick(R.id.b_confirmar_estado_fisico) void OnClick(){
        Intent intent = new Intent(this, Surveys.class);
        mRegEncuesta.setEstadoFisico("En forma");
        Log.d(TAG, mRegEncuesta.serializar());
        setContentView(R.layout.activity_surveys);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta_estado_fisico);
        ButterKnife.bind(this);
        mRegEncuesta = new RegistroEncuesta();
        Intent intent = getIntent();
        mRegEncuesta = (RegistroEncuesta)intent.getSerializableExtra("encuesta");
        tipo_encuesta = intent.getIntExtra("tipo_encuesta", -1);
        //Si ocurre un error vuelve a la pantalla de Mis encuestas
        if(tipo_encuesta == -1){
            //Toast error
            Toast.makeText(this, R.string.error_tipo_encuesta, Toast.LENGTH_LONG);
            setContentView(R.layout.activity_surveys);
            startActivity(new Intent(this, Surveys.class));
        }
    }
}
