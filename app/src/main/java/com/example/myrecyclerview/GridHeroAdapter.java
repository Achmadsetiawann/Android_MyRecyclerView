package com.example.myrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridHeroAdapter extends RecyclerView.Adapter<GridHeroAdapter.GridViewHolder> {

    /*
    Description : Function ArrayList Hero & Clicked CardView
    */
    private ArrayList<Hero> listHero;
    private OnItemClickCallback onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallback onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public GridHeroAdapter(ArrayList<Hero> list) {
        this.listHero = list;
    }

    /*
    Description : LifeCycle Method dengan menggunakan function Create
    */
    @NonNull
    @Override
    public GridHeroAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_president, viewGroup, false);
        return new GridViewHolder(view);
    }

    /*
    Description : Function Show/Blind View dengan menggunakan Glide
    */
    @Override
    public void onBindViewHolder(@NonNull final GridHeroAdapter.GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(listHero.get(position).getPhoto())
                .apply(new RequestOptions().override(350,350))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    /*
    Description : Function View dengan findViewById ada Layout item_grid_president.xml
    */
    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        public GridViewHolder(@NonNull View itemView) {
        super(itemView);
        imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }

    }

    /*
    Description : Function OnItemClickCallback
    */
    public interface OnItemClickCallback {
        void onItemClicked(Hero data);
    }


}
