package com.tribal.tribalphotos.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.gson.Gson

inline fun <reified T : Any> Any?.mapTo(newClass: Class<T>): T? =
    Gson().run {
        fromJson(toJson(this@mapTo), newClass)
    }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
