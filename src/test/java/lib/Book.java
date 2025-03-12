package lib;

import info.developia.lib.annotation.NotNull;

public class Book {
    @NotNull
    private final String title;
    private final Author author;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }
}
