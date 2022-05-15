package dev.adds.placetopay.ui.order

import android.app.Activity
import android.content.Context
import android.content.Intent
import dev.adds.placetopay.R
import dev.adds.placetopay.ui.base.ActivityRouter

class OrderRouter : ActivityRouter{

    override fun intent(activity: Context): Intent = Intent(activity, OrderActivity::class.java)

    override fun launch(activity: Context) {
        activity.startActivity(intent(activity))
        (activity as Activity).overridePendingTransition(R.anim.slide_up, R.anim.slide_out)
    }
}