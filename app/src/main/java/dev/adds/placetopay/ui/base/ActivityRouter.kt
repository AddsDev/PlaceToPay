package dev.adds.placetopay.ui.base

import android.content.Context
import android.content.Intent

interface ActivityRouter {
    fun intent(activity: Context): Intent

    fun launch(activity: Context) = activity.startActivity(intent(activity))
}