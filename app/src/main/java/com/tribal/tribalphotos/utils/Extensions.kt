package com.tribal.tribalphotos.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.annotation.LayoutRes
import com.google.gson.Gson

inline fun <reified T : Any> Any?.mapTo(newClass: Class<T>): T? =
    Gson().run {
        fromJson((this@mapTo).toString(), newClass)
    }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.makeVisibleAlpha(duration: Long, endAction: () -> Unit = {}) {
    alpha = 0f
    makeVisible()
    animate().alpha(1f).setDuration(duration).setInterpolator(AccelerateInterpolator()).withEndAction {
        endAction.invoke()
    }
}

fun View.makeGoneAlpha(duration: Long, endAction: () -> Unit = {}) {
    animate().alpha(0f).setDuration(duration).setInterpolator(AccelerateInterpolator()).withEndAction {
        endAction.invoke()
    }
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}
