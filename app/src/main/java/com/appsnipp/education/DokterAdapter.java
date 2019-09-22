package com.appsnipp.education;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DokterModel> imageModelArrayList;


    public DokterAdapter(Context ctx, ArrayList<DokterModel> imageModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public DokterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_popular_courses, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final DokterAdapter.MyViewHolder holder, final int position) {
        final DokterModel movieModel = imageModelArrayList.get(position);
        holder.time.setText(movieModel.getName());
        Glide.with (holder.itemView.getContext()).load(movieModel.getImage_drawable()).into(holder.iv);
//        Bitmap bitmap = BitmapFactory.decodeFile(imageModelArrayList.get(position));



        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailDokter.class );
                i.putExtra("fotodokter", holder.iv.getId());

                i.putExtra("namadokter", holder.time.getText().toString());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        CircleImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (CircleImageView) itemView.findViewById(R.id.iv);
        }

    }.

}
