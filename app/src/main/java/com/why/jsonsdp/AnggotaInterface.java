package com.why.jsonsdp;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by sdp03 on 7/18/16.
 */

public interface AnggotaInterface {
    @GET("/daftaranggota.php")
    void getDataAnggota(Callback<AnggotaWrapper> callback);

    @POST("/addanggota.php")
    void tambahAnggota(@Body AnggotaWrapper.Anggota body, Callback<AnggotaWrapper.Anggota> callBack);

    @FormUrlEncoded
    @POST("/addanggota.php")
    void tambahPostAnggota(@Field("nama") String nama,
                           @Field("username") String username,
                           @Field("password") String password,
                           @Field("alamat") String alamat,
                           @Field("latitude") String latitude,
                           @Field("longitude") String longitude,
                           @Field("foto") String foto,
                           Callback<Response> callback);
}
