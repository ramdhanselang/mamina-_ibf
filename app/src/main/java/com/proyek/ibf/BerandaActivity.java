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

import com.proyek.ibf.response.KonselorItem;
import com.proyek.ibf.response.ResponseKonselor;
import com.proyek.network.ApiServices;
import com.proyek.network.InitRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<KonselorModel> imageModelArrayList;
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

        dOkter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), ListDokterActivity.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.dokterlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        tampilKonselor();


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
                        recyclerView.setAdapter(adapter);
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