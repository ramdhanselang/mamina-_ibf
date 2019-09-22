package com.appsnipp.education;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileActivity extends Fragment {
    private SessionHandler session;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);
        session = new SessionHandler(getActivity().getApplicationContext());
        User user = session.getUserDetails();
        TextView nama = rootView.findViewById(R.id.nama);
        nama.setText(user.getFullName());
        TextView namal = rootView.findViewById(R.id.nameTextView);
        namal.setText(user.getFullName());
        return rootView;
    }
}
