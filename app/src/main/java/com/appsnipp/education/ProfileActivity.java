package com.appsnipp.education;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends Fragment {
    private SessionHandler session;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);
        session = new SessionHandler(getActivity().getApplicationContext());
        User user = session.getUserDetails();
        //NAMA atas
        TextView nama = rootView.findViewById(R.id.nama);
        nama.setText(user.getFullName());
        //NAMA bwh
        TextView namal = rootView.findViewById(R.id.nameTextView);
        namal.setText(user.getPoin());
        //NO HP
        TextView no_hp = rootView.findViewById(R.id.no_hpTextView);
        no_hp.setText(user.getNoHp());
        //EMAIL
        TextView email = rootView.findViewById(R.id.emailTextView);
        email.setText(user.getEmail());
        //TTL
        TextView ttl = rootView.findViewById(R.id.ttlTextView);
        ttl.setText(user.getTtl());
        //ALAMAt
        TextView alamat = rootView.findViewById(R.id.alamatTextView);
        alamat.setText(user.getAlamat());
        //POINT
        TextView poin = rootView.findViewById(R.id.poinTextView);
        poin.setText(user.getPoin());
        return rootView;
    }
}

