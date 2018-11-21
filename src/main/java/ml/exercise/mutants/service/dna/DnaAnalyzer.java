package ml.exercise.mutants.service.dna;

import org.springframework.beans.factory.annotation.Value;

public abstract class DnaAnalyzer {

    @Value("${valid.dna.sequence}")
    private int validDnaSequence;

    private int sequences = 0;

    public void addEntry(int order, int row, int column, char value) {
        int dnaSequence = analyseEntry(order, row, column, value);

        if (dnaSequence == validDnaSequence) {
            sequences++;
        }
    }

    protected abstract int analyseEntry(int order, int row, int column, char value);

    public int getSequences() {
        return sequences;
    }
}
