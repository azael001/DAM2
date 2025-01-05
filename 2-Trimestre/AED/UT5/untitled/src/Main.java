import com.db4o.*;
import com.db4o.config.*;
import com.db4o.query.*;
public class Main {

    private static final String DB4O_FILE = "database.db4o";

    public static void main(String[] args) {
        // Configurar db4o
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Person.class).cascadeOnUpdate(true);

        // Crear o abrir la base de datos
        ObjectContainer db = Db4oEmbedded.openFile(config, DB4O_FILE);

        try {
            // Insertar datos
            storeData(db);

            // Consultar datos
            queryData(db);
        } finally {
            db.close();
        }
    }

    private static void storeData(ObjectContainer db) {
        Person person = new Person("John Doe", 30);
        db.store(person);
        System.out.println("Dato almacenado: " + person);
    }

    private static void queryData(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Person.class);
        ObjectSet<Person> results = query.execute();

        System.out.println("Resultados de la consulta:");
        for (Person person : results) {
            System.out.println(person);
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
