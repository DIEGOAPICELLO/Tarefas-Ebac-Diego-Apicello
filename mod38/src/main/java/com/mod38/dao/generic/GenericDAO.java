package com.mod38.dao.generic;

import com.mod38.domain.Persistente;
import com.mod38.exceptions.DAOException;
import com.mod38.exceptions.MaisDeUmRegistroException;
import com.mod38.exceptions.TableException;
import com.mod38.exceptions.TipoChaveNaoEncontradaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericDAO <T extends Persistente, E extends Serializable> implements IGenericDAO <T,E> {

    protected Class<T> persistenteClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDAO(Class<T> persistenteClass) {
        this.persistenteClass = persistenteClass;
    }

    @Override
    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
        entityManager.persist(entity);
//		entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void excluir(T entity) throws DAOException {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            T managedCustomer = entityManager.find(this.persistenteClass, entity.getId());
            if (managedCustomer != null) {
                entityManager.remove(managedCustomer);
            }
        }

    }

    @Override
    public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
        entity = entityManager.merge(entity);
//		entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
        T entity = entityManager.find(this.persistenteClass, valor);
//		entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Collection<T> buscarTodos() throws DAOException {
        List<T> list =
                entityManager.createQuery(getSelectSql(), this.persistenteClass).getResultList();
        return list;
    }

    private String getSelectSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistenteClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    }


}