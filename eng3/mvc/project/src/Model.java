import java.util.ArrayList;

public class Model implements Subject{
    private ArrayList<Observer> observers;
    private String cotacaoBatata;
    private String cotacaoCenoura;

    public Model() {
        this.observers = new ArrayList<>();
    }

    public String getCotacaoBatata(){
        return this.cotacaoBatata;
    }

    public String getCotacaoCenoura(){
        return this.cotacaoCenoura;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        int obsIndex = observers.indexOf(observer);
        if (obsIndex >= 0) {
            observers.remove(obsIndex);
        }
    }

    public void notifyObserver() {
        for (int index = 0; index < observers.size(); index++) {
            Observer observer = (Observer)observers.get(index);
            observer.update(cotacaoBatata, cotacaoCenoura);
        }
    }

    public void valuesChanged() {
        notifyObserver();
    }

    public void setValues(String cotacaoBatata, String cotacaoCenoura){
        this.cotacaoBatata = cotacaoBatata;
        this.cotacaoCenoura = cotacaoCenoura;
    }
}
