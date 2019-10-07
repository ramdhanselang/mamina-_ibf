package com.proyek.ibf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailArtikel extends AppCompatActivity {

    ImageView ivGambarBerita;
    TextView tvJudul,tvTglTerbit, tvPenulis,tvSummary;
    WebView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        // Inisialisasi
        ivGambarBerita = (ImageView) findViewById(R.id.ivGambar);
        tvJudul =  (TextView) findViewById(R.id.judul);
        tvTglTerbit = (TextView) findViewById(R.id.tvTglTerbitArtikel);
        tvSummary = (TextView) findViewById(R.id.tvSummary);
        tvContent = (WebView) findViewById(R.id.tvContent);
        tvTglTerbit = (TextView) findViewById(R.id.tvTglTerbitArtikel);
        tvPenulis = (TextView) findViewById(R.id.tvPenulis);

        // Jalankan method tampil detail berita
        showDetailArtikel();

    }

    private void showDetailArtikel() {
        // Tangkap data dari intent
        String judul = getIntent().getStringExtra("judul");
        String tanggal = getIntent().getStringExtra("tanggal_posting");
        String penulis = getIntent().getStringExtra("penulis");
        String summary = getIntent().getStringExtra("summary");
        String content = getIntent().getStringExtra("content");
        String foto = getIntent().getStringExtra("foto");

        // Set judul actionbar / toolbar
       tvJudul.setText(judul);

        // Set ke widget
        tvPenulis.setText("Oleh : " + penulis);
        tvTglTerbit.setText(tanggal);
        // Untuk gambar artikel
        Picasso.with(this).load(foto).into(ivGambarBerita);
        tvContent.getSettings().setJavaScriptEnabled(true);
        tvContent.loadData(content, "text/html; charset=utf-8", "UTF-8");
        tvSummary.setText(summary);
    }
}
