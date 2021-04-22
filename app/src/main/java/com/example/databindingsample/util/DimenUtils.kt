package com.example.databindingsample.util

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

object DimenUtils {
    @JvmStatic
    fun main(args: Array<String>) {
        val stringBuilder = StringBuilder()
        //Add the tag at the beginning of xml
        val xmlStart = """<?xml version="1.0" encoding="utf-8"?>
        <resources>"""

        stringBuilder.append(xmlStart)

        // For Dp
        for (i in 0..200) {
            val start = "<dimen name=\"dp_$i\">$i" + "dp</dimen>" + "\n"
            stringBuilder.append(start)
        }

        stringBuilder.append("\n\n\n\n")

        // For sp
        for (i in 0..200) {
            val start = "<dimen name=\"sp_$i\">$i" + "sp</dimen>" + "\n"
            stringBuilder.append(start)
        }

        stringBuilder.append("</resources>")

        val sw400file = "./app/src/main/res/values/dimens.xml"
        writeFile(sw400file, stringBuilder.toString())
    }

    private fun writeFile(file: String?, text: String?) {
        var out: PrintWriter? = null
        try {
            out = PrintWriter(BufferedWriter(FileWriter(file)))
            out.println(text)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        out?.close()
    }
}