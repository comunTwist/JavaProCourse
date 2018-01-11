package com.gmail.agentup;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Console {
    static EntityManagerFactory emf;
    static EntityManager em;

    static final String[] TITLES = {"soup", "meat", "potatoes", "juice", "beer"};
    static final boolean[] DISCOUNTS = {true, false};
    static final Random RND = new Random();

    static String randomTitle() {
        return TITLES[RND.nextInt(TITLES.length)];
    }

    static boolean randomSale() {
        return DISCOUNTS[RND.nextInt(2)];
    }

    public static void startMenu() {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("CrazyTest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add dish");
                    System.out.println("2: add random dishes");
                    System.out.println("3: delete dish");
                    System.out.println("4: change dish");
                    System.out.println("5: view dishes");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addDish(sc);
                            break;
                        case "2":
                            insertRandomDishes(sc);
                            break;
                        case "3":
                            deleteDish(sc);
                            break;
                        case "4":
                            changeDish(sc);
                            break;
                        case "5":
                            viewDishes(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addDish(Scanner sc) {
        System.out.print("Enter dish title: ");
        String title = sc.nextLine();
        System.out.print("Enter dish price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);
        System.out.print("Enter dish weight: ");
        String sWeight = sc.nextLine();
        int weight = Integer.parseInt(sWeight);
        System.out.print("Enter dish sale (y/n): ");
        String sSale = sc.nextLine();
        boolean sale = false;
        if (sSale.equals("y")) {
            sale = true;
        }

        em.getTransaction().begin();
        try {
            Menu c = new Menu(title, price, weight, sale);
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteDish(Scanner sc) {
        System.out.print("Enter dish id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        Menu c = em.find(Menu.class, id);
        if (c == null) {
            System.out.println("Dish not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeDish(Scanner sc) {
        System.out.print("Enter dish title: ");
        String title = sc.nextLine();

        System.out.print("Enter new price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);

        System.out.print("Enter new weight: ");
        String sWeight = sc.nextLine();
        int weight = Integer.parseInt(sWeight);

        System.out.print("Enter dish sale (y/n): ");
        String sSale = sc.nextLine();
        boolean sale = false;
        if (sSale.equals("y")) {
            sale = true;
        }

        Menu c = null;
        try {
            Query query = em.createQuery("SELECT c FROM Menu c WHERE c.title = :title", Menu.class);
            query.setParameter("title", title);
            c = (Menu) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Dish not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            c.setPrice(price);
            c.setWeight(weight);
            c.setSale(sale);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void insertRandomDishes(Scanner sc) {
        System.out.print("Enter dishes count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);

        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                Menu c = new Menu(randomTitle(), RND.nextInt(100), RND.nextInt(200), randomSale());
                em.persist(c);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewDishes(Scanner sc) {

        Query query;
        int overweight = 0;
        int maxweight = 1000;

        System.out.print("Select select by parameters (y/n): ");
        String param = sc.nextLine();
        if (param.equals("y")) {
            System.out.print("Enter min price: ");
            String sMin = sc.nextLine();
            double minPrice = Double.parseDouble(sMin);
            System.out.print("Enter max price: ");
            String sMax = sc.nextLine();
            double maxPrice = Double.parseDouble(sMax);
            System.out.print("Enter sale (y/n): ");
            String sSale = sc.nextLine();
            boolean sale = false;
            if (sSale.equals("y")) {
                sale = true;
            }
            System.out.print("Enter max weight: ");
            String sMaxweight = sc.nextLine();
            maxweight = Integer.parseInt(sMaxweight);

            query = em.createQuery("SELECT c FROM Menu c WHERE price >= :min AND price <= :max AND sale = :sale", Menu.class);
            query.setParameter("min", minPrice);
            query.setParameter("max", maxPrice);
            query.setParameter("sale", sale);
        } else {
            query = em.createQuery("SELECT c FROM Menu c", Menu.class);
        }
        List<Menu> list = (List<Menu>) query.getResultList();

        for (Menu c : list) {
            overweight += c.getWeight();
            if (overweight > maxweight)
                break;
            System.out.println(c);
        }
    }
}
