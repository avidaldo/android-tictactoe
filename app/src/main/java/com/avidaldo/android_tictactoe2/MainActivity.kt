package com.avidaldo.android_tictactoe2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avidaldo.android_tictactoe2.ui.tresenrayaview.TresEnRayaViewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TresEnRayaViewFragment.newInstance())
                .commitNow()
        }
    }
}