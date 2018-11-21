package ml.exercise.mutants.service.dna;

import ml.exercise.mutants.service.dna.util.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DnaColumnAnalyzer extends DnaAnalyzer {

    private List<Element> columns = new ArrayList<>();

    @Override
    protected int analyseEntry(int order, int row, int column, char value) {

        if (columns.size() - 1 < column) {
            columns.add(new Element(value));
        } else {
            Element rowElement = columns.get(column);
            return rowElement.verify(value);
        }

        return 0;

    }
}
