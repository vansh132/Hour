package com.example.hourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hourapp.R;
import com.example.hourapp.model.Hour;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private onRecordClickListener onRecordClickListener;
    private List<Hour> recordList;
    private Context context;

    public RecyclerViewAdapter(List<Hour> recordList, Context context, onRecordClickListener onRecordClickListener) {
        this.recordList = recordList;
        this.context = context;
        this.onRecordClickListener = onRecordClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hour_row, parent, false);


        return new ViewHolder(view, onRecordClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Hour hour = recordList.get(position);
        holder.date.setText(hour.getDate());
        holder.noOfHours.setText(Integer.toString(hour.getNoOfHours()));
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public onRecordClickListener onRecordClickListener;
        public TextView date;
        public TextView noOfHours;

        public ViewHolder(@NonNull View itemView, onRecordClickListener onRecordClickListener) {
            super(itemView);

            date = itemView.findViewById(R.id.date_row);
            noOfHours = itemView.findViewById(R.id.noOfHours_row);
            this.onRecordClickListener = onRecordClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecordClickListener.onRecordClick(getAdapterPosition());
        }
    }

    public interface onRecordClickListener {
        void onRecordClick(int position);
    }
}
