package org.fpij.jitakyoei.model.validator;

import org.assertj.swing.util.Pair;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.verifier.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EntidadeValidator implements Validator<Entidade> {

    List<Verifier<Entidade>> verifiers = new LinkedList<>();

    public EntidadeValidator() {
        verifiers.add(new CnpjVerifier());
        verifiers.add(new Telefone1Verifier());
        verifiers.add(new EnderecoVerifier());
        verifiers.add(new NameVerifier());
    }

    @Override
    public boolean validate(Entidade obj) throws Exception {
        List<String> invalidFields = new ArrayList();

        verifiers.forEach(verifier -> {
            Pair<Boolean, String> outcome = verifier.verify(obj);
            if (!outcome.first){
                invalidFields.add(outcome.second);
            }
        });

        if (invalidFields.isEmpty()) {
            return true;
        }

        throw new IllegalArgumentException("Os seguintes dados da entidade estão inválidos: " + String.join(", ", invalidFields));
    }
}
