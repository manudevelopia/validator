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

    def "should validate object with provided data"() {
        expect:
        def book = new Book(title: title, author: author)
        Validator.isValid(book) == result
        where:
        result | title   | author
        true   | 'title' | 'author'
        false  | 'title' | 'a'
        false  | 'title' | null
        false  | 'title' | 'This is more than 10 characters'
        false  | null    | 'author'
    }

    def "should validate object"() {
        given:
        def book = new Book(title: "title", author: "author")
        when:
        def validation = Validator.is(book)
        then:
        validation.valid()
        !validation.hasErrors()
    }
}
