package org.fpij.jitakyoei.verifier;

import org.assertj.swing.util.Pair;
import org.fpij.jitakyoei.model.beans.Entidade;

public class NameVerifier implements Verifier<Entidade>{
    @Override
    public Pair<Boolean, String> verify(Entidade obj) {
        String name = obj.getNome();

        return Pair.of(!name.isEmpty(), "entidade");
    }
}
