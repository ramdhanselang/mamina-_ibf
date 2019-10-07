package com.proyek.ibf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.proyek.ibf.response.ArtikelItem;
import com.proyek.ibf.response.KonselorItem;
import com.proyek.ibf.response.ResponseArtikel;
import com.proyek.ibf.response.ResponseKonselor;
import com.proyek.network.ApiServices;
import com.proyek.network.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends Fragment {
    private RecyclerView recyclerViewKonselor,recyclerViewArtikelRekomen,recyclerViewArtikelPopular,recyclerViewArtikelLastest;
    private SessionHandler session;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_beranda, container, false);

        session = new SessionHandler(getActivity().getApplicationContext());
        User user = session.getUserDetails();
        TextView welcomeText = rootView.findViewById(R.id.welcome);

        welcomeText.setText("Welcome "+user.getFullName());

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        TextView dOkter = rootView.findViewById(R.id.dokter);

        TextView artikel= rootView.findViewById(R.id.artikel);

        dOkter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListKonselorActivity.class);
                startActivity(i);
            }
        });

        artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListArtikelActivity.class);
                startActivity(i);
            }
        });

        recyclerViewKonselor = (RecyclerView) rootView.findViewById(R.id.dokterlist);
        recyclerViewKonselor.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        tampilKonselor();

        recyclerViewArtikelRekomen = (RecyclerView) rootView.findViewById(R.id.ArtikelListRekomen);
        recyclerViewArtikelRekomen.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        tampilArtikelRekomen();

        recyclerViewArtikelPopular = (RecyclerView) rootView.findViewById(R.id.ArtikelListPopular);
        recyclerViewArtikelPopular.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        tampilArtikelPopular();

        recyclerViewArtikelLastest = (RecyclerView) rootView.findViewById(R.id.ArtikelListLastest);
        recyclerViewArtikelLastest.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        tampilArtikelLastest();




        ImageButton logoutBtn = (ImageButton) rootView.findViewById(R.id.logout);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }
        });
        return rootView;
    }

    private void tampilKonselor() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseKonselor> beritaCall = api.request_show_all_konselor();
        // Kirim request
        beritaCall.enqueue(new Callback<ResponseKonselor>() {
            @Override
            public void onResponse(Call<ResponseKonselor> call, Response<ResponseKonselor> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<KonselorItem> data_konselor = response.body().getDokter();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    // kalau tidak true
                    if (status){
                        // Buat Adapter untuk recycler view
                        KonselorAdapter adapter = new KonselorAdapter(getContext(), data_konselor);
                        recyclerViewKonselor.setAdapter(adapter);
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

    private void tampilArtikelRekomen() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseArtikel> artikelCall = api.request_show_all_artikel();
        // Kirim request
        artikelCall.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<ArtikelItem> data_artikel = response.body().getArtikel();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    // kalau tidak true
                    if (status){
                        // Buat Adapter untuk recycler view
                       AdapterArtikel adapter = new AdapterArtikel(getContext(), data_artikel);
                        recyclerViewArtikelRekomen.setAdapter(adapter);
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

    private void tampilArtikelPopular() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseArtikel> artikelCall = api.request_show_all_artikel();
        // Kirim request
        artikelCall.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<ArtikelItem> data_artikel = response.body().getArtikel();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    // kalau tidak true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterArtikel adapter = new AdapterArtikel(getContext(), data_artikel);
                        recyclerViewArtikelPopular.setAdapter(adapter);
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

    private void tampilArtikelLastest() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseArtikel> artikelCall = api.request_show_all_artikel();
        // Kirim request
        artikelCall.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<ArtikelItem> data_artikel = response.body().getArtikel();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    // kalau tidak true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterArtikel adapter = new AdapterArtikel(getContext(), data_artikel);
                        recyclerViewArtikelLastest.setAdapter(adapter);
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