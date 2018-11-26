package ml.exercise.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntry, Long> {

     List<DnaEntry> findByDna(@Param("dna") String dna);

}
