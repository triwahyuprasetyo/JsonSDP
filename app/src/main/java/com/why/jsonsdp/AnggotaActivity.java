package com.why.jsonsdp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnggotaActivity extends AppCompatActivity {
    public static AnggotaActivity obj;
    String[] daftarId;
    String[] daftarNama;
    String[] daftarAlamat;
    String[] daftarUsername;
    String[] daftarPassword;
    String[] daftarLatitude;
    String[] daftarLongitude;
    String[] daftarFoto;
    ListView listAnggota;
    ProgressDialog pd;
    private JSONObject jObject;
    private String jsonResult = "";
    private Button buttonAddAnggota, buttonRetrofitTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota);
        getdata();
        obj=this;

        buttonAddAnggota = (Button) findViewById(R.id.buttonAddAnggota);
        buttonAddAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });

        buttonRetrofitTest1 = (Button) findViewById(R.id.buttonRetrofit1Test);
        buttonRetrofitTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Retrofit1TestActivity.class);
                startActivity(i);
            }
        });
    }

    public void getdata() { //final String parameter) {
        Thread background = new Thread(new Runnable() {

            // membuat Handler untuk menerima pesan ketika selesai
            private final Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    String aResponse = msg.getData().getString("message");
                    // String cek = "Login Sukses";
                    Toast.makeText(
                            getBaseContext(),
                            "Response Server : " + aResponse,
                            Toast.LENGTH_LONG).show();
                    try {
                        jObject = new JSONObject(aResponse);
                        JSONArray menuitemArray = jObject.getJSONArray("data");

//                        Log.i("LOG I",menuitemArray.);
                        daftarId = new String[menuitemArray.length()];
                        daftarNama = new String[menuitemArray.length()];
                        daftarAlamat = new String[menuitemArray.length()];
                        daftarUsername = new String[menuitemArray.length()];
                        daftarPassword = new String[menuitemArray.length()];
                        daftarLatitude = new String[menuitemArray.length()];
                        daftarLongitude = new String[menuitemArray.length()];
                        daftarFoto = new String[menuitemArray.length()];
                        for (int i = 0; i < menuitemArray.length(); i++) {
                            daftarId[i] = menuitemArray.getJSONObject(i).getString("id").toString();
                            daftarNama[i] = menuitemArray.getJSONObject(i).getString("nama").toString();
                            daftarAlamat[i] = menuitemArray.getJSONObject(i).getString("alamat").toString();
                            daftarUsername[i] = menuitemArray.getJSONObject(i).getString("username").toString();
                            daftarPassword[i] = menuitemArray.getJSONObject(i).getString("password").toString();
                            daftarLatitude[i] = menuitemArray.getJSONObject(i).getString("latitude").toString();
                            daftarLongitude[i] = menuitemArray.getJSONObject(i).getString("longitude").toString();
                            daftarFoto[i] = menuitemArray.getJSONObject(i).getString("foto").toString();
                        }

                        listAnggota = (ListView) findViewById(R.id.list_anggota);
                        listAnggota.setAdapter(new ArrayAdapter(AnggotaActivity.this, android.R.layout.simple_list_item_1, daftarNama));
                        /*
                        listAnggota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //final String selection = daftar[position]; //.getItemAtPosition(arg2).toString();
                                final int posisi = position;
                                final CharSequence[] dialogitem = {"Edit", "Delete"};
                                AlertDialog.Builder builder = new AlertDialog.Builder(AnggotaActivity.this);
                                builder.setTitle("Pilih ?");
                                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        switch(item){
                                            case 0 :
                                                Intent i = new Intent(getApplicationContext(), EditActivity.class); //KulineryogyaActivity.class);
                                                i.putExtra("id", daftarid[posisi]);
                                                i.putExtra("nama", daftarnama[posisi]);
                                                i.putExtra("alamat", daftaralamat[posisi]);
                                                i.putExtra("telp", daftartelp[posisi]);
                                                startActivity(i);

                                                break;
                                            case 1 :
                                                DeleteData(daftarid[posisi]);
                                                break;
                                        }
                                    }
                                });
                                builder.create().show();
                            }
                        });
                        */
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getBaseContext(), "Gagal",
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    pd.dismiss();
                }
            };

            // program yang dijalankan ketika thread background berjalan
            public void run() {
                String SetServerString = "";

                try {
                    HttpRequest req = new HttpRequest("http://triwahyuprasetyo.xyz/daftaranggota.php");
                    //GET
                    //req.prepare().sendAndReadString();
                    //POST
                    //req.preparePost().withData("name=Bubu&age=29").send();
                    //POST
                    // HashMap<String, String> params=new HashMap<>();
                    // params.put("keyword", ""); //edcari.getText().toString());
//                    params.put("age", "29");
                    //req.preparePost().withData(params).sendAndReadJSON();
                    //req.preparePost().withData(params).sendAndReadString();
                    //SetServerString = req.preparePost().withData("name=123&age=29").sendAndReadString(); //req.preparePost().withData(params).sendAndReadString();
                    SetServerString = req.preparePost().sendAndReadString();

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
        pd = ProgressDialog.show(AnggotaActivity.this, "Please Wait",
                "Connecting..", true);

        background.start(); // memanggil thread background agar start
    }
}
