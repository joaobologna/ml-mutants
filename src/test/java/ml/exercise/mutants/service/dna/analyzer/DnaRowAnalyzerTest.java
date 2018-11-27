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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class DnaRowAnalyzerTest {

    @InjectMocks
    private DnaRowAnalyzer target;

    @Spy
    private ArrayList<Element> rows;

    @Mock
    private Element element;

    @Captor
    private ArgumentCaptor<Element> argument;

    @Test
    public void should_create_new_entry() {
        // given
        rows.add(new Element('x'));
        rows.add(new Element('y'));

        // when
        target.analyseEntry(1, 2, 0, 'a');

        verify(rows, times(3)).add(argument.capture());
        Assertions.assertThat(argument.getValue()).extracting(Element::getValue).isEqualTo('a');
    }

    @Test
    public void should_get_entry_from_list() {
        // given
        rows.add(element);
        // when
        target.analyseEntry(1, 0, 0, 'a');

        verify(element, times(1)).verify('a');
    }

}