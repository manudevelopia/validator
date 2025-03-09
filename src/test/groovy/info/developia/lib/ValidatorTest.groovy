package info.developia.lib

import info.developia.lib.annotation.Length
import info.developia.lib.annotation.NotNull
import spock.lang.Specification

class ValidatorTest extends Specification {

    class Book {
        @NotNull
        String title
        @Length(min = 3, max = 10)
        String author
    }

    def "should validate object"() {
        when:
        def book = new Book(title: "title", author: "author")
        then:
        Validator.is(book).valid()
    }
}
