package com.example.onem2m_inae_mvp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onem2m_in_ae.model.ContainerInstance
import com.example.onem2m_inae_mvp.databinding.ItemContainerBinding

class ContainerRecyclerViewAdapter :
    ListAdapter<ContainerInstance, ContainerRecyclerViewAdapter.ContainerViewHolder>(
        DIFF_CALLBACK
    ),
    AdapterContract.View, AdapterContract.Model {
    companion object {
        val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<ContainerInstance>() {
            override fun areItemsTheSame(
                oldItem: ContainerInstance,
                newItem: ContainerInstance
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ContainerInstance,
                newItem: ContainerInstance
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override var onClickFunc: ((ContainerInstance) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerViewHolder {
        return ContainerViewHolder(
            ItemContainerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClickFunc
        )
    }

    override fun onBindViewHolder(holder: ContainerViewHolder, position: Int) {
        holder.bind(
            currentList[position]
        )
    }

    inner class ContainerViewHolder(
        private val binding: ItemContainerBinding,
        private val listenerFunc: ((ContainerInstance) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContainerInstance) {
            binding.apply {
                containerItemImageView.setImageResource(item.deviceImage)
                containerItemNameTextView.text = item.deviceName
                containerItemImageView.setOnClickListener {
                    listenerFunc?.invoke(item)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<ContainerInstance>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    override fun addItems(imageItems: List<ContainerInstance>) {

    }

    override fun clearItem() {
        currentList.clear()
    }
}