package com.mycompany.manipulacaoarquivo.dao;

import com.mycompany.manipulacaoarquivo.entities.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PessoaDAO {
    private EntityManager entityManager;

    public PessoaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Pessoa insert(Pessoa pessoa) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(pessoa);
            transaction.commit();
            return pessoa;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Re-lança a exceção para ser tratada em outro lugar
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close(); // Fecha o EntityManager após a transação
            }
        }
    }
    
    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                            .getResultList();
    }
    
    public void deleteAll() {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.createQuery("DELETE FROM Pessoa").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
