package ml.exercise.mutants.service.mutant.interceptor;

import ml.exercise.mutants.service.dna.repository.DnaEntry;
import ml.exercise.mutants.service.dna.repository.DnaRepository;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantInterceptorTest {

    @InjectMocks
    private MutantInterceptor target;

    @Mock
    private DnaRepository dnaRepository;

    @Mock
    private DnaEntry response;

    @Mock
    private MethodInvocation invocation;

    private Object[] arguments = new Object[2];

    @Before
    public void setup() {
        arguments[1] = null;
        arguments[0] = new char[][]{
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'}
        };
    }

    @Test
    public void should_return_value_from_repository_if_present() throws Throwable {
        // given
        when(response.isMutant()).thenReturn(false);
        when(dnaRepository.findByDna(anyString())).thenReturn(singletonList(response));
        when(invocation.getArguments()).thenReturn(arguments);

        // when
        Boolean output = target.invoke(invocation);

        // then
        assertFalse(output);
    }

    @Test
    public void should_compute_value_if_not_present_in_repository() throws Throwable {
        // given
        when(invocation.proceed()).thenReturn(true);
        when(dnaRepository.findByDna(anyString())).thenReturn(Collections.emptyList());
        when(invocation.getArguments()).thenReturn(arguments);

        // when
        Boolean output = target.invoke(invocation);

        // then
        verify(invocation, times(1)).proceed();
        assertTrue(output);
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_exception_if_arguments_are_null() throws Throwable {
        // given
        when(invocation.getArguments()).thenReturn(null);

        // when
        target.invoke(invocation);
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_exception_if_arguments_are_bellow_two() throws Throwable {
        // given
        when(invocation.getArguments()).thenReturn(new Object[1]);

        // when
        target.invoke(invocation);
    }

}