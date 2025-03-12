package info.developia.lib

import lib.Author
import spock.lang.Specification

class ValidatorTest extends Specification {

    def "should validate object with provided data"() {
        expect:
        def author = new Author(name, surname)
        Validator.isValid(author) == result
        Validator.is(author).valid() == result
        where:
        result | name    | surname
        true   | 'title' | 'author'
        false  | 'title' | 'a'
        false  | 'title' | null
        false  | 'title' | 'This is more than 10 characters'
        false  | null    | 'author'
    }

    def "should validate object"() {
        given:
        def author = new Author('name', 'surname')
        when:
        def validation = Validator.is(author)
        then:
        validation.valid()
        !validation.hasErrors()
    }
}
