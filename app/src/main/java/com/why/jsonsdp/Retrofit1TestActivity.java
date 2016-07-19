package com.why.jsonsdp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Retrofit1TestActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewRetrofit;
    private String[] daftarNama;
    private Button buttonIntentAddAnggota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit1_test);

        setTitle("Retrofit 1.9");

        listViewRetrofit = (ListView) findViewById(R.id.listview_retrofit1);
        buttonIntentAddAnggota = (Button) findViewById(R.id.button_intent_add_anggota);
        buttonIntentAddAnggota.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
        Log.i("SDP Retrofit", "On Resume Method");
    }

    private void retrieveData() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://triwahyuprasetyo.xyz")
                .build();
        AnggotaInterface anggotaService = restAdapter.create(AnggotaInterface.class);
        anggotaService.getDataAnggota(new Callback<AnggotaWrapper>() {
            @Override
            public void success(AnggotaWrapper anggotaWrapper, Response response) {
                Toast.makeText(getApplicationContext(), "Retrieve Success", Toast.LENGTH_LONG).show();
                String[] daftarNama = new String[anggotaWrapper.getAnggota().size()];
                int i = 0;
                for (AnggotaWrapper.Anggota anggota : anggotaWrapper.getAnggota()) {
                    Log.d("SDP", "Anggota :: " + anggota.getId());
                    Log.d("SDP", "Anggota :: " + anggota.getNama());
                    Log.d("SDP", "Anggota :: " + anggota.getAlamat());
                    Log.d("SDP", "Anggota :: " + anggota.getUsername());
                    Log.d("SDP", "Anggota :: " + anggota.getPassword());
                    Log.d("SDP", "=======================================");
                    daftarNama[i] = anggota.getNama();
                    i++;
                }
                listViewRetrofit.setAdapter(new ArrayAdapter(Retrofit1TestActivity.this, android.R.layout.simple_list_item_1, daftarNama));
                listViewRetrofit.invalidate();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Retrieve Error", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("SDP", "Error :: " + error.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonIntentAddAnggota.getId()) {
            Intent moveToAddAnggota = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(moveToAddAnggota);
        }
    }
}
