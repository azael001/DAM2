package defaul;

import Util.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        // Abrir una sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Empleadosdefi dl = new Empleadosdefi();
        dl=(Empleadosdefi) session.get(Empleadosdefi.class, 1);
        System.out.println("nombre: " + dl.getNombre());
        session.close();
    }
}
