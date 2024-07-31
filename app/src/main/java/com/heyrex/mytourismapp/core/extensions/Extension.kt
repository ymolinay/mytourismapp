package com.heyrex.mytourismapp.core.extensions

import android.content.Context
import android.util.DisplayMetrics
import com.heyrex.mytourismapp.BuildConfig

fun getAppVersion(): String {
    val fullVersionName = BuildConfig.VERSION_NAME
    return fullVersionName.split("-")[0]
}

fun storeVersionIsGreaterThanAppVersion(serverVersion: String, appVersion: String): Boolean {
    val serverVersionSplit = serverVersion.split(".").map { it.toInt() }
    val appVersionSplit = appVersion.split(".").map { it.toInt() }
    for (i in 0 until minOf(serverVersionSplit.size, appVersionSplit.size)) {
        if (serverVersionSplit[i] > appVersionSplit[i]) {
            return true
        } else if (serverVersionSplit[i] == appVersionSplit[i]) {
            continue
        } else {
            return false
        }
    }
    return serverVersionSplit.size > appVersionSplit.size
}

fun Int.toPx(context: Context): Int {
    return this * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}