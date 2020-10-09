import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class LoadBalancer {
    private Semaphore webServer = new Semaphore(4);

    public synchronized void processing(String user) throws InterruptedException {
        webServer.acquire();
        System.out.println("Server is processing " + user + " request...");
    }

    public void done(String user) {
        webServer.release();
        System.out.println("Server finished processing " + user + " requests.");
    }

    public static void main(String[] args) {
        LoadBalancer lb = new LoadBalancer();

        ArrayList<User> users = new ArrayList<>();
        for(int i = 0; i < 16; i++) {
            // Instancia 16 usuários
            users.add(new User("User " + (i + 1), lb));
        }

        for (User user : users) {
            user.start();
        }
    }
}