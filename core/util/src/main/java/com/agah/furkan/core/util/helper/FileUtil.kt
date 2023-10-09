package com.agah.furkan.core.util.helper

import android.content.Context
import java.io.IOException
import java.io.InputStreamReader

object FileUtil {
    fun readStringFromAssets(context: Context, fileName: String): String {
        try {
            val inputStream = context.assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, Charsets.UTF_8)
            reader.use {
                it.readLines().forEach { line ->
                    builder.append(line)
                }
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}