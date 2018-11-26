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
public class DnaRowAnalyzer extends DnaAnalyzer {

    private List<Element> rows = new ArrayList<>();

    @Override
    protected int analyseEntry(int order, int row, int column, char value) {

        if (rows.size() - 1 < row) {
            rows.add(new Element(value));
        } else {
            Element lineElement = rows.get(row);
            return lineElement.verify(value);
        }

        return 0;
    }
}
