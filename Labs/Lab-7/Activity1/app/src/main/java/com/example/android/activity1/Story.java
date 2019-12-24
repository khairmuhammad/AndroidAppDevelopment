package com.example.android.activity1;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Story implements Serializable {
    private String text;                 // text of the story
    private List<String> placeholders;   // list of placeholders to fill in
    private int filledIn;                // number of placeholders that have been filled in
    private boolean htmlMode;            // set to true to surround placeholders with <b></b> tags

    {
        // instance initializer; runs before any constructor
        text = "";
        placeholders = new ArrayList<String>();
        filledIn = 0;
        htmlMode = true;
        clear();
    }

    /** constructs a new empty Story */
    public Story() {
        // empty
    }

    /** constructs a new Story reading its text from the given input stream */
    public Story(InputStream stream) {
        read(stream);
    }

    /** constructs a new Story reading its text from the given Scanner */
    public Story(Scanner input) {
        read(input);
    }

    /** resets the story back to an empty initial state */
    public void clear() {
        text = "";
        placeholders.clear();
        filledIn = 0;
    }

    /** replaces the next unfilled placeholder with the given word */
    public void fillInPlaceholder(String word) {
        if (!isFilledIn()) {
            text = text.replace("<" + filledIn + ">", word);
            filledIn++;
        }
    }

    /** returns the next placeholder such as "adjective",
     *  or empty string if story is completely filled in already */
    public String getNextPlaceholder() {
        if (isFilledIn()) {
            return "";
        } else {
            return placeholders.get(filledIn);
        }
    }

    /** returns total number of placeholders in the story */
    public int getPlaceholderCount() {
        return placeholders.size();
    }

    /** returns how many placeholders still need to be filled in */
    public int getPlaceholderRemainingCount() {
        return placeholders.size() - filledIn;
    }

    /** returns true if all placeholders have been filled in */
    public boolean isFilledIn() {
        return filledIn >= placeholders.size();
    }

    /** reads initial story text from the given input stream */
    public void read(InputStream stream) {
        read(new Scanner(stream));
    }

    /** reads initial story text from the given Scanner */
    public void read(Scanner input) {
        while (input.hasNext()) {
            String word = input.next();
            if (word.startsWith("<") && word.endsWith(">")) {
                // a placeholder; replace with e.g. "<0>" so I can find/replace it easily later
                // (make them bold so that they stand out!)
                if (htmlMode) {
                    text += " <b><" + placeholders.size() + "></b>";
                } else {
                    text += " <" + placeholders.size() + ">";
                }
                // "<plural-noun>" becomes "plural noun"
                String placeholder = word.substring(1, word.length() - 1).replace("-", " ");
                placeholders.add(placeholder);
            } else {
                // a regular word; just concatenate
                if (!text.isEmpty()) {
                    text += " ";
                }
                text += word;
            }
        }
    }

    /** returns story text */
    public String toString() {
        return text;
    }
}