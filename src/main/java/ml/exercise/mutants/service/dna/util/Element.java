package ml.exercise.mutants.service.dna.util;

public class Element {
    private char value;
    private int count;

    public Element(char value) {
        this.value = value;
        reset();
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int verify(char value) {
        if (this.value == value) {
            count++;
        } else {
            reset();
            this.value = value;
        }

        return count;
    }

    private void reset() {
        count = 1;
    }
}
