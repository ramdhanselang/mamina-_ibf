package com.proyek.ibf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.proyek.ibf.response.KonselorItem;
import com.proyek.ibf.response.ResponseKonselor;
import com.proyek.network.ApiServices;
import com.proyek.network.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDokterActivity extends AppCompatActivity {

    // Deklarasi Widget
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dokter);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.dokterlist);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilKonselor();
    }

    private void tampilKonselor() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseKonselor> konselorCall = api.request_show_all_konselor();
        // Kirim request
        konselorCall.enqueue(new Callback<ResponseKonselor>() {
            @Override
            public void onResponse(Call<ResponseKonselor> call, Response<ResponseKonselor> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<KonselorItem> data_dokter = response.body().getDokter();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = trueA
                    if (status){
                        // Buat Adapter untuk recycler view
                        DokterListAdapter adapter = new DokterListAdapter(ListDokterActivity.this, data_dokter);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(ListDokterActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKonselor> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }

        });
    }
}
