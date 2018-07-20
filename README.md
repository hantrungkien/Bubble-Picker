# Bubble-Picker

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
	implementation 'com.github.hantrungkien:Bubble-Picker:v1.0.5'
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

A special thanks go to [Mr. Igalata](https://github.com/igalata)

## Known iOS versions of the animation

* https://github.com/Ronnel/BubblePicker
* https://github.com/efremidze/Magnetic

## License

MIT License

Copyright (c) 2017 Kien Han Trung

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
