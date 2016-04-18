package com.aecc.aecc.Controlador.PreguntasEncuestas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aecc.aecc.Controlador.Surveys;
import com.aecc.aecc.Modelo.RegistroEncuesta;
import com.aecc.aecc.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EncuestaNivelDolor extends AppCompatActivity {

    private int tipo_encuesta;
    private int mProgress = 0;
    private RegistroEncuesta mRegEncuesta;
    @Bind(R.id.sb_nivel_dolor)
    SeekBar msbNivelDolor;
    @Bind(R.id.tv_seek_bar)
    TextView mtvSeekBar;
    @OnClick(R.id.b_confirmar_nivel_dolor) void OnClick(){

        Intent intent;
        intent = new Intent(this, EncuestaPartesDolor.class);
        setContentView(R.layout.activity_encuesta_partes_dolor);
        mRegEncuesta.setNivelDolor(mProgress);
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
        setContentView(R.layout.activity_encuesta_nivel_dolor);
        ButterKnife.bind(this);

        mtvSeekBar.setText("Covered: " + msbNivelDolor.getProgress() + "/" + msbNivelDolor.getMax());
        msbNivelDolor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                mProgress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mtvSeekBar.setText("Covered: " + mProgress + "/" + seekBar.getMax());
            }
        });
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
