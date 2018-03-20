package com.example.shikh.updater;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.shikh.updater.model.task;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by shikh on 17-02-2018.
 */

public class taskadapter extends RecyclerView.Adapter<taskadapter.taskViewHolder>{
    ArrayList<task> Task;

    @Override
    public taskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new taskViewHolder(li.inflate(R.layout.item_task,parent,false));
    }

    @Override
    public void onBindViewHolder(taskViewHolder holder, int position) {
        holder.BindView(Task.get(position));
    }

    @Override
    public int getItemCount() {
        return Task.size();
    }

    class taskViewHolder extends RecyclerView.ViewHolder{
        TextView tv_task;

        public taskViewHolder(View itemView) {
            super(itemView);
            tv_task = itemView.findViewById(R.id.tv_task);
        }

        public void BindView(task task1){

        }

    }
}
