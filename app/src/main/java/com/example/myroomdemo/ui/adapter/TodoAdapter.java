package com.example.myroomdemo.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myroomdemo.data.model.TodoModel;
import com.example.myroomdemo.databinding.RowTodoItemBinding;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    private ArrayList<TodoModel> list = new ArrayList<>();
    private TodoAdapterListener listener;

    public TodoAdapter(TodoAdapterListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowTodoItemBinding binding = RowTodoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TodoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {

        TodoModel model = list.get(position);

        holder.binding.setModel(model);
        holder.binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(model);
            }
        });
        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUpdate(model);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<TodoModel> list){
        this.list = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public static class TodoHolder extends RecyclerView.ViewHolder {

        RowTodoItemBinding binding;

        public TodoHolder(@NonNull RowTodoItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    public interface TodoAdapterListener{
        void onClick(TodoModel todoModel);
        void onDelete(TodoModel todoModel);
        void onUpdate(TodoModel todoModel);
    }
}
