package ml.exercise.mutants.service.analiser;

import ml.exercise.mutants.service.analiser.util.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MutantRowAnaliser extends MutantAnaliser {

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
