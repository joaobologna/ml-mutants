package ml.exercise.mutants.service.dna.analyzer;

import ml.exercise.mutants.service.dna.model.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
public class DnaDiagonalAnalyzerTest {

    @InjectMocks
    private DnaDiagonalAnalyzer target;

    @Spy
    private ArrayList<Element> diagonals;

    @Test
    public void should_create_element_principal_diagonal() {
        // when
        target.analyseEntry(3, 1, 1, 'a');
    }

}