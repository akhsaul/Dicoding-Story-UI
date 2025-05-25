package org.akhsaul.dicoding.story.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.akhsaul.dicoding.story.ui.databinding.ItemStoryBinding

class MyListAdapter(
    private val listener: (Story, View, String) -> Unit
) : ListAdapter<Story, MyListAdapter.MyViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemStoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), listener)
    }

    class MyViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Story, onClick: (Story, View, String) -> Unit) {
            with(binding) {
                val transitionName = root.context.getString(
                    R.string.transition_view_to_detail,
                    item.id
                )
                Log.d("MyViewHolder", "bind, transitionName: $transitionName")
                root.transitionName = transitionName
                root.setOnClickListener {
                    onClick(item, root, transitionName)
                }
                ivItemPhoto.setImageResource(R.drawable.photo_test)
                tvItemName.text = item.name
                tvDesc.text = item.description
                tvCreateAt.text = item.createdAt.toString()
            }
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(
            oldItem: Story,
            newItem: Story
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Story,
            newItem: Story
        ): Boolean {
            return oldItem == newItem
        }
    }
}

