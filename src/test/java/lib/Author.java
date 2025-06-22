package lib;

import info.developia.lib.annotation.Length;
import info.developia.lib.annotation.NotNull;

public record Author(
        @NotNull String name,
        @Length(min = 3, max = 10) String surname
) {
}