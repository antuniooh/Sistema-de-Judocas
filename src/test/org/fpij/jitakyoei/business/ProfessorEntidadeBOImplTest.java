package org.fpij.jitakyoei.business;

import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.model.dao.DAOImpl;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import utils.GenerateObjects;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfessorEntidadeBOImplTest {

    ProfessorEntidadeBOImpl professorEntidadeBOTest = new ProfessorEntidadeBOImpl(GenerateObjects.generateAppView());

    private static DAO<ProfessorEntidade> dao = new DAOImpl<>(ProfessorEntidade.class);

    @BeforeClass
    public static void set(){
        DatabaseManager.setEnviroment(DatabaseManager.TEST);
    }

    @AfterEach
    public static void afterEach(){
        clearDatabase();
    }

    public static void clearDatabase(){
        List<ProfessorEntidade> all = dao.list();
        for (ProfessorEntidade each : all) {
            dao.delete(each);
        }
        assertEquals(0, dao.list().size());
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
