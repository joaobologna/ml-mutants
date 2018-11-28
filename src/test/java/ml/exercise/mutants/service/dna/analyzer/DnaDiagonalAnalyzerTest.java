package ml.exercise.mutants.service.dna.analyzer;

import ml.exercise.mutants.service.dna.model.Element;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class DnaDiagonalAnalyzerTest {

    @InjectMocks
    private DnaDiagonalAnalyzer target;

    @Spy
    private ArrayList<Element> diagonals;

    @Mock
    private Element element;

    @Captor
    private ArgumentCaptor<Element> argument;

    @Test
    public void should_create_element_for_main_diagonal() {
        // when
        target.analyseEntry(3, 1, 1, 'a');

        // then
        verify(diagonals, times(1)).add(argument.capture());
        assertThat(argument.getValue()).extracting(Element::getValue).isEqualTo('a');
    }

    @Test
    public void should_create_element_for_upper_triangle() {
        // given
        diagonals.add(new Element('x'));

        // when
        target.analyseEntry(3, 1, 2, 'a');

        // then
        verify(diagonals, times(2)).add(argument.capture());
        assertThat(argument.getValue()).extracting(Element::getValue).isEqualTo('a');
    }

    @Test
    public void should_create_element_for_lower_triangle() {
        // given
        diagonals.add(new Element('x'));
        diagonals.add(new Element('y'));

        // when
        target.analyseEntry(3, 2, 1, 'a');

        // then
        verify(diagonals, times(3)).add(argument.capture());
        assertThat(argument.getValue()).extracting(Element::getValue).isEqualTo('a');
    }

    @Test
    public void should_search_for_element_on_main_diagonal_if_present() {
        // given
        diagonals.add(element);
        diagonals.add(new Element('a'));
        diagonals.add(new Element('b'));

        // when
        target.analyseEntry(3, 1, 1, 'x');

        // then
        verify(element, times(1)).verify('x');
    }

    @Test
    public void should_search_for_element_on_upper_diagonal_if_present() {
        // given
        diagonals.add(new Element('a'));
        diagonals.add(element);
        diagonals.add(new Element('b'));

        // when
        target.analyseEntry(3, 1, 2, 'x');

        // then
        verify(element, times(1)).verify('x');
    }

    @Test
    public void should_search_for_element_on_lower_diagonal_if_present() {
        // given
        diagonals.add(new Element('a'));
        diagonals.add(new Element('b'));
        diagonals.add(new Element('c'));
        diagonals.add(element);

        // when
        target.analyseEntry(3, 1, 0, 'x');

        // then
        verify(element, times(1)).verify('x');
    }

}