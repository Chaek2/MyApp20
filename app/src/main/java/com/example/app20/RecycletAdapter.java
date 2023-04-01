package com.example.app20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycletAdapter extends RecyclerView.Adapter<RecycletAdapter.ViewHolder> {
    private RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<Zametki> groupsList;


    public RecycletAdapter(RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<Zametki> groupsList) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.groupsList = groupsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_1_card,parent,false);
        return new ViewHolder(view, recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Zametki group = groupsList.get(position);
//        holder.id.setText(group.getID());
//        holder.key.setText(group.getUsers_Key());
        holder.text.setText(group.getZametki_Text());
    }
    @Override
    public int getItemCount(){
        return groupsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView key;
        TextView text;
        ViewHolder(View view, RecyclerViewInterface recyclerViewInterface){
            super(view);
//            id = view.findViewById(R.id.id);
//            key = view.findViewById(R.id.key);
            text = view.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(RecycletAdapter.this.recyclerViewInterface !=null){
                        int pos= getAdapterPosition();
                        if (pos !=RecyclerView.NO_POSITION){
                            RecycletAdapter.this.recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
