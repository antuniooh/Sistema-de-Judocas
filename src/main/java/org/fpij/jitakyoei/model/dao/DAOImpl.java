package org.fpij.jitakyoei.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.fpij.jitakyoei.model.validator.Validator;
import org.fpij.jitakyoei.util.DatabaseManager;

import com.db4o.ObjectSet;
import com.db4o.ext.ExtObjectContainer;

public class DAOImpl<E> implements DAO<E> {

	private static ExtObjectContainer db = DatabaseManager.getConnection();
	private Class<E> clazz;
	private Validator<E> validator;
	private boolean useEquals;
	
	public DAOImpl(Class<E> clazz, Validator<E> val, boolean comp){
		this.validator = val;
		this.useEquals = comp;
		this.clazz = clazz;
	}
	
	public DAOImpl(Class<E> clazz, boolean useEquals){
		this.validator = new DefaultValidator<E>();
		this.useEquals = useEquals;
		this.clazz = clazz;
	}
	
	public DAOImpl(Class<E> clazz){
		this.validator = new DefaultValidator<E>();
		this.clazz = clazz;
	}

	public DAOImpl(Class<E> clazz, Validator<E> val){
		this.validator = val;
		this.clazz = clazz;
	}

	private void openDBIfClosed() {
		if (db.isClosed()) {
			db = DatabaseManager.getConnection();
			System.out.println("DB is closed?: " + db.isClosed());
		}
	}

	@Override
	public synchronized boolean save(E object) throws Exception {
		System.out.println("DAOImpl.save() ->" + db.isClosed());
		System.out.println(object);

		openDBIfClosed();

		if(object != null && validator.validate(object)){
			db.store(object);
			db.commit();
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public synchronized void delete(E object) {
		openDBIfClosed();
		db.delete(object);
		db.commit();
	}
	
	@Override
	public List<E> list() {
		List<E> objects = new ArrayList<E>();
		openDBIfClosed();
		ObjectSet<E> result = db.queryByExample(clazz);
		while(result.hasNext()){
			objects.add((E)result.next());
		}
		return objects;
	}
	
	@Override
	public E get(E object) throws IllegalArgumentException{
		openDBIfClosed();
		List<E> objectList = db.queryByExample(clazz);
		if(useEquals){
			for(E each: objectList){
				if(each.equals(object)){
					return each;
				}
			}
		}
		else{
			int index = objectList.indexOf(object);
			if(index >= 0){
				return objectList.get(index);
			}
		}
		throw new IllegalArgumentException("Nenhum objeto encontrado!");
	}

	@Override
	public List<E> search(E object) {
		List<E> objects = new ArrayList<E>();
		openDBIfClosed();
		ObjectSet<E> result = db.queryByExample(object);
		while(result.hasNext()){
			objects.add((E)result.next());
		}
		return objects;		
	}

	/**
	 * Classe utilizada caso o nenhuma classe Validador seja fornecida na instanciação.
	 */
	public class DefaultValidator<T> implements Validator<T> {
		@Override
		public boolean validate(T obj) {
			return true;
		}
	}
}