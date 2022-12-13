package com.devyataykin.vk_cup.features.tags

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.devyataykin.vk_cup.*
import com.devyataykin.vk_cup.model.Tag
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.button.MaterialButton

class FragmentTagList : Fragment() {
    private var recycler: RecyclerView? = null
    private var adapter: TagAdapter? = null
    private var continueButton: MaterialButton? = null
    private var tags: MutableList<Tag> = mutableListOf()
    private val viewModel: TagViewModel by viewModels { ViewModelFactoryTagList() }

    private var listener: ContinueButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ContinueButtonClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continueButton = view.findViewById(R.id.continue_button)
        continueButton?.setOnClickListener{
            listener?.onContinueClicked()
        }

        recycler = view.findViewById(R.id.tags_list_recycler_view)
        adapter = TagAdapter(object : TagViewHolder.Listener {
            override fun onClick(tag: Tag) {
                tags.sortBy { !it.isClicked }
                updateData(tags)
            }
        })

        recycler?.adapter = adapter
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.layoutManager = FlexboxLayoutManager(context)

        viewModel.getListTags().observe(this.viewLifecycleOwner) {
            updateData(it)
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    private fun setContinueButtonVisibility() {
        continueButton?.visibility =
            if (tags.any { it.isClicked }) View.VISIBLE else View.GONE
    }

    interface ContinueButtonClickListener {
        fun onContinueClicked()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(repoTags: List<Tag>) {
        tags = repoTags.toMutableList()
        tags.sortBy { !it.isClicked }
        setContinueButtonVisibility()
        adapter?.bindTags(tags)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTagList()
    }
}