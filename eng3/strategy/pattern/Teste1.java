public class Teste1 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.setMovimentacao(new Corrida());
        animal.movimentar();

        animal.setMovimentacao(new Nado());
        animal.movimentar();

        animal.setMovimentacao(new Voo());
        animal.movimentar();
    }
}