package ml.exercise.mutants.service;

import ml.exercise.mutants.service.analiser.MutantAnaliser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantService {

    @Value("${max.sequence.required}")
    private int maxSequenceRequired;

    @Autowired
    private List<MutantAnaliser> validators;

    public boolean isMutant(char[][] array, int order) {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                final int row = i;
                final int column = j;
                final char value = array[i][j];

                validators.forEach(impl -> impl.addEntry(row, column, value));
            }
        }

        return validators.stream().mapToInt(MutantAnaliser::getSequences).sum() >= maxSequenceRequired;
    }
}
