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

class KonselorAdapter extends RecyclerView.Adapter<KonselorAdapter.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<KonselorItem> konselor;
    public KonselorAdapter(Context context, List<KonselorItem> data_konselor) {
        // Inisialisasi
        this.context = context;
        this.konselor = data_konselor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.tvNama.setText(konselor.get(position).getNama());
        // Dapatkan url gambar
        final String urlGambarKonselor = "http://192.168.1.4/member/images/" + konselor.get(position).getFoto();
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
        TextView tvNama;
        public MyViewHolder(View itemView) {

            super(itemView);
            // inisialisasi widget
            ivGambar = (CircleImageView) itemView.findViewById(R.id.iv);
            tvNama = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
