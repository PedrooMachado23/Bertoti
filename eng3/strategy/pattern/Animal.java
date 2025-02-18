public class Animal {
   private Movimentacao movimentacao;

   public void setMovimentacao(Movimentacao movimentacao){
    this.movimentacao = movimentacao;
   }

   public void movimentar(){
    this.movimentacao.mover();
   }
}