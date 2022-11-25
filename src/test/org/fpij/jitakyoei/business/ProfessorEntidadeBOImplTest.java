package org.fpij.jitakyoei.business;

import com.db4o.ObjectSet;
import com.db4o.ext.ExtObjectContainer;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.ProfessorEntidade;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.model.dao.DAOImpl;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.GenerateObjects;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfessorEntidadeBOImplTest {

    ProfessorEntidadeBOImpl professorEntidadeBOTest = new ProfessorEntidadeBOImpl(GenerateObjects.generateAppView());

    private static DAO<ProfessorEntidade> dao = new DAOImpl<>(ProfessorEntidade.class);
    private static ExtObjectContainer db;

    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);
        db = DatabaseManager.getTestDBConnection();
        clearDatabase();
    }

    @BeforeEach
    public static void clearDatabase(){
        if (db.isClosed()) {
            db = DatabaseManager.getConnection();
            System.out.println("DB is closed?: " + db.isClosed());
        }

        ObjectSet result = db.get(ProfessorEntidade.class);

        while(result.hasNext()) {
            db.delete(result.next());
        }
    }

    @Test
    public void checkCreateProfessorEntidade() throws Exception {
        clearDatabase();
        ProfessorEntidade professorEntidade = GenerateObjects.generateProfessorEntidade();
        List<ProfessorEntidade> list = new ArrayList<>();
        list.add(professorEntidade);

        professorEntidadeBOTest.createProfessorEntidade(list);

        if (db.isClosed()) {
            db = DatabaseManager.getConnection();
            System.out.println("DB is closed?: " + db.isClosed());
        }

        assertEquals(professorEntidade.hashCode(), db.get(ProfessorEntidade.class).get(0).hashCode());
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
