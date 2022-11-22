package com.owl_laugh_at_wasted_time.mytestmobile.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owl_laugh_at_wasted_time.mytestmobile.Initializer
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.ActivityMainBinding
import com.owl_laugh_at_wasted_time.mytestmobile.presentation.delegates.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    val component by lazy {
        Initializer.component(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}