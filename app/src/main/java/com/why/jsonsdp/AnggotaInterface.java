package com.why.jsonsdp;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by sdp03 on 7/18/16.
 */

public interface AnggotaInterface {
    @GET("/daftaranggota.php")
    void getStreams(@Query(" ") String q, Callback<List<AnggotaPojo>> callback);

    @POST("/addanggota.php")
    void tambahAnggota(@Body AnggotaPojo body, Callback<AnggotaPojo> callBack);
}
