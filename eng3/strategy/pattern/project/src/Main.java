public class Main {
    public static void main(String[] args) throws Exception {
        Context context = new Context();

        context.setStrategy(new ConcreteStrat1());
        context.executeStrategy();

        context.setStrategy(new ConcreteStrat2());
        context.executeStrategy();

        context.setStrategy(new ConcreteStrat3());
        context.executeStrategy();
    }
}
