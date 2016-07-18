package com.why.jsonsdp;

/**
 * Created by sdp03 on 7/18/16.
 */

import com.google.gson.annotations.Expose;

public class AnggotaPojo {
    @Expose
    private String id;
    @Expose
    private String nama;
    @Expose
    private String alamat;
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @Expose
    private String foto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
