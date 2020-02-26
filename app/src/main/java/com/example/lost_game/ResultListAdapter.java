package com.example.lost_game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lost_game.database.Result;

import java.util.List;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ResultViewHolder> {

    class ResultViewHolder extends RecyclerView.ViewHolder {
        private final TextView resultNameItemView;
        private final TextView resultScoreItemView;
        private final TextView resultDateItemView;


        private ResultViewHolder(View itemView) {
            super(itemView);
            resultNameItemView = itemView.findViewById(R.id.nameView);
            resultScoreItemView = itemView.findViewById(R.id.scoreView);
            resultDateItemView = itemView.findViewById(R.id.dateView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Result> mResults; // Cached copy of results

    ResultListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        if (mResults != null) {
            Result current = mResults.get(position);
            holder.resultNameItemView.setText(current.name);
            holder.resultScoreItemView.setText(String.valueOf(current.timeScore));
            holder.resultDateItemView.setText(String.valueOf(current.date));
        } else {
            // Covers the case of data not being ready yet.
            holder.resultNameItemView.setText("No result");
            holder.resultNameItemView.setText("");
            holder.resultDateItemView.setText("");
        }
    }

    void setResults(List<Result> results){
        mResults = results;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mResults has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mResults != null)
            return mResults.size();
        else return 0;
    }
}