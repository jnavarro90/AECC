package com.aecc.aecc.Controlador.PreguntasEncuestas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aecc.aecc.Controlador.Surveys;
import com.aecc.aecc.Modelo.RegistroEncuesta;
import com.aecc.aecc.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EncuestaSleep extends AppCompatActivity {

    private int tipo_encuesta;
    private RegistroEncuesta mRegEncuesta;

    @OnClick(R.id.b_confirmar_sleep) void OnClick(){
        Intent intent;
        intent = new Intent(this, EncuestaDolor.class);
        setContentView(R.layout.activity_encuesta_dolor);
        mRegEncuesta.setSleep("Bueno");
        if(tipo_encuesta == 1) {
            intent.putExtra("tipo_encuesta", 1);
        }else{
            intent.putExtra("tipo_encuesta", 2);
        }
        intent.putExtra("encuesta", mRegEncuesta);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta_sleep);
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
