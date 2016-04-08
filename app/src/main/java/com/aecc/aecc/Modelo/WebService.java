package com.aecc.aecc.Modelo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebService {
    public static final String TAG = WebService.class.getSimpleName();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private String mUrl;
    private double mLatitud;
    private double mLongitud;
    private String mIdioma;
    private String mUnidad;
    private String mApiKey;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private Request mRequest;
    private JSONObject mRespuestaWS;

    public WebService(String url){
        mUrl = url;
        mRespuestaWS = new JSONObject();
    }

    public WebService(String url, double latitud, double longitud, String idioma, String unidad, String apiKey){
        mUrl = url;
        mLatitud = latitud;
        mLongitud = longitud;
        mIdioma = idioma;
        mUnidad = unidad;
        mApiKey = apiKey;
        mRespuestaWS = new JSONObject();
    }

    public JSONObject get() {
         mRequest = new Request.Builder()
                 .url(mUrl)
                 .get()
                 .build();

        //Call se hace para que sea asincrona al hilo del main
        Call call = mOkHttpClient.newCall(mRequest);
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
        return new JSONObject();
    }

    public JSONObject post(String metodo, String json, String parametros) {
        RequestBody body = RequestBody.create(JSON, json);
        mRequest = new Request.Builder()
                .url(mUrl+ metodo)
                .post(body)
                .build();

        Response response = null;
        try {
            response = mOkHttpClient.newCall(mRequest).execute();

            if (response.code() == 404){
                mRespuestaWS.put("error", 404);
            }else {
                //Tenemos que asignarlo a una variable global para salir del onResponse

                mRespuestaWS = new JSONObject(response.body().string());
                Log.d(TAG, mRespuestaWS.toString());

            }
        } catch (IOException e) {
            Log.e(TAG, "Exception caugth", e);
        } catch (JSONException e) {
            Log.e(TAG, "Exception caugth", e);
        }

        //Devolvemos la respuesta del webservice en formato JSON
        return mRespuestaWS;
    }
}
