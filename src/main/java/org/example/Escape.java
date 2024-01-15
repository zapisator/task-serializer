package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static org.example.Ascii.FIRST_PRINTABLE_INDEX;

@Getter
@AllArgsConstructor
public enum Escape {
    FREQUENCIES((char) (FIRST_PRINTABLE_INDEX)),
    CONSEQUENCES((char) (FIRST_PRINTABLE_INDEX + 1)),
    SOLITARIES_ONE_SYMBOL((char) (FIRST_PRINTABLE_INDEX + 2)),
    SOLITARIES_TWO_SYMBOL((char) (FIRST_PRINTABLE_INDEX + 3));

    private final char character;

    public static Escape fromCharacter(char c) {
        return Arrays.stream(Escape.values())
                .filter(escape -> escape.getCharacter() == c)
                .findFirst()
                .orElseThrow();
    }

}
