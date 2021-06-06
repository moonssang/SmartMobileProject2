package com.smartmobileproject.function;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smartmobileproject.activity.R;

import java.util.ArrayList;

public class itemAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public itemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycle_item,parent,false);
        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        Item item= items.get(position);
        vh.txtemail.setText(item.getEmail());

        Glide.with(context).load(item.getImgPath()).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView txtemail;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            txtemail=itemView.findViewById(R.id.txtemail);
            iv=itemView.findViewById(R.id.iv);

        }
    }
}



