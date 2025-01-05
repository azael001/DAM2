public class Main {
    public static void main(String args[]) {
        ClienteDatagram c = new ClienteDatagram("Pedro");
        c.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ClienteDatagramD d = new ClienteDatagramD("Azael");
        d.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ClienteDatagramF f = new ClienteDatagramF("Rita");
        f.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ClienteDatagramN n = new ClienteDatagramN("Alberto");
        n.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }
}
