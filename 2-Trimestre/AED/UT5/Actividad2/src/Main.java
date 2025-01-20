import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bdequipos.yap");
        Equipos e = new Equipos("Playas C", "alin",3,"Avenida Playa del Hombre","Boss",20);
//        db.store(e);

//obtener nombre y puntos de los equipos de categoría alevín
        Equipos categoria = new Equipos(null, "alevin", 0, null, null, 0);
        ObjectSet<Equipos> r = db.queryByExample(categoria);
        while (r.hasNext()) {
            Equipos per=r.next();
            System.out.println("Nombre : "+per.getNombreEquipo()+" "+"Puntos :"
                    +per.getPuntos());
        }
        System.out.println("Dime de que equipo quieres hacer la búsqueda");
        String nombreEq = input.nextLine();
//Obtener sede y presidente de un equipo solicitado
        Equipos equipo = new Equipos(nombreEq, null, 0, null, null, 0);
        ObjectSet<Equipos> re = db.queryByExample(equipo);
        while (re.hasNext()) {
            Equipos per=re.next();
            System.out.println("sede : "+per.getSede()+"Presidente :"
                    +per.getPresidente());
        }

//Visualizar todos los datos.
        Equipos equipos = new Equipos(null, null, 0, null, null, 0);
        ObjectSet<Equipos> res = db.queryByExample(equipos);
        while (res.hasNext()) {
            Equipos per = res.next();
            System.out.println("Nombre : " + per.getNombreEquipo() + " " + "Puntos :"
                    + per.getPuntos() + " sede : " + per.getSede() + " Presidente :"
                    + per.getPresidente() + " grupo " + per.getGrupo() + " categoría " + per.getCategoria() );
        }
        db.close();


    }
}
