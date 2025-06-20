package javaDictionaryCSC220;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Scanner;
import java.util.List;

public class Dictionary220Professional {

    private enum Entry {
        BOOK_NOUN("book", "noun", "A written work published in printed or electronic form."),
        BOOK_VERB("book", "verb", "To arrange for someone to have a seat on a plane."),
        BOOKABLE_ADJ("bookable", "adjective", "Can be ordered in advance."),
        BOOKCASE_NOUN("bookcase", "noun", "A piece of furniture with shelves."),
        BOOKBINDER_NOUN("bookbinder", "noun", "A person who fastens the pages of books."),
        CSC220_ADJ("csc220", "adjective", "Ready to create complex data structures."),
        CSC220_NOUN("csc220", "noun", "Data Structures."),
        CSC220_VERB("csc220", "verb", "To create data structures.");


        private final String key;
        private final String pos;
        private final String value;

        Entry(String key, String pos, String value) {
            this.key = key.toLowerCase();
            this.pos = pos.toLowerCase();
            this.value = value;
        }

        public String getKey() { return key; }
        public String getPartOfSpeech() { return pos; }
        public String getValue() { return value; }
    }

    public static void main(String[] args) {
        Multimap<String, Entry> dictionary = ArrayListMultimap.create();
        for (Entry e : Entry.values()) {
            dictionary.put(e.getKey(), e);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("- DICTIONARY 220 JAVA Professional -----");
        System.out.println("-----          powered by Google Guava -");

        while (true) {
            System.out.print("\nSearch: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("!q")) break;

            String[] tokens = input.split("\\s+");
            System.out.println(" |");

            if (tokens.length == 1) {
                String word = tokens[0].toLowerCase();
                if (dictionary.containsKey(word)) {
                    for (Entry entry : dictionary.get(word)) {
                        printFormatted(entry);
                    }
                } else {
                    System.out.println("  <Not found>");
                }

            } else if (tokens.length == 2) {
                String word = tokens[0].toLowerCase();
                String part = tokens[1].toLowerCase();

                if (!List.of("noun", "verb", "adjective", "distinct").contains(part)) {
                    System.out.println("  <2nd argument must be a part of speech or \"distinct\">");
                } else if (dictionary.containsKey(word)) {
                    boolean found = false;
                    for (Entry entry : dictionary.get(word)) {
                        if (entry.getPartOfSpeech().equals(part)) {
                            printFormatted(entry);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("  <Not found>");
                } else {
                    System.out.println("  <Not found>");
                }

            } else {
                System.out.println("  <2nd argument must be a part of speech or \"distinct\">");
            }

            System.out.println(" |");
        }

        System.out.println("\n-----THANK YOU-----");
    }

    private static void printFormatted(Entry entry) {
        System.out.printf("  %s [%s] : %s%n", capitalize(entry.getKey()), entry.getPartOfSpeech(), entry.getValue());
    }

    private static String capitalize(String word) {
        if (word.isEmpty()) return word;
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
