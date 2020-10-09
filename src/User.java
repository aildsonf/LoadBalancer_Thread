import java.util.Random;

public class User extends Thread {
    private String name;
    private LoadBalancer loadBalancer;

    public User(String name, LoadBalancer loadBalancer) {
        this.name = name;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public void run() {
        try {
            Random requests = new Random();
            for(int i = 0; i < requests.nextInt(10); i++) {
                // Gera uma quantidade aleatória de requisições
                loadBalancer.processing(this.name);
                Thread.sleep(1000);
                loadBalancer.done(this.name);
                Thread.sleep(450);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
