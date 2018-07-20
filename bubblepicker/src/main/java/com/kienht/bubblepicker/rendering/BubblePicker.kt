package com.kienht.bubblepicker.rendering

import android.content.Context
import android.graphics.PixelFormat
import android.opengl.GLSurfaceView
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import com.kienht.bubblepicker.BubblePickerListener
import com.kienht.bubblepicker.R
import com.kienht.bubblepicker.adapter.BubblePickerAdapter
import com.kienht.bubblepicker.model.Color
import com.kienht.bubblepicker.model.PickerItem

/**
 * Created by irinagalata on 1/19/17.
 */
class BubblePicker(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {
    private lateinit var renderer: PickerRenderer

    @ColorInt
    var background: Int = 0
        set(value) {
            field = value
            renderer.backgroundColor = Color(value)
        }

    var datas: List<PickerItem> = ArrayList()
        set(value) {
            field = value
            renderer.pickerList = value
            super.onResume()
        }

    var adapter: BubblePickerAdapter? = null
        set(value) {
            field = value
            if (value != null) {
                renderer.pickerList = ArrayList((0 until value.totalCount)
                        .map { value.getItem(it) }.toList())
            }
            super.onResume()
        }

    var maxSelectedCount: Int? = null
        set(value) {
            renderer.maxSelectedCount = value
        }

    var listener: BubblePickerListener? = null
        set(value) {
            renderer.listener = value
        }

    var bubbleSize = 50
        set(value) {
            if (value in 1..100) {
                renderer.bubbleSize = value
            }
        }

    val selectedItems: List<PickerItem?>
        get() = renderer.selectedItems

    var centerImmediately = false
        set(value) {
            field = value
            renderer.centerImmediately = value
        }

    var isAlwaysSelected = true
        set(value) {
            field = value
            renderer.isAlwaysSelected = value
        }

    var swipeMoveSpeed = 1.5f

    private var startX = 0f
    private var startY = 0f
    private var previousX = 0f
    private var previousY = 0f

    init {
        init()
        attrs?.let { retrieveAttrubutes(attrs) }
    }

    private fun init() {
        renderer = PickerRenderer(this)

        setZOrderOnTop(true)
        setEGLContextClientVersion(2)
        setEGLConfigChooser(8, 8, 8, 8, 16, 0)
        holder.setFormat(PixelFormat.RGBA_8888)
        setRenderer(renderer)
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                previousX = event.x
                previousY = event.y
            }
            MotionEvent.ACTION_UP -> {
                if (isClick(event)) renderer.resize(event.x, event.y)
                renderer.release()
            }
            MotionEvent.ACTION_MOVE -> {
                if (isSwipe(event)) {
                    renderer.swipe((previousX - event.x) * swipeMoveSpeed, (previousY - event.y) * swipeMoveSpeed)
                    previousX = event.x
                    previousY = event.y
                } else {
                    release()
                }
            }
            else -> release()
        }

        return true
    }

    private fun release() = postDelayed({ renderer.release() }, 0)

    private fun isClick(event: MotionEvent) = Math.abs(event.x - startX) < 20 && Math.abs(event.y - startY) < 20

    private fun isSwipe(event: MotionEvent) = Math.abs(event.x - previousX) > 20 && Math.abs(event.y - previousY) > 20

    private fun retrieveAttrubutes(attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.BubblePicker)

        if (array.hasValue(R.styleable.BubblePicker_maxSelectedCount)) {
            maxSelectedCount = array.getInt(R.styleable.BubblePicker_maxSelectedCount, -1)
        }

        if (array.hasValue(R.styleable.BubblePicker_backgroundColor)) {
            background = array.getColor(R.styleable.BubblePicker_backgroundColor, -1)
        }

        array.recycle()
    }

    override fun onResume() {
        if (renderer.pickerList.isNotEmpty()) {
            super.onResume()
        }
    }

    override fun onPause() {
        if (renderer.pickerList.isNotEmpty()) {
            super.onPause()
            renderer.clear()
        }
    }

}
