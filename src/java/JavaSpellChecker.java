package com.richardlog.spellcheck;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class JavaSpellChecker {

    private static final File DICTIONARY_WORDS = new File("/usr/share/dict/words");

    boolean checkSpelling(String input) throws IOException {
        Set<String> words = readDictionary();
        for (String word : input.toLowerCase().split(" ")) {
            if (!words.contains(word)) {
                System.out.println(word + " is not in the dictionary");
                return false;
            }
        }
        return true;
    }

    private Set<String> readDictionary() throws IOException {
        Set<String> words = new HashSet<String>();
        words.add("java");
        InputStream stream = new FileInputStream(DICTIONARY_WORDS);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String word;
            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
        } finally {
            stream.close();
        }
        return words;
    }

    public static void main(String[] args) throws IOException {
        String defaultInput = "yeah, java";
        boolean valid = new JavaSpellChecker().checkSpelling(args.length > 0 ? args[0] : defaultInput);
        System.out.println("valid? " + valid);
    }
}
