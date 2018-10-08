package cz.airbank.airbankapplication.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.airbank.airbankapplication.BR
import cz.airbank.airbankapplication.arch.viewmodel.BaseViewModel

/**
 * Base adapter for RecyclerView.
 *
 * @author Ivo Chvojka
 */
abstract class BaseRecyclerAdapter<T>(private val viewModel: BaseViewModel?) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var list: List<T> = emptyList()

    abstract fun getLayoutIdForPosition(position: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RecyclerViewHolder(DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false))

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        getData(position)
                ?.let {
                    val bindingSuccess = holder.binding.setVariable(BR.data, it)
                    if (!bindingSuccess) {
                        throw IllegalStateException("Binding ${holder.binding} data variable name should be 'data'")
                    }
                }

        viewModel?.let {
            val bindingSuccess = holder.binding.setVariable(BR.viewModel, it)
            if (!bindingSuccess) {
                throw IllegalStateException("Binding ${holder.binding} viewModel variable name should be 'viewModel'")
            }
        }
    }

    override fun getItemViewType(position: Int) = getLayoutIdForPosition(position)

    override fun getItemCount(): Int = list.size

    open fun getData(position: Int): T = list[position]

    open fun setData(list: List<T>) {
        this.list = list
        notifyDataSetChanged()
    }
}