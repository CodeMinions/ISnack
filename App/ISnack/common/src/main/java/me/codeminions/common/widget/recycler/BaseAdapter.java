package me.codeminions.common.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>> {

    private List<T> list;

    private OnItemClickListener<T> listener;

    public BaseAdapter() {
        this(new ArrayList<>());
    }

    public BaseAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    abstract public int getItemViewType(int position);

    abstract public BaseViewHolder<T> onCreateViewHolder(View view, int layoutId);

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        BaseViewHolder<T> holder = onCreateViewHolder(view, layoutId);
        view.setOnClickListener((v) -> {
            if(listener != null)
                listener.onItemClick(holder.data);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        holder.bind(list.get(position));
    }

    public void setListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract static class BaseViewHolder<T> extends RecyclerView.ViewHolder {
        private T data;

        public BaseViewHolder(View v) {
            super(v);
        }

        abstract public void onBind(T data);

        private void bind(T data) {
            this.data = data;
            onBind(data);
        }
    }
}
