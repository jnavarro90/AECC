package com.aecc.aecc.Controlador.PreguntasEncuestas;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.aecc.aecc.Controlador.Surveys;
import com.aecc.aecc.Modelo.RegistroEncuesta;
import com.aecc.aecc.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EncuestaDolor extends AppCompatActivity {

    private int tipo_encuesta;
    private RegistroEncuesta mRegEncuesta;

    @Bind(R.id.rb_dolor_si)
    RadioButton mDolorSi;

    @Bind(R.id.rb_dolor_no)
    RadioButton mDolorNo;

    @OnClick(R.id.b_confirmar_dolor) void OnClick(){
        Intent intent = new Intent(this, EncuestaDolor.class);
        if(mDolorSi.isChecked()){
            intent = new Intent(this, EncuestaNivelDolor.class);
            mRegEncuesta.setDolor(true);
            setContentView(R.layout.activity_encuesta_nivel_dolor);

        }else if(mDolorNo.isChecked()){
            intent = new Intent(this, EncuestaSintomas.class);
            mRegEncuesta.setDolor(false);
            setContentView(R.layout.activity_encuesta_sintomas);
        }else{
            Toast.makeText(this, "No has elegido ninguna opción", Toast.LENGTH_LONG);
        }

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
        setContentView(R.layout.activity_encuesta_dolor);
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
