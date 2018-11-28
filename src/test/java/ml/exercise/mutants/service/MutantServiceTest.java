package ml.exercise.mutants.service;

import ml.exercise.mutants.service.dna.repository.DnaRepository;
import ml.exercise.mutants.service.dna.analyzer.DnaAnalyzer;
import ml.exercise.mutants.service.dna.analyzer.DnaColumnAnalyzer;
import ml.exercise.mutants.service.dna.analyzer.DnaDiagonalAnalyzer;
import ml.exercise.mutants.service.dna.analyzer.DnaRowAnalyzer;
import ml.exercise.mutants.service.mutant.MutantService;
import ml.exercise.mutants.service.mutant.model.MutantStats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantServiceTest {

    @InjectMocks
    private MutantService target;

    @Mock
    private DnaRepository dnaRepository;

    @Captor
    private ArgumentCaptor<Boolean> captor;

    @Spy
    private ArrayList<DnaAnalyzer> analyzers;

    @Mock
    private DnaRowAnalyzer dnaRowAnalyzer;

    @Mock
    private DnaColumnAnalyzer dnaColumnAnalyzer;

    @Mock
    private DnaDiagonalAnalyzer dnaDiagonalAnalyzer;

    @Before
    public void setup() {
        analyzers.add(dnaColumnAnalyzer);
        analyzers.add(dnaRowAnalyzer);
        analyzers.add(dnaDiagonalAnalyzer);
    }

    @Test
    public void should_invoke_mutants_and_human_count() {
        // when
        target.stats();

        // then
        verify(dnaRepository, times(2)).countByMutant(captor.capture());
        assertThat(captor.getAllValues()).contains(true, false);
    }

    @Test
    public void should_calc_mutant_and_human_count_and_ratio() {
        // given
        long humans = 10;
        long mutants = 5;
        when(dnaRepository.countByMutant(false)).thenReturn(humans);
        when(dnaRepository.countByMutant(true)).thenReturn(mutants);

        // when
        MutantStats stats = target.stats();

        // then
        assertThat(stats).extracting(MutantStats::getHumans).isEqualTo(humans);
        assertThat(stats).extracting(MutantStats::getMutants).isEqualTo(mutants);
        assertThat(stats).extracting(MutantStats::getRatio).isEqualTo(1f * mutants / humans);
    }

    @Test
    public void should_invoke_add_entry_method_for_every_element() {
        // given
        char[][] multi = new char[][]{
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'}
        };

        // when
        target.isMutant(multi, multi.length);

        // then
        for (int i = 0; i < multi.length; i++) {
            for (int j = 0; j < multi.length; j++) {
                verify(dnaRowAnalyzer).addEntry(multi.length, i, j, multi[i][j]);
                verify(dnaColumnAnalyzer).addEntry(multi.length, i, j, multi[i][j]);
                verify(dnaDiagonalAnalyzer).addEntry(multi.length, i, j, multi[i][j]);
            }
        }
    }

    @Test
    public void should_define_mutant_if_sum_of_sequencs_is_greater_or_equal_then_property_value() {
        // given
        ReflectionTestUtils.setField(target, "requiredDnaSequences", 3);
        when(dnaRowAnalyzer.getSequences()).thenReturn(1);
        when(dnaColumnAnalyzer.getSequences()).thenReturn(1);
        when(dnaDiagonalAnalyzer.getSequences()).thenReturn(1);

        char[][] multi = new char[][]{
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'}
        };

        // when
        boolean response = target.isMutant(multi, multi.length);

        // then
        assertTrue(response);
    }

    @Test
    public void should_define_human_if_sum_of_sequencs_is_less_then_property_value() {
        // given
        ReflectionTestUtils.setField(target, "requiredDnaSequences", 4);
        when(dnaRowAnalyzer.getSequences()).thenReturn(1);
        when(dnaColumnAnalyzer.getSequences()).thenReturn(1);
        when(dnaDiagonalAnalyzer.getSequences()).thenReturn(1);

        char[][] multi = new char[][]{
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'}
        };

        // when
        boolean response = target.isMutant(multi, multi.length);

        // then
        assertFalse(response);
    }

}