package com.why.jsonsdp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        anggotaService.getStreams("", new Callback<List<AnggotaPojo>>() {

            @Override
            public void success(List<AnggotaPojo> quotes, Response response) {
                for (AnggotaPojo quotePojo : quotes) {
                    Log.d("SDP", "Anggota :: " + quotePojo.getId());
                    Log.d("SDP", "Anggota :: " + quotePojo.getNama());
                    Log.d("SDP", "Anggota :: " + quotePojo.getAlamat());
                    Log.d("SDP", "Anggota :: " + quotePojo.getUsername());
                    Log.d("SDP", "Anggota :: " + quotePojo.getPassword());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
