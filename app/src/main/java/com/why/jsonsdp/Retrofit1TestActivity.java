package com.why.jsonsdp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Retrofit1TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit1_test);

        RestAdapter restAdapter = new RestAdapter.Builder()
                //.setEndpoint("http://www.cheesejedi.com")
                .setEndpoint("http://triwahyuprasetyo.xyz")
                .build();

        AnggotaInterface anggotaService = restAdapter.create(AnggotaInterface.class);

        anggotaService.getDataAnggota(new Callback<AnggotaWrapper>() {

            @Override
            public void success(AnggotaWrapper anggotaWrapper, Response response) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                for (AnggotaWrapper.Anggota anggota : anggotaWrapper.getAnggota()) {
                    Log.d("SDP", "Anggota :: " + anggota.getId());
                    Log.d("SDP", "Anggota :: " + anggota.getNama());
                    Log.d("SDP", "Anggota :: " + anggota.getAlamat());
                    Log.d("SDP", "Anggota :: " + anggota.getUsername());
                    Log.d("SDP", "Anggota :: " + anggota.getPassword());
                    Log.d("SDP", "=======================================");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Errorr", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("SDP", "Error :: " + error.getMessage());
            }
        });
    }
}
