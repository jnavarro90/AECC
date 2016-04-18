package com.aecc.aecc.Controlador.PreguntasEncuestas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.aecc.aecc.Controlador.Surveys;
import com.aecc.aecc.Modelo.RegistroEncuesta;
import com.aecc.aecc.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EncuestaAnimo extends AppCompatActivity {

    private int tipo_encuesta;
    private RegistroEncuesta mRegEncuesta;


    @Bind(R.id.tv_header_animo)
    TextView m_header_animo;
    @OnClick (R.id.b_confirmar_animo) void OnClick(){
        Intent intent;
        switch(tipo_encuesta){
            case 1:
                mRegEncuesta.setAnimo("Animado");
                intent = new Intent(this, EncuestaSleep.class);
                setContentView(R.layout.activity_encuesta_sleep);
                intent.putExtra("tipo_encuesta", 1);
                intent.putExtra("encuesta", mRegEncuesta);
                startActivity(intent);
                break;
            case 2:
                mRegEncuesta.setAnimo("Animado");
                intent = new Intent(this, EncuestaActividades.class);
                setContentView(R.layout.activity_encuesta_actividades);
                intent.putExtra("tipo_encuesta", 2);
                intent.putExtra("encuesta", mRegEncuesta);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta_animo);
        ButterKnife.bind(this);
        mRegEncuesta = new RegistroEncuesta();
        Intent intent = getIntent();
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
