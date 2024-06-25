package com.example.effectivemobiletest.utils

import android.text.InputFilter
import android.text.Spanned


object CyrillicFilter : InputFilter {
    override fun filter(
        text: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
    ): CharSequence? {
        val previousText = dest?.subSequence(dstart, dend) ?: ""

        if (text == null) return previousText
        if (text.length == 1) {
            return if (cyrillicAlphabet.contains(text[0])) {
             text
            } else {
                previousText
            }
        } else {
            text.forEachIndexed { index, symbol ->
                if (!cyrillicAlphabet.contains(symbol)) {
                    return text.subSequence(start, index)
                }
            }
            return null
        }
    }
}


val cyrillicAlphabet = listOf(
    'А', 'а', 'Б', 'б', 'В', 'в', 'Г', 'г', 'Д', 'д', 'Е', 'е', 'Ё', 'ё', 'Ж', 'ж', 'З', 'з', 'И',
    'и', 'Й', 'й', 'К', 'к', 'Л', 'л', 'М', 'м', 'Н', 'н', 'О', 'о', 'П', 'п', 'Р', 'р', 'С', 'с',
    'Т', 'т', 'У', 'у', 'Ф', 'ф', 'Х', 'х', 'Ц', 'ц', 'Ч', 'ч', 'Ш', 'ш', 'Щ', 'щ', 'Ъ', 'ъ', 'Ы',
    'ы', 'Ь', 'ь', 'Э', 'э', 'Ю', 'ю', 'Я', 'я', ' ', '-')