package com.kienht.bubble_picker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author kienht
 * @since 19/07/2018
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button_sync_activity.setOnClickListener({
            val intent = Intent(this@MainActivity, SyncActivity::class.java)
            startActivity(intent)
        })

        button_async_activity.setOnClickListener({
            val intent = Intent(this@MainActivity, AsyncActivity::class.java)
            startActivity(intent)
        })
    }

}
