package ml.exercise.mutants.service.mutant;

import ml.exercise.mutants.service.dna.repository.DnaRepository;
import ml.exercise.mutants.service.dna.analyzer.DnaAnalyzer;
import ml.exercise.mutants.service.mutant.model.MutantStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantService {

    @Value("${dna.max.required.sequences}")
    private int requiredDnaSequences;

    @Autowired
    private List<DnaAnalyzer> analyzers;

    @Autowired
    private DnaRepository dnaRepository;

    public boolean isMutant(char[][] array, int order) {
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                final int row = i;
                final int column = j;
                final char value = array[i][j];

                analyzers.forEach(impl -> impl.addEntry(order, row, column, value));
            }
        }

        return analyzers.stream().mapToInt(DnaAnalyzer::getSequences).sum() >= requiredDnaSequences;
    }

    public MutantStats stats() {
        Long humans = dnaRepository.countByMutant(false);
        Long mutants = dnaRepository.countByMutant(true);

        return new MutantStats(humans, mutants, 1f * mutants / humans);
    }
}
