/*
 * Copyright (C) 2024 murangogo
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package com.moe.moetranslator.utils

import android.content.Context
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import android.content.res.Resources

object UtilTools {
    /**
     * 将 dp 值转换为像素值 (不再需要手动初始化，直接从系统获取密度)
     */
    fun dp2px(dpValue: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dpValue * density + 0.5f).roundToInt()
    }

    /**
     * 将像素值转换为 dp 值
     */
    fun px2dp(pxValue: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (pxValue / density + 0.5f).roundToInt()
    }

    /**
     * 获取屏幕宽度（像素）
     */
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高度（像素）
     */
    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    /**
     * 计算两个字符串的相似度百分比
     */
    fun calculateSimilarity(str1: String, str2: String): Double {
        if (str1.isEmpty() && str2.isEmpty()) return 1.0 // 1.0 代表 100%
        if (str1.isEmpty() || str2.isEmpty()) return 0.0

        val distance = levenshteinDistance(str1, str2)
        val maxLength = max(str1.length, str2.length)

        return (maxLength - distance).toDouble() / maxLength
    }

    /**
     * 计算Levenshtein距离
     */
    private fun levenshteinDistance(str1: String, str2: String): Int {
        val len1 = str1.length
        val len2 = str2.length
        val dp = Array(len1 + 1) { IntArray(len2 + 1) }

        for (i in 0..len1) dp[i][0] = i
        for (j in 0..len2) dp[0][j] = j

        for (i in 1..len1) {
            for (j in 1..len2) {
                val cost = if (str1[i - 1] == str2[j - 1]) 0 else 1
                dp[i][j] = min(
                    min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                )
            }
        }

        return dp[len1][len2]
    }

    fun init(context: android.content.Context) {
    
    }
}