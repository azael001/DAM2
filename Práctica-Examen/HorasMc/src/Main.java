import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double total;
        int totalAntes = 661;
        /* 0,42 19 5 */
        /* 2,10*/
        Scanner s = new Scanner(System.in);
        System.out.println("Cuantas horas hiciste de 10 a 12");
        int horasC=s.nextInt();
        System.out.println("Cuantas horas hiciste de 12 a 2");
        int horasCE=s.nextInt();;
        System.out.println("Cuantas horas hiciste Extra");
        int horasCom=s.nextInt();
        total = (totalAntes+ (Double)(0.42*horasC)+(Double)(2.10*horasCE) + (Double) (8.88*horasCom)) *0.98;
        System.out.println("Cobrarás " + total + "Euros netos");
    }


}