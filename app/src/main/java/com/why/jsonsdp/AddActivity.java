package com.why.jsonsdp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    ProgressDialog pd;
    private EditText editTextNama, editTextAlamat, editTextUsername, editTextPassword;
    private Button buttonAddSave;

    public void saveAnggota(final String nama,
                            final String alamat,
                            final String username,
                            final String password) {
        Thread background = new Thread(new Runnable() {

            // membuat Handler untuk menerima pesan ketika selesai
            private final Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    String aResponse = msg.getData().getString("message");
                    // String cek = "Login Sukses";
                    Toast.makeText(
                            getBaseContext(),
                            "Data Anggota Tersimpan " + aResponse,
                            Toast.LENGTH_SHORT).show();

                    pd.dismiss();
                    //getdata();
                    finish();
                    AnggotaActivity.obj.getdata();
                    // btnloginlogin.setEnabled(true);
                }
            };

            // program yang dijalankan ketika thread background berjalan
            public void run() {
                String SetServerString = "";

                try {
                    HttpRequest req = new HttpRequest("http://triwahyuprasetyo.xyz/addanggota.php");
                    //GET
                    //req.prepare().sendAndReadString();
                    //POST
                    //req.preparePost().withData("name=Bubu&age=29").send();
                    //POST
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("nama", nama);
                    params.put("alamat", alamat);
                    params.put("username", username);
                    params.put("password", password);
                    //edcari.getText().toString());
                    //req.preparePost().withData(params).sendAndReadJSON();
                    //req.preparePost().withData(params).sendAndReadString();
                    //SetServerString = req.preparePost().withData("name=123&age=29").sendAndReadString(); //req.preparePost().withData(params).sendAndReadString();
                    SetServerString = req.preparePost().withData(params).sendAndReadString();

                } catch (Exception e) {

                }

                Message msgObj = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("message", SetServerString);
                msgObj.setData(b);
                handler.sendMessage(msgObj);
            }

        });
        // Start Thread
        pd = ProgressDialog.show(AddActivity.this, "Please Wait",
                "Connecting..", true);
        // btnloginlogin.setEnabled(false);

        background.start(); // memanggil thread background agar start
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonAddSave = (Button) findViewById(R.id.buttonAddSave);
        buttonAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAnggota(editTextNama.getText().toString(), editTextAlamat.getText().toString(), editTextUsername.getText().toString(), editTextPassword.getText().toString());
            }
        });
    }
}
