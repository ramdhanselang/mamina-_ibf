package com.appsnipp.education;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BerandaActivity extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DokterModel> imageModelArrayList;
    private DokterAdapter adapter;
    private SessionHandler session;

    private int[] myImageList = new int[]{R.drawable.dokter1, R.drawable.dokter2,R.drawable.dokter3,R.drawable.dokter4,R.drawable.dokter5,R.drawable.dokter6,R.drawable.dokter7};
    private String[] myImageNameList = new String[]{"Dokter1","Dokter2","Dokter3","Dokter4","Dokter5","Dokter6","Dokter7"};
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_beranda, container, false);

        session = new SessionHandler(getActivity().getApplicationContext());
        User user = session.getUserDetails();
        TextView welcomeText = rootView.findViewById(R.id.welcome);

        welcomeText.setText("Welcome "+user.getPoin());

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

        imageModelArrayList = Dokter();
        adapter = new DokterAdapter((Context) getActivity(), imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


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


    private ArrayList<DokterModel> Dokter(){

        ArrayList<DokterModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            DokterModel dokterModel = new DokterModel();
            dokterModel.setName(myImageNameList[i]);
            dokterModel.setImage_drawable(myImageList[i]);
            list.add(dokterModel);
        }

        return list;
    }
}
