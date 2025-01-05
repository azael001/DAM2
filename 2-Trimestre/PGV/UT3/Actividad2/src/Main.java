import java.util.Scanner;
import java.util.concurrent.Semaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String nombreUser;
        Scanner sc = new Scanner(System.in);
        Semaphore s = new Semaphore(1);
        for(int i = 0; i<=3; i++){

            try {
                s.acquire();
                System.out.println("Dime un nombre para continuar");
                nombreUser = sc.nextLine();
                Chat chat = new Chat(nombreUser,s);
                chat.start();
                s.release();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}