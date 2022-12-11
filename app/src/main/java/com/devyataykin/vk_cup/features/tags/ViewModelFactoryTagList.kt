package com.devyataykin.vk_cup.features.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryTagList(): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        TagViewModel::class.java -> TagViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}