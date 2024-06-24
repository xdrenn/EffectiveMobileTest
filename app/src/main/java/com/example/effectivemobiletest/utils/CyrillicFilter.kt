package com.example.effectivemobiletest.utils

import java.util.regex.Pattern


object CyrillicFilter {

    fun isCyrillic(text : String) : CharSequence? {
        val pattern = Pattern.compile("[\\u0400-\\u04FF]")
        return if(pattern.matcher(text).find()){
            text
        } else {
            return null
        }
    }
}