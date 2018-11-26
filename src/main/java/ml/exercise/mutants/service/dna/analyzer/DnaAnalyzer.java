package ml.exercise.mutants.service.dna.analyzer;

import org.springframework.beans.factory.annotation.Value;

public abstract class DnaAnalyzer {

    @Value("${dna.required.sequence}")
    private int requiredDnaSequence;

    private int sequences = 0;

    public void addEntry(int order, int row, int column, char value) {
        int dnaSequence = analyseEntry(order, row, column, value);

        if (dnaSequence == requiredDnaSequence) {
            sequences++;
        }
    }

    protected abstract int analyseEntry(int order, int row, int column, char value);

    public int getSequences() {
        return sequences;
    }
}
