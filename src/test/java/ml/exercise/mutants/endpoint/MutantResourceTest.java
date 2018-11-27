package ml.exercise.mutants.endpoint;

import ml.exercise.mutants.service.mutant.MutantService;
import ml.exercise.mutants.service.mutant.model.MutantStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantResourceTest {

    @InjectMocks
    private MutantResource target;

    @Mock
    private MutantService mutantService;

    @Mock
    private MutantStats stats;

    @Test
    public void should_return_status_200_for_mutant() {
        // given
        when(mutantService.isMutant(any(), anyInt())).thenReturn(true);

        // when
        ResponseEntity response = target.isMutant(Collections.emptyList());

        // then
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void should_return_status_403_for_human() {
        // given
        when(mutantService.isMutant(any(), anyInt())).thenReturn(false);

        // when
        ResponseEntity response = target.isMutant(Collections.emptyList());

        // then
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void should_return_stats_provided_by_service_class() {
        // given
        when(mutantService.stats()).thenReturn(stats);

        // when
        ResponseEntity<MutantStats> response = target.stats();

        // then
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response).extracting(ResponseEntity::getBody).isEqualTo(stats);
    }
}