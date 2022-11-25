package org.fpij.jitakyoei.verifier;

import org.assertj.swing.util.Pair;
import org.fpij.jitakyoei.model.beans.Entidade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CnpjVerifier implements Verifier<Entidade> {

    public Pair<Boolean, String> verify(Entidade obj) {
        String cnpj = obj.getCnpj();

        Pattern pattern = Pattern.compile("[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}");
        Matcher matcher = pattern.matcher(cnpj);
        return Pair.of(matcher.find(), "cnpj");
    }
}
