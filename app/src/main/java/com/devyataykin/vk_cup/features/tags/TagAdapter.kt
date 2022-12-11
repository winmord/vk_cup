package com.devyataykin.vk_cup.features.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.devyataykin.vk_cup.R
import com.devyataykin.vk_cup.model.Tag
import com.devyataykin.vk_cup.model.TagDiffCallback

class TagAdapter(private val listener: TagViewHolder.Listener) :
    RecyclerView.Adapter<TagViewHolder>() {
    private var tags = mutableListOf<Tag>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_tag, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tags[position], listener)
    }

    override fun getItemCount(): Int = tags.size

    fun bindTags(bindedTags: List<Tag>) {
        val diffCallback = TagDiffCallback(tags, bindedTags)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        tags.clear()
        tags.addAll(bindedTags)
        diffResult.dispatchUpdatesTo(this)
    }
}