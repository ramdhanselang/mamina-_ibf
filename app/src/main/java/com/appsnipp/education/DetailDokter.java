package com.appsnipp.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailDokter extends AppCompatActivity {

    CircleImageView foto;
    private TextView nama;
    int fotodokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);
        foto = (CircleImageView) findViewById(R.id.foto);
        nama = (TextView) findViewById(R.id.nama);
        fotodokter = getIntent().getIntExtra("fotodokter",0);
        Intent i = getIntent();
//      nama.setText(i.getStringExtra("namadokter"));
        nama.setText(i.getIntExtra("fotodokter",0));
        foto.setImageResource(fotodokter);
        //Glide.with(this).asBitmap().load(fotodokter).into(foto);
    }
}
