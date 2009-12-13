package org.fpij.jitakyoei.view.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.java.dev.genesis.annotation.Action;
import net.java.dev.genesis.annotation.DataProvider;
import net.java.dev.genesis.annotation.Form;
import net.java.dev.genesis.ui.swing.SwingBinder;

import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.model.dao.DAOImpl;
import org.fpij.jitakyoei.view.ViewComponent;
import org.fpij.jitakyoei.view.gui.AlunoPanel;

@Form
public class AlunoForm implements ViewComponent{
	private FiliadoForm filiadoForm;
	private Professor professor;
	private JComboBox professorCombo;
	private Entidade entidade;
	private JComboBox entidadeCombo;
	private AlunoPanel gui;
	private AppFacade facade;
	private List<Professor> resultProfessores;
	private List<Entidade> resultEntidades;
	
	public AlunoForm(AlunoPanel alunoPanel) {
		this.gui = alunoPanel;
		SwingBinder binder = new SwingBinder(gui, this);
		binder.bind();
		filiadoForm = new FiliadoForm(gui.getFiliadoPanel());
		professorCombo = gui.getProfessor();
		entidadeCombo = gui.getEntidade();
//		populaProfessorCombo();
	}
	
	public void populaProfessorCombo(){
		try{
			resultProfessores = facade.listProfessores();
		}catch (Exception e) {
			System.out.println("eeeerrrrrrrrrrrrrooooooooooooooooo");
			e.printStackTrace();
			System.out.println("eeeerrrrrrrrrrrrrooooooooooooooooo");
		}
		for (Professor p : resultProfessores) {
			professorCombo.addItem(p);
		}
	}
	
//	@DataProvider(objectField = "professor")
//	public List<Professor> populaProfessor() {
//		System.out.println("AlunoForm.populaProfessor()");
//		return resultProfessores;
//	}
//	
//	@DataProvider(objectField = "entidade")
//	public List<Entidade> populaEntidade() {
//		System.out.println("AlunoForm.populaEntidade()");
//		return resultEntidades;
//	}
	
	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Aluno pegarBean() {
		System.out.println("AlunoForm.getBean()");		

		Aluno a = new Aluno();
		a.setFiliado(filiadoForm.pegarBean());
		System.out.println("AlunoForm.getBean() depois do getBean filiadoForm");
		a.setProfessor((Professor) professorCombo.getSelectedItem());
		a.setEntidade((Entidade) entidadeCombo.getSelectedItem());
		System.out.println(a.getProfessor() +" - "+a.getEntidade());
		return a;
	}

	@Override
	public JPanel getGui() {
		return gui;
	}

	@Override
	public void registerFacade(AppFacade fac) {
		this.facade = fac;		
	}
	
}