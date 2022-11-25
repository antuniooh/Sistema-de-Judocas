package org.fpij.jitakyoei.verifier;

import org.assertj.swing.util.Pair;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;

import java.util.ArrayList;
import java.util.List;

public class EnderecoVerifier implements Verifier<Entidade>{

    @Override
    public Pair<Boolean, String> verify(Entidade obj) {
        Endereco endereco = obj.getEndereco();
        List<String> invalidFields = new ArrayList();

        if (!endereco.getCidade().matches("[a-zA-Z]+")){
            invalidFields.add("cidade");
        }

        if (!endereco.getBairro().matches("[a-zA-Z]+")){
            invalidFields.add("bairro");
        }

        if (!endereco.getRua().matches("[a-zA-Z]+")){
            invalidFields.add("rua");
        }

        if (!endereco.getEstado().matches("[a-zA-Z]+")){
            invalidFields.add("estado");
        }

        if (!endereco.getCep().matches("\\d+")){
            invalidFields.add("cep");
        }

        return Pair.of(invalidFields.isEmpty(), String.join(", ", invalidFields));
    }
}
