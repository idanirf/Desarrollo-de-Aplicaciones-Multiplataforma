package src.vista;

import src.Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class VistaCajaFuerte extends JFrame {
    //Controller creado
    private final Controller controlador;
    private JLabel numeroIntroducido;
    private JButton jLabel2;
    private JButton jLabel3;
    private JButton jLabel1;
    private JButton jLabel4;
    private JButton jLabel5;
    private JButton jLabel6;
    private JButton jLabel7;
    private JButton jLabel8;
    private JButton jLabel9;
    private JButton jLabel0;
    private JButton jLabelConfirmar;
    private JButton jLabelBorrar;
    private JLabel numeroResultado;
    private JTextArea resultadosAnteriores;

    public VistaCajaFuerte() {
        initComponents();
        //Inicializamos el controlador
        controlador = new Controller(numeroIntroducido, numeroResultado, resultadosAnteriores);
        setActions();
        this.setSize(500, 700);
        this.pack();
    }

    private void initComponents() {
        numeroResultado = new JLabel();
        resultadosAnteriores = new JTextArea();
        numeroIntroducido = new JLabel();
        jLabel1 = new JButton();
        jLabel2 = new JButton();
        jLabel3 = new JButton();
        jLabel4 = new JButton();
        jLabel5 = new JButton();
        jLabel6 = new JButton();
        jLabel7 = new JButton();
        jLabel8 = new JButton();
        jLabel9 = new JButton();
        jLabel0 = new JButton();
        jLabelConfirmar = new JButton();
        jLabelBorrar = new JButton();

        //Asignamos valor a los botones y Labels
        jLabel1.setText("1");
        jLabel2.setText("2");
        jLabel3.setText("3");
        jLabel4.setText("4");
        jLabel5.setText("5");
        jLabel6.setText("6");
        jLabel7.setText("7");
        jLabel8.setText("8");
        jLabel9.setText("9");
        jLabelConfirmar.setText("Confirmar");
        jLabel0.setText("0");
        jLabelBorrar.setText("Borrar");
        numeroResultado.setText("Numero Resultado");
        resultadosAnteriores.setText("Resultados Anteriores: " + "\n");

        JPanel grid2 = new JPanel(new GridLayout(4, 4));
        grid2.add(jLabel1);
        grid2.add(jLabel2);
        grid2.add(jLabel3);
        grid2.add(jLabel4);
        grid2.add(jLabel5);
        grid2.add(jLabel6);
        grid2.add(jLabel7);
        grid2.add(jLabel8);
        grid2.add(jLabel9);
        grid2.add(jLabelConfirmar);
        grid2.add(jLabel0);
        grid2.add(jLabelBorrar);

        JPanel grid3 = new JPanel(new GridLayout(3, 1));
        grid3.add(numeroIntroducido);
        grid3.add(grid2);
        grid3.add(numeroResultado);

        JPanel grid1 = new JPanel(new GridLayout(1, 2));
        grid1.add(grid3);
        grid1.add(resultadosAnteriores);
        pack();
        this.add(grid1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caja Fuerte");
    }

    private void setActions() {
        jLabel1.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel2.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel3.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel4.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel5.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel6.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel7.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel8.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel9.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabel0.addActionListener(
                e -> controlador.menejaBoton(e));
        jLabelConfirmar.addActionListener(
                e -> controlador.numeroIntentos(e));
        jLabelBorrar.addActionListener(
                e -> controlador.borrar(e));
    }
}
