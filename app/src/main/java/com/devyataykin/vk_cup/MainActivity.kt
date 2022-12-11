package com.devyataykin.vk_cup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devyataykin.vk_cup.features.dzen.DzenFragment
import com.devyataykin.vk_cup.features.tags.FragmentTagList

class MainActivity : AppCompatActivity(), FragmentTagList.ContinueButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.frame_layout, FragmentTagList.newInstance())
            }.commit()
    }

    override fun onContinueClicked() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame_layout,
                DzenFragment.newInstance(),
                DzenFragment::class.java.simpleName
            )
            .addToBackStack("trans:${DzenFragment::class.java.simpleName}")
            .commit()
    }
}