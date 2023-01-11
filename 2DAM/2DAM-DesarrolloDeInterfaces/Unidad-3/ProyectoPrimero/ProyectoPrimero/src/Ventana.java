package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JMenuBar menu;
    private JMenu menuArchivo;
    private JMenuItem menuItemCerrar;
    private JLabel pantalla;
    private JButton bontonMenos;
    private JButton botonMas;
    private JPanel panelPrincipal;

    public Ventana() throws HeadlessException {
        initComponents();
    }

    private void initComponents() {

        menu = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        menuItemCerrar = new JMenuItem("Cerrar");
        menu.add(menuArchivo);
        menuArchivo.add(menuItemCerrar);
        this.setJMenuBar(menu);

        pantalla = new JLabel("0");
        botonMas = new JButton("Incrementar");
        bontonMenos = new JButton("Decrementar");
        panelPrincipal = new JPanel();

        panelPrincipal.add(pantalla);
        panelPrincipal.add(botonMas);
        panelPrincipal.add(bontonMenos);
        this.add(panelPrincipal);

        botonMas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(pantalla.getText());
                valor++;
                pantalla.setText(String.valueOf(valor));

            }
        });

        bontonMenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = Integer.parseInt(pantalla.getText());
                valor--;
                pantalla.setText(String.valueOf(valor));
                }
        });

        menuItemCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(new Dimension(300, 300));

    }
}
