package hamburgueria.cozinha.preparo.templatemethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PreparoTemplate {
    public final List<String> executar(String item) {
        if (item == null || item.isBlank()) {
            throw new IllegalArgumentException("Item obrigatorio");
        }
        List<String> etapas = new ArrayList<>();
        etapas.add(separar(item));
        etapas.add(cozinhar(item));
        etapas.add(montar(item));
        etapas.add(embalar(item));
        return Collections.unmodifiableList(etapas);
    }

    protected String separar(String item) {
        return "separar " + item;
    }

    protected abstract String cozinhar(String item);

    protected String montar(String item) {
        return "montar " + item;
    }

    protected String embalar(String item) {
        return "embalar " + item;
    }
}
