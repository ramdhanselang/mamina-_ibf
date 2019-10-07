package com.proyek.ibf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyek.ibf.response.ArtikelItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<ArtikelItem> artikel;
    public AdapterArtikel(Context context, List<ArtikelItem> data_Artikel) {
        // Inisialisasi
        this.context = context;
        this.artikel = data_Artikel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.artikel_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.tvJudul.setText(artikel.get(position).getJudul());
        holder.tvPenulis.setText(artikel.get(position).getPenulis());
        holder.tvTglTerbit.setText(artikel.get(position).getTanggalPosting());

        // Dapatkan url gambar
        final String urlGambarBerita = "http://mamina.id/ibf/images/" + artikel.get(position).getFoto();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambarBerita).into(holder.ivGambarBerita);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailArtikel.class);
                // sisipkan data ke intent
                varIntent.putExtra("judul", artikel.get(position).getJudul());
                varIntent.putExtra("tanggal_posting", artikel.get(position).getTanggalPosting());
                varIntent.putExtra("penulis", artikel.get(position).getPenulis());
                varIntent.putExtra("Summary", artikel.get(position).getSummary());
                varIntent.putExtra("foto", urlGambarBerita);
                varIntent.putExtra("content", artikel.get(position).getContent());

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return artikel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvSummary, tvPenulis;
        public MyViewHolder(View itemView) {
            super(itemView);
            // inisialisasi widget
            ivGambarBerita = (ImageView) itemView.findViewById(R.id.ivFotoArtikel);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            tvSummary = (TextView) itemView.findViewById(R.id.tvSummary);
            tvTglTerbit = (TextView) itemView.findViewById(R.id.tvTglArtikel);
            tvPenulis = (TextView) itemView.findViewById(R.id.tvPenulis);
        }
    }
}
