package com.aecc.aecc.Controlador;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebService {
    public static final String TAG = WebService.class.getSimpleName();
    private String mUrl;
    private double mLatitud;
    private double mLongitud;
    private String mIdioma;
    private String mUnidad;
    private String mApiKey;

    public WebService(String url){
        mUrl = url;
    }

    public WebService(String url, double latitud, double longitud, String idioma, String unidad, String apiKey){
        mUrl = url;
        mLatitud = latitud;
        mLongitud = longitud;
        mIdioma = idioma;
        mUnidad = unidad;
        mApiKey = apiKey;
    }

    public Call obtenerWebService() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(mUrl).build();

        Call call = client.newCall(request);
        return call;
    }
}
