# Bubble-Picker

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![](https://jitpack.io/v/hantrungkien/Bubble-Picker.svg)](https://jitpack.io/#hantrungkien/Bubble-Picker)

Read how we did it [on Medium](https://medium.com/@igalata13/how-to-create-a-bubble-selection-animation-on-android-627044da4854#.ajonc010b)

<img src="shot.gif"/>

## Requirements
- Android SDK 16+

## Usage

Add to your root build.gradle:
```Groovy
allprojects {
	repositories {
	...
	maven { url "https://jitpack.io" }
	}
}
```

Add the dependency:
```Groovy
dependencies {
	implementation 'com.github.hantrungkien:Bubble-Picker:v1.0.5.2'
}
```

## How to use this library

This library was developed and extended based on https://github.com/igalata/Bubble-Picker

## Please review Activity in the Sample for both cases 

- [Sync Data](https://github.com/hantrungkien/Bubble-Picker/blob/master/app/src/main/java/com/kienht/bubble_picker/SyncActivity.kt)

- [Async Data](https://github.com/hantrungkien/Bubble-Picker/blob/master/app/src/main/java/com/kienht/bubble_picker/AsyncActivity.kt)

## Now: 
- We can set Picker Item selected always is true   
```
picker.isAlwaysSelected = false

//or

mBubblePicker.setAlwaysSelected(true);

```

- We can set swipe move speed value (default = 1.5f)
```
picker.swipeMoveSpeed = 2f

//or

mBubblePicker.setSwipeMoveSpeed(2f);

```

- [Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader): Load image from url

```

pickerItem.setIsUseImgUrl(true);
pickerItem.setImgUrl(...)

//use Drawable
pickerItem.setIsUseImgUrl(false);
pickerItem.setImgDrawable(...)

```

Add `BubblePicker` to your xml layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/white"
    android:orientation="vertical">

    <com.kienht.bubblepicker.rendering.BubblePicker
        android:id="@+id/picker"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:backgroundColor="@android:color/white" />

</LinearLayout>
```

Specify the `BubblePickerListener` to get notified about events

Kotlin
```kotlin
picker.listener = object : BubblePickerListener {
            override fun onBubbleSelected(item: PickerItem) {

            }

            override fun onBubbleDeselected(item: PickerItem) {

            }
}
```

Java
```java
picker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem item) {
                
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem item) {

            }
});
```

To get all selected items use `picker.selectedItems` variable in Kotlin or `picker.getSelectedItems()` method in Java.

For more usage examples please review the sample app

## Thanks

A special thanks go to [Mr. Lopspower](https://github.com/igalata)

## Known iOS versions of the animation

* https://github.com/Ronnel/BubblePicker
* https://github.com/efremidze/Magnetic

## LICENCE

    Copyright 2018 Kien Han Trung

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
