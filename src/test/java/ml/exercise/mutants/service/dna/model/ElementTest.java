package ml.exercise.mutants.service.dna.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementTest {

    private Element target;

    @Test
    public void should_verify_existing_element() {
        // given
        char a = 'a';
        target = new Element(a);

        // when
        int count = target.verify(a);

        // then
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void should_verify_new_element() {
        // given
        char a = 'a';
        char b = 'b';
        target = new Element(a);

        // when
        int count = target.verify(b);

        // then
        assertThat(count).isEqualTo(1);
    }

}