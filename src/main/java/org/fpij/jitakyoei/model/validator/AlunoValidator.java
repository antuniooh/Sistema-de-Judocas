package org.fpij.jitakyoei.model.validator;

import org.fpij.jitakyoei.model.beans.Aluno;

public class AlunoValidator implements Validator<Aluno>{
	@Override
	public boolean validate(Aluno obj) {
		return true;
	}
}