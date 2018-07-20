package com.kienht.bubble_picker

import android.content.res.TypedArray
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.kienht.bubblepicker.BubblePickerListener
import com.kienht.bubblepicker.adapter.BubblePickerAdapter
import com.kienht.bubblepicker.model.BubbleGradient
import com.kienht.bubblepicker.model.PickerItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author kienht
 * @since 19/07/2018
 */
class MainActivity : AppCompatActivity() {

    lateinit var images: TypedArray
    lateinit var colors: TypedArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titles = resources.getStringArray(R.array.countries)
        colors = resources.obtainTypedArray(R.array.colors)
        images = resources.obtainTypedArray(R.array.images)

        picker.adapter = object : BubblePickerAdapter {
            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                }
            }
        }

        picker.bubbleSize = 10
        picker.listener = object : BubblePickerListener {
            override fun onBubbleDeselected(item: PickerItem) {
                toast("Unselected: " + item.title!!)
            }

            override fun onBubbleSelected(item: PickerItem) {
                toast("Selected: " + item.title!!)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        colors.resources
        images.resources
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
