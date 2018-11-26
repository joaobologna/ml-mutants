package ml.exercise.mutants.service.mutant.model;

public class MutantStats {

    private Long humans;
    private Long mutants;
    private Float ratio;

    public MutantStats(Long humans, Long mutants, Float ratio) {
        this.humans = humans;
        this.mutants = mutants;
        this.ratio = ratio;
    }

    public Long getHumans() {
        return humans;
    }

    public void setHumans(Long humans) {
        this.humans = humans;
    }

    public Long getMutants() {
        return mutants;
    }

    public void setMutants(Long mutants) {
        this.mutants = mutants;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }
}
