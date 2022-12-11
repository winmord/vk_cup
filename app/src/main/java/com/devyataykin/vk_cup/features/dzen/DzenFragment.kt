package com.devyataykin.vk_cup.features.dzen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devyataykin.vk_cup.R

class DzenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dzen, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DzenFragment()
    }
}