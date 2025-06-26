package lib;

import info.developia.lib.annotation.NotNull;
import info.developia.lib.annotation.Number;

public class Book {
    @NotNull
    private final String title;
    private final Author author;
    @Number(min = 1, max = 100, positive = true)
    private final int pages;

    public Book(String title, Author author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
}
