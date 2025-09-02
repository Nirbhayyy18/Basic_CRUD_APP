package com.example.recyclerview_example2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class personAdapter extends RecyclerView.Adapter<personAdapter.viewholder> {

    ArrayList<infomodel> data = new ArrayList<>();
    public personAdapter(ArrayList<infomodel> data)
    {
        this.data = data;
    }
    public class viewholder extends RecyclerView.ViewHolder{
        TextView name,phone;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.person_name);
            phone = itemView.findViewById(R.id.phone_number);
        }
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.person_info, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull personAdapter.viewholder holder, int position) {
        infomodel item = data.get(position);
        holder.name.setText(item.name);
        holder.phone.setText(item.phone);
//        int pos = holder.getAdapterPosition();

        //update data
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d1 = new Dialog(view.getContext());
                d1.setContentView(R.layout.add_data);
                TextInputEditText t1 = d1.findViewById(R.id.name_input);
                TextInputEditText t2 = d1.findViewById(R.id.phone_input);
                t1.setText(item.name);
                t2.setText(item.phone);

                Button b1 = d1.findViewById(R.id.save_btn);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = t1.getText().toString();
                        String phone = t2.getText().toString();

                        data.set(position, new infomodel(name, phone));
                        notifyItemChanged(position);
                        d1.dismiss();
                    }
                });
                d1.getWindow().setLayout(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT
                );
                d1.show();
            }
        });

        // Delete Data
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                data.remove(position);   // remove item
                notifyItemRemoved(position); //notify that item removed
               return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
