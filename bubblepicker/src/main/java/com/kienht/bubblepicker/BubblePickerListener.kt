package com.kienht.bubblepicker

import com.kienht.bubblepicker.model.PickerItem

/**
 * Created by irinagalata on 3/6/17.
 */
interface BubblePickerListener {

    fun onBubbleSelected(item: PickerItem)

    fun onBubbleDeselected(item: PickerItem)

}