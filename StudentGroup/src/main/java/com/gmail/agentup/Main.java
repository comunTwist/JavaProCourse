//Вывести на экран список групп с
//указанием количества студентов
//в каждой группе.

package com.gmail.agentup;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DrWhite");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            try {
                Group group1 = new Group("Sportsman");
                Group group2 = new Group("Wunderkind");
                Group group3 = new Group("Developer");

                Student vasa = new Student("Vasa", 21);
                Student peta = new Student("Peta", 23);
                Student toma = new Student("Toma", 19);

                group1.addStudent(vasa);
                group2.addStudent(vasa);
                group1.addStudent(peta);
                group3.addStudent(peta);
                group1.addStudent(toma);

                em.persist(group1);
                em.persist(group2);
                em.persist(group3);

                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                return;
            }

            //Query query = em.createQuery("SELECT c FROM Group c", Group.class);
            Query query = em.createNamedQuery("Group.findAll", Group.class);
            List<Group> grouptList = query.getResultList();

            for (Group group : grouptList) {
                String title = group.getTitle();
                List<Student> students = group.getStudents();

                int count = students.size();
                System.out.println(title + ": " + count + " students");
//                for (Student student : students) {
//                    System.out.print(student);
//                }
//                System.out.println();

            }

        } finally {
            em.close();
            emf.close();
        }
    }
}
