package me.codeminions.factory

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.codeminions.common.widget.BindingRecyclerAdapter

abstract class RecyclerFragment<FragmentBinding : ViewDataBinding,
        ItemViewBinding : ViewDataBinding,
        DataType> :
        PresenterFragment<FragmentBinding>() {

    protected abstract val adapter: BindingRecyclerAdapter<DataType, ItemViewBinding>
    protected abstract val recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = getLayoutManager()
    }

    open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

}
