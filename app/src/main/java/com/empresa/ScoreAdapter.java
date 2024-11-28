package com.empresa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {
    private Context context;
    private List<Score> scores;

    public ScoreAdapter(Context context, List<Score> scores) {
        this.context = context;
        this.scores = scores;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return scores.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_score, parent, false);
        }

        TextView usernameTextView = convertView.findViewById(R.id.usernameTextView);
        TextView scoreTextView = convertView.findViewById(R.id.scoreTextView);

        Score score = scores.get(position);
        usernameTextView.setText(score.getUsername());
        scoreTextView.setText(String.valueOf(score.getScore()));

        return convertView;
    }
}