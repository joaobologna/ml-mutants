package ml.exercise.mutants.service.dna.analyzer;

import ml.exercise.mutants.service.dna.model.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DnaDiagonalAnalyzer extends DnaAnalyzer {

    private List<Element> diagonals = new ArrayList<>();

    @Override
    protected int analyseEntry(int order, int row, int column, char value) {
        int index = column - row;

        if (index < 0) {
            index = order - 1 + Math.abs(index);
        }

        if (diagonals.size() - 1 < index) {
            diagonals.add(new Element(value));
        } else {
            Element diagonalElement = diagonals.get(index);
            return diagonalElement.verify(value);
        }

        return 0;
    }

}
