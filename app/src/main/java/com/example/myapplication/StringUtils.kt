package com.example.myapplication

import android.text.TextUtils
import android.util.Log
import java.util.regex.Pattern

object StringUtils {

    fun formatText(content: String, maxLen: Int): String {
        if (TextUtils.isEmpty(content)) {
            return content
        }

        Log.e("Charles", "全中文=" + isFullChinese(content))
        Log.e("Charles", "全英文=" + isEnglish(content))

        val currentMaxLen: Int = if (isFullChinese(content)) {
            10
        } else {
            maxLen
        }

        var count = 0
        var endIndex = 0
        for (i in content.indices) {
            val item = content[i]
            Log.e("Charles", "char==$item")
            count = if (item.toInt() < 128) {
                count + 1
            } else {
                count + 2
            }

            if (currentMaxLen == count || item.toInt() >= 128 && currentMaxLen + 1 == count) {
                endIndex = i
                Log.e("Charles", "endIndex=$i")
            }
        }

        return if (count <= currentMaxLen) {
            content
        } else {
            content.substring(0, endIndex) + "..." //末尾添加省略号
        }
    }

    private fun isEnglish(str: String): Boolean {
        val regexp = "[a-zA-Z0-9\\s*]+"
        val pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    private fun isFullChinese(str: String): Boolean {
        val rules = "^[\\u4e00-\\u9fa5\\s*]*\$"
        val pattern = Pattern.compile(rules, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }
}
