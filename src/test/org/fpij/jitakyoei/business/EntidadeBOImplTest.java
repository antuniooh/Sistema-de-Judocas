package org.fpij.jitakyoei.business;

import com.db4o.ObjectSet;
import com.db4o.ext.ExtObjectContainer;
import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.dao.DAO;
import org.fpij.jitakyoei.model.dao.DAOImpl;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.GenerateObjects;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntidadeBOImplTest {

    EntidadeBOImpl entidadeTest = new EntidadeBOImpl(GenerateObjects.generateAppView());

    private static DAO<Entidade> daoE = new DAOImpl<>(Entidade.class);
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
        ObjectSet result = db.get(Entidade.class);

        while(result.hasNext()) {
            db.delete(result.next());
        }
    }

    @Test
    public void checkCreateEntidade() throws Exception {
        Entidade entidade = GenerateObjects.generateEntidade();
        entidadeTest.createEntidade(entidade);

        List<Entidade> retornoLista = entidadeTest.searchEntidade(entidade);
        Entidade entidadeReceived = retornoLista.get(retornoLista.size()-1);

        assertEquals(entidade.getNome(), entidadeReceived.getNome());
    }

    @Test
    public void checkUpdateEntidade() throws Exception {
        // get last entidade
        Entidade entidade = GenerateObjects.generateEntidade();
        entidadeTest.createEntidade(entidade);

        List<Entidade> retornoLista = entidadeTest.listAll();
        Entidade lastEntidadeInserted = retornoLista.get(retornoLista.size()-1);

        lastEntidadeInserted.setTelefone2("1140028922");

        // update last Alunp
        entidadeTest.updateEntidade(lastEntidadeInserted);

        // search for the last entidade
        List<Entidade> retorno = entidadeTest.listAll();
        Entidade entidadeReceivedFinal = retorno.get(retornoLista.size()-1);

        assertEquals("1140028922", entidadeReceivedFinal.getTelefone2());
    }

    @Test
    public void checkUpdateEntidadeIllegalArgumentException() {
        try{
            Entidade entidade = GenerateObjects.generateEntidade();
            entidade.setCnpj("000000");
            entidadeTest.updateEntidade(entidade);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    public void checkUpdateEntidadeException() {
        try{
            entidadeTest.updateEntidade(GenerateObjects.generateEntidade());
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(Exception.class, e.getClass());
        }
    }


    @Test
    public void checkCreateEntidadeException() {
        try {
            entidadeTest.createEntidade(null);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(Exception.class, e.getClass());
        }
    }

    @Test
    public void checkSearchEntidadeException() {

        try {
            entidadeTest.searchEntidade(null);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals(Exception.class, e.getClass());
        }
    }

}
