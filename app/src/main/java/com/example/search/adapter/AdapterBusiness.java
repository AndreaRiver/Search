package com.example.search.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.search.R;
import com.example.search.pojo.business;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterBusiness extends RecyclerView.Adapter<AdapterBusiness.viewholderbusiness>{

    List<business> businessList;

    public AdapterBusiness(List<business> businessList) {
        this.businessList = businessList;
    }

    @NonNull
    @Override
    public viewholderbusiness onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_business,parent,false);
        viewholderbusiness holder = new viewholderbusiness(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderbusiness holder, int position) {
        business bs = businessList.get(position);

        holder.description.setText(bs.getDescription());
        holder.BusName.setText(bs.getName());
        holder.country.setText(bs.getCountry());

    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    public class viewholderbusiness extends RecyclerView.ViewHolder {
        TextView BusName;
        TextView country;
        TextView description;
        public viewholderbusiness(@NonNull View itemView) {
            super(itemView);
            BusName = itemView.findViewById(R.id.BusName);
            country = itemView.findViewById(R.id.country);
            description = itemView.findViewById(R.id.description);
        }
    }
}
