package c;

import org.hibernate.Session;
import org.hibernate.query.Query;
import Util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Abrir una sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Departamento dl = new Departamento();;
        dl=(Departamento) session.get(Departamento.class, 10);
        System.out.println("nombre" + dl.getNombredeptno());
        session.close();
    }
}
