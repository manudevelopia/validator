package info.developia.lib

import lib.Author
import lib.Book
import spock.lang.Specification

class ValidatorNestedTest extends Specification {

    def "should validate object with provided data"() {
        given:
        def author = new Author(name, surname)
        def book = new Book(title, author)
        expect:
        Validator.isValid(book) == result
//        Validator.is(book).valid() == result
        where:
        result | title   | name                              | surname
        true   | 'title' | 'name'                            | 'surname'
        false  | 'title' | 'name'                            | ''
        false  | 'title' | null                              | 'surname'
        false  | 'title' | 'name'                            | null
        false  | 'title' | 'This is more than 10 characters' | 'This is more than 10 characters'
        false  | null    | 'name'                            | 'surname'
    }
}
