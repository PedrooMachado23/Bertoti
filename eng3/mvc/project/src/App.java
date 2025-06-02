import java.util.*;

public class App {
    public static void main(String[] args) {
        final Model model = new Model();
        Controller controller = new Controller(model);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                model.setValues("10", "5");
                model.valuesChanged();
                System.out.println("Cotação mudou");
            }
        }, 10000);

        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                model.setValues("5", "10");
                model.valuesChanged();
                System.out.println("Cotação mudou");
            }
        }, 15000);
    }
}
