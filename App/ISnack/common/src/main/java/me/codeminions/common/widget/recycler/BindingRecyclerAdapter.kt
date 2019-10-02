package me.codeminions.common.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BindingRecyclerAdapter<Data, DataBinding : ViewDataBinding>(var list: List<Data> = ArrayList(), var listener: OnItemClickListener<Data>? = null) : RecyclerView.Adapter<BindingRecyclerAdapter.BindingViewHolder<DataBinding>>() {

    abstract override fun getItemViewType(position: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BindingViewHolder<DataBinding> {

        val binding = DataBindingUtil.inflate<DataBinding>(LayoutInflater.from(parent.context), layoutId, parent, false)

        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<DataBinding>, position: Int) {
        onBindViewHolder(holder.binding, list[position])
        holder.binding.root.setOnClickListener {
            listener?.onItemClick(list[position])
        }
    }

    abstract fun onBindViewHolder(bing: DataBinding, data: Data)

    override fun getItemCount(): Int {
        return list.size
    }

    class BindingViewHolder<DataBinding : ViewDataBinding>(val binding: DataBinding) : RecyclerView.ViewHolder(binding.root)

}
