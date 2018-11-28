package ml.exercise.mutants.service.dna.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dna")
public class DnaEntry {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String dna;

    @Column
    private boolean mutant;

    public DnaEntry() {

    }

    public DnaEntry(Long id) {
        this.id = id;
    }

    public DnaEntry(String dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
