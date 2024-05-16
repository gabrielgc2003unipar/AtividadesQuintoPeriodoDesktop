package com.mycompany.manipulacaoarquivo.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Importações específicas do PostgreSQL JDBC
import org.postgresql.Driver;

public class EntityManagerUtil {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManagerFactory getEntityManagerFactory(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("DesktopPU");      
            System.out.println("Conexão aberta");
        }
        return emf;
    }
    
    public static EntityManager getManager(){
        if(em == null || !em.isOpen()){
            em = getEntityManagerFactory().createEntityManager();
            System.out.println("Conexão aberta");
        }
        return em;
    }

    public static void closeEntityManager(){
        if(em != null && em.isOpen()){
            em.close();
            System.out.println("EntityManager fechado!");
        }
    }

    public static void closeEntityManagerFactory(){
        if(emf != null && emf.isOpen()){
            emf.close();
            System.out.println("Conexão Fechada!");
        }
    }
}
