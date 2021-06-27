package com.richardlog.spellcheck

import java.io.*
import java.io.File
import java.util.Set

class KotlinSpellChecker {

    private val DICTIONARY_WORDS = File("/usr/share/dict/words");

    fun checkSpelling(input : String) : Boolean {
        val words = readDictionary()
        for (word in input.toLowerCase().split(" ")) {
            if (!words.contains(word)) {
                println("$word is not in the dictionary");
                return false;
            }
        }
        return true;
    }

    private fun readDictionary() : Set<String> {
        val words = hashSet<String>("kotlin")
        val stream = FileInputStream(DICTIONARY_WORDS).buffered();
        try {
            val reader = InputStreamReader(stream, "UTF-8");
            reader.forEachLine( { words.add(it)} )
        } finally {
            stream.close();
        }
        return words;
    }
}

fun main(args : Array<String>) {
    val defaultInput = "defaultinput kotlin"
    val valid = KotlinSpellChecker().checkSpelling(if (args.size > 0) args[0] else defaultInput)
    println("valid? $valid")
}
