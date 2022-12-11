package com.devyataykin.vk_cup.features.tags

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devyataykin.vk_cup.data.TagData
import com.devyataykin.vk_cup.model.Tag

class TagViewModel(): ViewModel() {
    var tagList: MutableLiveData<List<Tag>> = MutableLiveData()

    init {
        tagList.value = TagData.getTags()
    }

    fun getListTags() = tagList
    fun initTags() {
        tagList.value = TagData.getTags()
    }
}