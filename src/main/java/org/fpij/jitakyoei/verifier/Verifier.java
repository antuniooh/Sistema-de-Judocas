package org.fpij.jitakyoei.verifier;

import org.assertj.swing.util.Pair;

public interface Verifier<E> {
    public Pair<Boolean, String> verify(E obj);
}
