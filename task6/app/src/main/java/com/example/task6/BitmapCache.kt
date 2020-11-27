package com.example.task6

import android.graphics.Bitmap
import android.util.LruCache


public class BitmapCache {
    private lateinit var memoryCache: LruCache<String, Bitmap>
    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8

        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }
    }

    public fun getBitmap(url: String?): Bitmap? {
        return memoryCache.get(url)
    }
    public fun putBitmap(url: String?, image: Bitmap){
        memoryCache.put(url, image)
    }
}

