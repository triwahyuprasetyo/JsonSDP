package com.why.jsonsdp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by why on 7/19/16.
 */

public interface AnggotaService {
    @GET("daftaranggota.php")
    Call<AnggotaWrapper> listAnggota();

    @FormUrlEncoded
    @POST("addanggota.php")
    Call<Void> tambahPostAnggota(@Field("nama") String nama,
                                 @Field("username") String username,
                                 @Field("password") String password,
                                 @Field("alamat") String alamat,
                                 @Field("latitude") String latitude,
                                 @Field("longitude") String longitude,
                                 @Field("foto") String foto);
}
