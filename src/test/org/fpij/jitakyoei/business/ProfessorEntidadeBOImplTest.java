package org.fpij.jitakyoei.business;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import utils.GenerateObjects;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfessorEntidadeBOImplTest {

    ProfessorEntidadeBOImpl professorEntidadeBOTest = new ProfessorEntidadeBOImpl(GenerateObjects.generateAppView());

    @AfterEach
    public static void afterEach(){
        DatabaseManager.setEnviroment(DatabaseManager.TEST);
        try {
            DatabaseManager.getConnection().rollback();
        }catch (Exception ignore){}
    }

    @Test
    public void checkCreateProfessorEntidade() throws Exception {
        ProfessorEntidade professorEntidade = GenerateObjects.generateProfessorEntidade();
        List<ProfessorEntidade> list = new ArrayList<>();
        list.add(professorEntidade);


        professorEntidadeBOTest.createProfessorEntidade(list);

    }

    @Test
    public void checkCreateProfessorEntidadeException() throws Exception {

        try {
            professorEntidadeBOTest.createProfessorEntidade(null);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(Exception.class, e.getClass());
        }
    }


}
