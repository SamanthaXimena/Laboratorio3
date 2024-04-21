package com.example.laboratorio3;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPelicula extends RecyclerView.Adapter<AdapterPelicula.ViewHolder>{

    ArrayList<Persona> list;

    public AdapterPelicula(ArrayList<Persona> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_detallepelicula,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.writePersona(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView  Director, Actors, Released,Genre , Writer, Plot, Rating, Title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Director = itemView.findViewById(R.id.id_Director);
            Actors = itemView.findViewById(R.id.id_Actors);
            Genre = itemView.findViewById(R.id.id_Genre);
            Writer = itemView.findViewById(R.id.id_Writer);
            Plot = itemView.findViewById(R.id.id_Plot);
            Rating = itemView.findViewById(R.id.id_Rating);
            Title = itemView.findViewById(R.id.id_Title);
            Released = itemView.findViewById(R.id.id_Released);


        }

        public void writePersona(Persona p) {
            Title.setText(("BODY:\n" + p.getTitle()));
            Director.setText(("Director:\n" + p.getDirector()));
            Actors.setText(("Actors:\n" + p.getActors()));
            Genre.setText(("Genre:\n" + p.getGenre()));
            Writer.setText(("Writer:\n" + p.getGenre()));
            Plot.setText(("Plot:\n" + p.getGenre()));
            Rating.setText(("Rating:\n" + p.getRating()));
            Released.setText(("Rating:\n" + p.getReleased()));



        }
    }
}
