import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class View implements ActionListener, Observer{
    private Subject model;
    private ControllerInterface controller;
    private String cotacaoBatata = "0";
    private String cotacaoCenoura = "0";

    JFrame frame;
	JPanel panelBotoes;
	JPanel panelCotacoes;
	JPanel panelBatata;
	JPanel panelCenoura;
	JLabel labelBatata;
	JLabel labelCenoura;
	JLabel textBatata;
	JLabel textCenoura;
	JButton subButton;
	JButton unsubButton;

    public View(Subject model, ControllerInterface controller){
        this.model = model;
        this.controller = controller;
    }

    public void createView(){
        frame = new JFrame("Cotação de Sacas");
		panelBotoes = new JPanel();
		panelCotacoes = new JPanel();
		panelBatata = new JPanel();
		panelCenoura = new JPanel();
		subButton = new JButton("Assinar cotação das sacas");
		unsubButton = new JButton("Cancelar Assinatura");
		labelBatata = new JLabel();
		labelCenoura = new JLabel();
		textBatata = new JLabel("Saca de Batatas");
		textCenoura = new JLabel("Saca de Cenouras");

        subButton.addActionListener(this);
		unsubButton.addActionListener(this);
		
		panelBotoes.add(subButton);
		panelBotoes.add(unsubButton);
		panelBatata.add(textBatata);
		panelBatata.add(labelBatata);
		panelCenoura.add(textCenoura);
		panelCenoura.add(labelCenoura);
		panelCotacoes.add(panelBatata);
		panelCotacoes.add(panelCenoura);
		frame.getContentPane().add(BorderLayout.SOUTH, panelBotoes);
		frame.getContentPane().add(BorderLayout.CENTER, panelCotacoes);
		
		labelBatata.setText(cotacaoBatata);
		labelCenoura.setText(cotacaoCenoura);

        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void display(){
        labelBatata.setText(cotacaoBatata);
        labelCenoura.setText(cotacaoCenoura);
    }

    public void update(String cotacaoBatata, String cotacaoCenoura){
        this.cotacaoBatata = cotacaoBatata;
        this.cotacaoCenoura = cotacaoCenoura;

        display();
    }

    public void enableSubButton(){
        subButton.setEnabled(true);
    }

    public void disableSubButton(){
        subButton.setEnabled(false);
    }

    public void enableUnsubButton(){
        unsubButton.setEnabled(true);
    }

    public void disableUnsubButton(){
        unsubButton.setEnabled(false);
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == subButton) {
            controller.sub();
        }
        if (event.getSource() == unsubButton) {
            controller.unsub();
        }
    }
}
