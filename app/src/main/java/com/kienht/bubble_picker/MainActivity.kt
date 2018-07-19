package com.kienht.bubble_picker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titles = resources.getStringArray(R.array.countries)
        val colors = resources.obtainTypedArray(R.array.colors)
        val images = resources.obtainTypedArray(R.array.images)

        picker.isAlwaysSelected = false
        picker.adapter = object : BubblePickerAdapter {
            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    isUseImgUrl = false
                    imgDrawable = ContextCompat.getDrawable(this@MainActivity, images.getResourceId(position, 0))
                }
            }
        }

        colors.recycle()
        images.recycle()

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
        if (!picker.isResumed) {
            picker.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
