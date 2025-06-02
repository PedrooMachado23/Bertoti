public interface Subject {
    public void registerObserver(Observer observer);
    public void deleteObserver(Observer observer);
    public void notifyObserver();
    public void setValues(String cotacaoBata, String cotacaoCenoura);
}
