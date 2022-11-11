package com.owl_laugh_at_wasted_time.mytestmobile

import android.content.Context
import com.owl_laugh_at_wasted_time.mytestmobile.di.AppComponent
import com.owl_laugh_at_wasted_time.mytestmobile.di.DaggerAppComponent

object Initializer {
    fun component(context: Context): AppComponent {
        return DaggerAppComponent.factory().create(context)
    }
}