package com.proyek.ibf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.proyek.ibf.response.ArtikelItem;
import com.proyek.ibf.response.ResponseArtikel;
import com.proyek.network.ApiServices;
import com.proyek.network.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArtikelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artikel);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilArtikel();
    }

    private void tampilArtikel() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseArtikel> arikelCall = api.request_show_all_artikel();
        // Kirim request
        arikelCall.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<ArtikelItem> data_artikel = response.body().getArtikel();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterArtikel adapter = new AdapterArtikel(ListArtikelActivity.this, data_artikel);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(ListArtikelActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}