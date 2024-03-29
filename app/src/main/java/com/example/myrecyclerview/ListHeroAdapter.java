package com.example.myrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {

    /*
    Description : Function ArrayList Hero & Clicked CardView
    */
    private ArrayList<Hero> listHero;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    public ListHeroAdapter(ArrayList<Hero> list) {
        this.listHero = list;
    }

    /*
    Description : LifeCycle Method dengan menggunakan function Create
    */
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_hero, viewGroup, false);
        return new ListViewHolder(view);
    }

    /*
    Description : Function Show/Blind View dengan menggunakan Glide
    */
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Hero hero = listHero.get(position);

        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);

        holder.tvName.setText(hero.getName());
        holder.tvFrom.setText(hero.getFrom());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    /*
    Description : Function View Array sesuai dengan list yang ada pada HeroesData.java
    */
    @Override
    public int getItemCount() {
        return listHero.size();
    }

    /*
    Description : Function View dengan findViewById ada Layout item_row_hero.xml
    */
    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName, tvFrom;

        public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        imgPhoto = itemView.findViewById(R.id.img_item_photo);
        tvName = itemView.findViewById(R.id.tv_item_name);
        tvFrom = itemView.findViewById(R.id.tv_item_from);
        }
    }

    /*
    Description : Function OnItemClickCallback
    */
    public interface OnItemClickCallback {
        void onItemClicked(Hero data);
    }









}
