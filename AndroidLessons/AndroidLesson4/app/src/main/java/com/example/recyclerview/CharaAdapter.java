package com.example.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview.R;

public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder>{
    Context context;
    LayoutInflater mInflater;
    DataSource dataSource;
    OnPokemonListener mOnPokemonListener;

    //TODO 11.3 Complete the constructor to initialize the DataSource instance variable
    CharaAdapter(Context context, DataSource dataSource, OnPokemonListener onPokemonListener){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataSource = dataSource;
        this.mOnPokemonListener = onPokemonListener;
    }

    //TODO 11.5 the layout of each Card is inflated and used to instantiate CharaViewHolder (no coding)
    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.pokemon, viewGroup, false);
        return new CharaViewHolder(itemView, mOnPokemonListener);
    }

    //TODO 11.6 the data at position i is extracted and placed on the i-th card
    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder charaViewHolder, int i) {
        Bitmap image = dataSource.getImage(i);
        String name = dataSource.getName(i);
        charaViewHolder.imageViewChara.setImageBitmap(image);
        charaViewHolder.textViewName.setText(name);
    }

    //TODO 11.7 the total number of data points must be returned here
    @Override
    public int getItemCount() {
        return this.dataSource.getSize();
    }

    //TODO 11.4 complete the constructor to initialize the instance variables
    // TODO 13.1 (Self-added) Getting each item to respond to Clicks
    // refer to https://www.youtube.com/watch?v=69C1ljfDvl0 on how to do this
    // Remove implements if it using alternative method
    static class CharaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewChara;
        TextView textViewName;
        OnPokemonListener onPokemonListener;

        CharaViewHolder(View view, OnPokemonListener onPokemonListener){
            super(view);
            imageViewChara = view.findViewById(R.id.cardViewImage);
            textViewName = view.findViewById(R.id.cardViewTextName);

            this.onPokemonListener = onPokemonListener;
            view.setOnClickListener(this);

            // Alternative method to implement onClickListener for the adapter
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onPokemonListener.onPokemonClick(getAdapterPosition());
//                }
//            });
        }

        @Override
        public void onClick(View view) {
            onPokemonListener.onPokemonClick(getAdapterPosition());
        }
    }

    public interface OnPokemonListener {
        void onPokemonClick(int position);
    }
}
