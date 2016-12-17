package me.brendanweinstein.nestedscroll;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private List<String> items;

    public TestAdapter() {
        items = new ArrayList<>();
        for (int i=0; i < 50; i++) {
            items.add("llama gate llama gate llama llama gate");
        }
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#0000FF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public TestViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
