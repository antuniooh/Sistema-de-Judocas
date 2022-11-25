package org.fpij.jitakyoei.verifier;

import org.assertj.swing.util.Pair;
import org.fpij.jitakyoei.model.beans.Entidade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefone1Verifier implements Verifier<Entidade> {
    @Override
    public Pair<Boolean, String> verify(Entidade obj) {
        String telefone1 = obj.getTelefone1();

        Pattern pattern = Pattern.compile("[1-9]{2}(?:[2-8]|9[1-9])[0-9]{3}[0-9]{4}");
        Matcher matcher = pattern.matcher(telefone1);
        return Pair.of(matcher.find(), "telefone1");
    }
}
