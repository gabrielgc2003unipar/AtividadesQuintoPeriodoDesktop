package org.example.dao;

import javax.persistence.EntityTransaction;
import java.util.List;

public class PessoaDAO {
    private com.mycompany.app.pdv.util.EntityManagerUtil entityManager;

    public PessoaDAO(com.mycompany.app.pdv.util.EntityManagerUtil entityManager) {
        this.entityManager = entityManager;
    }
    public void insert(org.example.entities.Pessoa pessoa) {
        javax.persistence.EntityManager em = entityManager.getManager();
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
        em.close();
    }
    public List<Pessoa> findAll() {
        EntityManager em = entityManager.getManager();
        List<Pessoa> pessoas = em.createQuery("SELECT P FROM Pessoa P",
                Pessoa.class).getResultList();
        em.close();
        return pessoas;
    }
    public void deleteAll() {


    }

}