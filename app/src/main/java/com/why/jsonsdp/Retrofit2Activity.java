package com.why.jsonsdp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Activity extends AppCompatActivity implements View.OnClickListener {
    private ListView listAnggota;
    private ProgressDialog pd;
    private Button buttonAddAnggota;
    private String[] daftarNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        setTitle("Retrofit 2");

        buttonAddAnggota = (Button) findViewById(R.id.button_addanggota_retrofit2);
        buttonAddAnggota.setOnClickListener(this);
        listAnggota = (ListView) findViewById(R.id.list_anggota_retrofit2);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonAddAnggota.getId()) {
            Intent i = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "OnResume", Toast.LENGTH_SHORT).show();
        Log.i("SDP RETROFIT ", "ON RESUME");
        retrieveAnggota();
    }

    private void retrieveAnggota() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://triwahyuprasetyo.xyz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnggotaService service = retrofit.create(AnggotaService.class);

        Call<AnggotaWrapper> call = service.listAnggota();
        call.enqueue(new Callback<AnggotaWrapper>() {
            @Override
            public void onResponse(Call<AnggotaWrapper> call, Response<AnggotaWrapper> response) {
                Toast.makeText(getApplicationContext(), "Success Retrieve: " + response.code() + "/" + response.message(), Toast.LENGTH_SHORT).show();
                daftarNama = new String[response.body().getAnggota().size()];
                int i = 0;
                for (AnggotaWrapper.Anggota anggota : response.body().getAnggota()) {
                    Log.d("SDP", "Anggota :: " + anggota.getId());
                    Log.d("SDP", "Anggota :: " + anggota.getNama());
                    Log.d("SDP", "Anggota :: " + anggota.getAlamat());
                    Log.d("SDP", "Anggota :: " + anggota.getUsername());
                    Log.d("SDP", "Anggota :: " + anggota.getPassword());
                    Log.d("SDP", "=======================================");
                    daftarNama[i] = anggota.getNama();
                    i++;
                }
                listAnggota.setAdapter(new ArrayAdapter(Retrofit2Activity.this, android.R.layout.simple_list_item_1, daftarNama));
            }

            @Override
            public void onFailure(Call<AnggotaWrapper> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail Retrieve", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
