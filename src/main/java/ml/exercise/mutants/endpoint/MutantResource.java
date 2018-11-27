package ml.exercise.mutants.endpoint;

import ml.exercise.mutants.service.mutant.MutantService;
import ml.exercise.mutants.service.mutant.model.MutantStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MutantResource {

    @Autowired
    private MutantService mutantService;

    @PostMapping(path = "/mutant")
    public ResponseEntity isMutant(@RequestBody List<String> dna) {
        char[][] array = new char[dna.size()][];
        array = dna.stream().map(String::toCharArray).collect(Collectors.toList()).toArray(array);

        if (mutantService.isMutant(array, dna.size())) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<MutantStats> stats() {
        return ResponseEntity.ok(mutantService.stats());
    }
}
