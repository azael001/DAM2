import java.io.*;

public class ActividadE {
    public static void main(String[] args) {
        File file = new File("./src/d.txt");
        try {
            FileOutputStream fo = new FileOutputStream(file);

            for (int i = 0; i<=50; i++){
                fo.write(i);
                fo.flush();
            }

            FileInputStream fi = new FileInputStream(file);

            int a;
            while((a=fi.read()) != -1){
                System.out.println(a);

            }
            String mas ="+";
            for(int o = 0; o<10; o++){
               fo.write(mas.getBytes());
            }
            while((a=fi.read()) != -1){
                System.out.println((char)a);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
