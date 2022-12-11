package com.devyataykin.vk_cup.features.tags

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devyataykin.vk_cup.R
import com.devyataykin.vk_cup.model.Tag

class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tagText: TextView? = itemView.findViewById(R.id.tag_text)
    private val tagLayout: LinearLayout? = itemView.findViewById(R.id.tag_view_holder)
    private val tagDivider: View? = itemView.findViewById(R.id.tag_divider)
    private val tagIcon: ImageView? = itemView.findViewById(R.id.tag_icon)

    fun bind(tag: Tag, listener: Listener) {
        itemView.setOnClickListener {
            tag.isClicked = !tag.isClicked

            val colorFrom: Int
            val colorTo: Int
            val tagIconId: Int

            if (tag.isClicked) {
                colorFrom = R.color.tag_color
                colorTo = R.color.tag_selected_color
                tagIconId = R.drawable.ic_baseline_check_24
                tagDivider?.visibility = View.INVISIBLE
            } else {
                colorFrom = R.color.tag_selected_color
                colorTo = R.color.tag_color
                tagIconId = R.drawable.ic_baseline_add_24
                tagDivider?.visibility = View.VISIBLE
            }

            val colorAnimation =
                ValueAnimator.ofObject(ArgbEvaluator(), getColor(colorFrom), getColor(colorTo))
            colorAnimation.duration = 450

            colorAnimation.addUpdateListener { animator ->
                tagLayout?.backgroundTintList =
                    ColorStateList.valueOf(animator.animatedValue as Int)
            }
            colorAnimation.start()
            tagIcon?.setImageResource(tagIconId)

            listener.onClick(tag)
        }

        setViews(tag)
    }

    private fun setViews(tag: Tag) {
        val isClicked = tag.isClicked

        tagText?.text = tag.text
        tagIcon?.setImageResource(if (isClicked) R.drawable.ic_baseline_check_24 else R.drawable.ic_baseline_add_24)
        tagLayout?.backgroundTintList =
            ColorStateList.valueOf(
                getColor(if (isClicked) R.color.tag_selected_color else R.color.tag_color)
            )
        tagDivider?.visibility = if (isClicked) View.INVISIBLE else View.VISIBLE
    }

    private fun getColor(id: Int): Int = ContextCompat.getColor(
        itemView.context,
        id
    )

    interface Listener {
        fun onClick(tag: Tag)
    }
}