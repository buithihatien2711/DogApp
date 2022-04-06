package com.example.dogapp.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private List<DogBreed> dogBreeds;
    private Context context;


    public DogAdapter(Context context) {
        this.context = context;
    }

    public DogAdapter(List<DogBreed> dogBreeds) {
        this.dogBreeds = dogBreeds;
    }

    public DogAdapter(List<DogBreed> dogBreeds, Context context) {
        this.dogBreeds = dogBreeds;
        this.context = context;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dog, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(dogBreeds.get(position).getName());
        holder.txtBredFor.setText(dogBreeds.get(position).getBredFor());
        //Using Glide library to display the image
        Glide.with(context)
                .load(dogBreeds.get(position).getUrl())
                .into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        if(dogBreeds != null){
            return dogBreeds.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public ImageView ivAvatar;
        public TextView txtBredFor;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            txtBredFor = itemView.findViewById(R.id.txt_bred_for);
            itemView.setTag(itemView);


        }

    }
}
