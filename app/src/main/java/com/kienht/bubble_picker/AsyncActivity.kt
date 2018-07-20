package com.kienht.bubble_picker

import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kienht.bubblepicker.BubblePickerListener
import com.kienht.bubblepicker.adapter.BubblePickerAdapter
import com.kienht.bubblepicker.model.BubbleGradient
import com.kienht.bubblepicker.model.PickerItem
import com.kienht.bubblepicker.rendering.BubblePicker

/**
 * @author kienht
 * @since 20/07/2018
 */
class AsyncActivity : AppCompatActivity(), BubblePickerListener {

    lateinit var images: TypedArray
    lateinit var colors: TypedArray

    private var picker: BubblePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.async_activity)

        val titles = resources.getStringArray(R.array.countries)
        colors = resources.obtainTypedArray(R.array.colors)
        images = resources.obtainTypedArray(R.array.images)

        Handler().postDelayed({
            picker = BubblePicker(this, null)
            picker!!.adapter = object : BubblePickerAdapter {
                override val totalCount = titles.size

                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
                        title = titles[position]
                        gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                                colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    }
                }
            }

            picker!!.bubbleSize = 10
            picker!!.listener = this@AsyncActivity

            setContentView(picker)

        }, 3000)
    }

    override fun onResume() {
        super.onResume()
        if (picker != null) {
            picker!!.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (picker != null) {
            picker!!.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        colors.resources
        images.resources
    }

    override fun onBubbleSelected(item: PickerItem) {
        toast("Selected: " + item.title!!)
    }

    override fun onBubbleDeselected(item: PickerItem) {
        toast("Unselected: " + item.title!!)
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}