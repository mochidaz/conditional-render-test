package com.example.lambdadelta

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

public fun getScreenSize(context: Context): Pair<Int, Int> {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels
    return Pair(width, height)
}
