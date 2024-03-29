package com.company.app.utils

import android.util.Log
import com.company.app.BuildConfig

class Logger {

    companion object {
        fun i(name: String, msg: String) {
            if (BuildConfig.SHOW_LOG) {
                Log.i(name, msg)
            }
        }

        fun i(s: String) {
            if (BuildConfig.SHOW_LOG) {
                val maxLogSize = 1000
                for (i in 0..s.length / maxLogSize) {
                    val start = i * maxLogSize
                    var end = (i + 1) * maxLogSize
                    end = if (end > s.length) s.length else end
                    Log.v("---------------Mine: ", s.substring(start, end))
                }
            }
        }
    }

}