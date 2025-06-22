package info.developia.lib

import lib.Author
import lib.Book
import spock.lang.Specification

class ValidatorNestedTest extends Specification {

    def "should validate object with provided data"() {
        given:
        def author = new Author(name, surname)
        def book = new Book(title, author, pages)
        expect:
        Validator.isValid(book) == result
        Validator.is(book).valid() == result
        where:
        result | title   | name                              | surname                           | pages
//        true   | 'title' | 'name'                            | 'surname'                         | 100
//        false  | null    | 'name'                            | 'surname'                         | 100
//        false  | 'title' | null                              | 'surname'                         | 100
//        false  | 'title' | 'name'                            | null                              | 100
        false  | 'title' | 'name'                            | 'surname'                         | 101
//        false  | 'title' | 'name'                            | ''                                | 100
//        false  | 'title' | 'This is more than 10 characters' | 'This is more than 10 characters' | 100
    }
}
