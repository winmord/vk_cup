package com.devyataykin.vk_cup.model

import androidx.recyclerview.widget.DiffUtil

class TagDiffCallback(
    private val oldList: List<Tag>,
    private val newList: List<Tag>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].text == newList[newItemPosition].text
                && oldList[oldItemPosition].isClicked == newList[newItemPosition].isClicked
    }
}