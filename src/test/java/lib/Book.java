package lib;

import info.developia.lib.annotation.NotNull;
import info.developia.lib.annotation.Number;

public record Book(
        @NotNull String title,
        Author author,
        @Number(min = 1, max = 1000, positive = true) int pages
) {
}
