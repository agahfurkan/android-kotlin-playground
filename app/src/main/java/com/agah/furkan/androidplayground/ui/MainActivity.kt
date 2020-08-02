package com.agah.furkan.androidplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}