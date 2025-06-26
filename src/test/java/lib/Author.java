package lib;

import info.developia.lib.annotation.Length;
import info.developia.lib.annotation.NotNull;

public class Author {
    @NotNull
    private final String name;
    @Length(min = 3, max = 10)
    private final String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}