package org.fpij.jitakyoei.controller;

import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.model.dao.DAOImpl;
import org.fpij.jitakyoei.view.AppView;

public class AlunoBOImpl implements AlunoBO {
	private static DAO<Aluno> dao = new DAOImpl<Aluno>(Aluno.class);
	private AppView view;

	public AlunoBOImpl(AppView view) {
		this.view = view;
	}

	private void fireModelChangeEvent(Aluno aluno) {
		view.handleModelChange(aluno);
	}

	@Override
	public void createAluno(Aluno aluno) throws Exception {
		try {
			dao.save(aluno);
			fireModelChangeEvent(aluno);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException( "Ocorreu um erro ao cadastrar o aluno!"
				+ " Verifique se todos os dados foram preenchidos corretamente.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Desculpe, ocorreu um erro desconhecido ao salvar o aluno.");
		}
	}

	@Override
	public void updateAluno(Aluno aluno) throws Exception{
		try {
			dao.save(aluno);
			fireModelChangeEvent(aluno);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException( "Ocorreu um erro ao salvar os dados do aluno."
				+ " Verifique se todos os dados foram preenchidos corretamente!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Desculpe, ocorreu um erro desconhecido ao salvar o aluno.");
		}
	}
	
	

	@Override
	public List<Aluno> searchAluno(Aluno aluno) throws Exception {
		List<Aluno> result;
		try {
			result = dao.search(aluno);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ocorreu um erro ao salvar os dados do aluno."
				+ " Verifique se todos os dados foram preenchidos corretamente!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Desculpe, ocorreu um erro desconhecido ao salvar o aluno.");
		}
		return result;
	}

	@Override
	public List<Aluno> listAll() throws Exception {
		List<Aluno> result;
		try {
			result = dao.list();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Ocorreu um erro ao obter a lista de alunos."
				+ " Verifique se todos os dados foram preenchidos corretamente!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Desculpe, ocorreu um erro desconhecido ao salvar o aluno.");
		}
		return result;
	}
}