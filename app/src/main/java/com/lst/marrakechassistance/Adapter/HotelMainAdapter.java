package com.lst.marrakechassistance.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.lst.marrakechassistance.Activity.MainActivity;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HotelMainAdapter extends RecyclerView.Adapter<HotelMainAdapter.ViewHolder> {
    ArrayList<Hotel> hotels;
    DecimalFormat formatter;
    Context context;


    public HotelMainAdapter(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
        formatter = new DecimalFormat("###,###,###,###");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        context= parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(hotels.get(position).getTitle());
        holder.addressTxt.setText(hotels.get(position).getAddress());

        holder.priceTxt.setText("&"+formatter.format(hotels.get(position).getPrice()));

        int drawableRecouceId = holder.itemView.getResources().getIdentifier(hotels.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableRecouceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("object",hotels.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, addressTxt,priceTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            addressTxt = itemView.findViewById(R.id.addressText);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
