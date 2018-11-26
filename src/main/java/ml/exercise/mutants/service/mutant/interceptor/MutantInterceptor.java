package ml.exercise.mutants.service.mutant.interceptor;

import ml.exercise.mutants.repository.DnaEntry;
import ml.exercise.mutants.repository.DnaRepository;
import ml.exercise.mutants.service.mutant.MutantService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Interceptor for {@link MutantService#isMutant(char[][], int)} with
 * the intent to persist the given computed value into a database.
 */
@Component
public class MutantInterceptor implements MethodInterceptor {

    @Autowired
    private DnaRepository dnaRepository;

    @Override
    public Boolean invoke(MethodInvocation invocation) throws Throwable {
        String dna = extractDna(invocation.getArguments());
        List<DnaEntry> dnaList = dnaRepository.findByDna(dna);

        if (dnaList.isEmpty()) {
            return persist(dna, (Boolean) invocation.proceed());
        }

        return dnaList.stream().findFirst().map(DnaEntry::isMutant).orElseThrow(IllegalArgumentException::new);
    }

    private Boolean persist(String dna, Boolean result) {
        dnaRepository.save(new DnaEntry(dna, result));
        return result;
    }

    private String extractDna(Object[] arguments) {
        if (Objects.isNull(arguments) || arguments.length < 2) {
            throw new IllegalStateException();
        }

        StringBuilder builder = new StringBuilder();
        Stream.of((char[][]) arguments[0]).forEachOrdered(builder::append);

        return builder.toString();
    }
}
