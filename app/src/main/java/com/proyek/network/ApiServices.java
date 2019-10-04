package com.proyek.network;

import com.proyek.ibf.response.ResponseArtikel;
import com.proyek.ibf.response.ResponseKonselor;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    //@TIPEMETHOD("API_END_POINT")
    @GET("tampil_artikel.php")
    Call<ResponseArtikel> request_show_all_artikel();
    @GET("tampil_konselor.php")
    Call<ResponseKonselor> request_show_all_konselor();
    // <ModelData> nama_method()

}