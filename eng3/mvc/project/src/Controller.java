public class Controller implements ControllerInterface{
    View view;
    Subject model;

    public Controller(Subject model) {
        this.model = model;
        view = new View(model, this);
        view.createView();
        view.disableUnsubButton();
    }

    public void sub(){
        model.registerObserver(view);
        System.out.println("Assinatura realizada");
        view.disableSubButton();
        view.enableUnsubButton();
    }

    public void unsub(){
        model.deleteObserver(view);
        System.out.println("Assinatura cancelada");
        view.enableSubButton();
        view.disableUnsubButton();
    }
}
