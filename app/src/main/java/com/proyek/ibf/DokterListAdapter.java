package com.proyek.ibf;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proyek.ibf.response.KonselorItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DokterListAdapter extends RecyclerView.Adapter<DokterListAdapter.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<KonselorItem> konselor;
    public DokterListAdapter(Context context, List<KonselorItem> data_konselor) {
        // Inisialisasi
        this.context = context;
        this.konselor = data_konselor;
    }

    @Override
    public DokterListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.list_dokter, parent, false);

        // Hubungkan dengan MyViewHolder
        DokterListAdapter.MyViewHolder holder = new DokterListAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DokterListAdapter.MyViewHolder holder, final int position) {
        // Set widget
        holder.tvNama.setText(konselor.get(position).getNama());
        holder.tvStatus.setText(konselor.get(position).getStatus());
        // Dapatkan url gambar
        final String urlGambarKonselor = "http://mamina.id/ibf/images/" + konselor.get(position).getFoto();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambarKonselor).into(holder.ivGambar);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailDokter.class);
                // sisipkan data ke intent
                varIntent.putExtra("nama", konselor.get(position).getNama());
                varIntent.putExtra("deskripsi", konselor.get(position).getDsskripsi());
                varIntent.putExtra("status", konselor.get(position).getStatus());
                varIntent.putExtra("foto", urlGambarKonselor);

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return konselor.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        CircleImageView ivGambar;
        TextView tvNama,tvStatus;
        public MyViewHolder(View itemView) {

            super(itemView);
            // inisialisasi widget
            ivGambar = (CircleImageView) itemView.findViewById(R.id.foto);
            tvNama = (TextView) itemView.findViewById(R.id.nama);
            tvStatus = (TextView) itemView.findViewById(R.id.status);
        }
    }
}