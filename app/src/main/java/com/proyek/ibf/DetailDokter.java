package com.proyek.ibf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailDokter extends AppCompatActivity {

    // Deklarasi
    ImageView ivGambar;
    TextView btnKonsul;
    TextView tvNama, tvDeskripsi, tvStatus;
    private SessionHandler session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        session = new SessionHandler(this);
        final User user = session.getUserDetails();

        // Inisialisasi
        ivGambar = (ImageView) findViewById(R.id.foto);
        tvNama = (TextView) findViewById(R.id.nama);
        tvDeskripsi = (TextView) findViewById(R.id.deskripsi);
        tvStatus = (TextView) findViewById(R.id.status);
        btnKonsul = (TextView) findViewById(R.id.Btnkonsul);


        btnKonsul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getPoin();
                Intent varIntent = new Intent(DetailDokter.this, Chat.class);
                varIntent.putExtra("namadokter", tvNama.getText().toString());
                startActivity(varIntent);
            }
        });

        // Jalankan method tampil detail berita
        showDetailKonselor();



    }

    private void showDetailKonselor() {
        // Tangkap data dari intent
        String nama = getIntent().getStringExtra("nama");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String status = getIntent().getStringExtra("status");
        String foto = getIntent().getStringExtra("foto");

        // Set ke widget
        tvNama.setText(nama);
        tvDeskripsi.setText(deskripsi);
        // Untuk gambar berita
        Picasso.with(this).load(foto).into(ivGambar);
        // Set isi berita sebagai html ke WebView
        tvStatus.setText(status);
    }
}
