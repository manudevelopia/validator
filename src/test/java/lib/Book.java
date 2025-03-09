package lib;

import info.developia.lib.annotation.Length;
import info.developia.lib.annotation.NotNull;

public class Book {
    @NotNull
    private final String title;
    @Length(min = 3, max = 10)
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
