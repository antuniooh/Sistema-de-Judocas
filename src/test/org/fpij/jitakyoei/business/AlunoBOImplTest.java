package org.fpij.jitakyoei.business;

import com.db4o.ObjectSet;
import com.db4o.ext.ExtObjectContainer;
import org.fpij.jitakyoei.facade.AppFacade;
import org.fpij.jitakyoei.model.beans.*;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.model.dao.DAOImpl;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.fpij.jitakyoei.util.FiliadoID;
import org.fpij.jitakyoei.view.AppView;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import utils.GenerateObjects;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AlunoBOImplTest {

    AlunoBOImpl alunoTest = new AlunoBOImpl(GenerateObjects.generateAppView());
    private static ExtObjectContainer db;

    @BeforeClass
    public static void setUp() {
        DatabaseManager.setEnviroment(DatabaseManager.TEST);
        db = DatabaseManager.getTestDBConnection();
        clearDatabase();
    }

    @BeforeEach
    public void beforeEach(){
    }

    public static void clearDatabase(){
        ObjectSet result = db.get(Aluno.class);

        while(result.hasNext()) {
            db.delete(result.next());
        }
    }

    @Test
    public void checkCreateAluno() throws Exception {
        Aluno aluno = GenerateObjects.generateAluno();
        alunoTest.createAluno(aluno);

        List<Aluno> retornoLista = alunoTest.searchAluno(aluno);
        Aluno alunoReceived = retornoLista.get(0);

        assertEquals(aluno, alunoReceived);
    }

    @Test
    public void checkUpdateAluno() throws Exception {
        // get last aluno
        Aluno aluno = GenerateObjects.generateAluno();
        alunoTest.createAluno(aluno);

        List<Aluno> retornoLista = alunoTest.listAll();
        Aluno lastAlunoInserted = retornoLista.get(0);

        Filiado newFiliado = GenerateObjects.generateFiliado();
        lastAlunoInserted.setFiliado(newFiliado);

        // update last Alunp
        alunoTest.updateAluno(lastAlunoInserted);

        // search for the last aluno
        List<Aluno> retorno = alunoTest.listAll();
        Aluno alunoReceivedFinal = retorno.get(0);

        assertEquals(newFiliado, alunoReceivedFinal.getFiliado());
    }

    @Test
    public void checkUpdateAlunoIllegalArgumentException() throws Exception {
        try{
            alunoTest.updateAluno(new Aluno());
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    public void checkUpdateAlunoException() throws Exception {
        try{
            //DatabaseManager.close();
            alunoTest.updateAluno(new Aluno());
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(IllegalArgumentException.class, e.getClass());
//            DatabaseManager.setEnviroment(DatabaseManager.TEST);
//            DatabaseManager.open();
        }
    }


    @Test
    public void checkCreateAlunoException() throws Exception {
        Aluno aluno = new Aluno();

        try {
            alunoTest.createAluno(aluno);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(Exception.class, e.getClass());
        }
    }

    @Test
    public void checkSearchAlunoException() throws Exception {

        try {
            alunoTest.searchAluno(null);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(Exception.class, e.getClass());
        }
    }

}
