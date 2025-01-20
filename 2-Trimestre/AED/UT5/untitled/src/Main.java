import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {

	public static void main(String[] args) {
		
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bdpersonas.yap");
//		Personas p = new Personas(1,"1","2","3");
//		db.store(p);
//
		
		ObjectSet<Personas> pe=db.queryByExample(new Personas());
		System.out.println("n de personas: " + pe.size());
		while(pe.hasNext()) {

			Personas per = pe.next();
			System.out.println("Nombre : "+per.getNombre()+" "+"Dirección :"
					+per.getDomicilio());
		}
	
		db.close();
		
		
	}

}
/*
ObjectSet<Personas> pe=db.queryByExample(new Personas());
System.out.println("n de personas: " + pe.size());
while(pe.hasNext()) {
	
	Personas per = pe.next();
	System.out.println("Nombre : "+per.getNombre()+" "+"Dirección :"
			+per.getDomicilio());
}
p=db.queryByExample(new Personas(0,null,null,"Telde"));*/