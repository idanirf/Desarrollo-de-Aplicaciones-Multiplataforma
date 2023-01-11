package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class View extends JFrame {
    private JPanel panel;
    private JTable tablero;
    private JButton btnReset;
    private JButton btnSalir;
    private JLabel lblTurno;
    private JLabel lblJugador1;
    private JLabel lblJugador2;
    private JOptionPane dialogo;

    public View() {
        super("Juego de las tres en raya");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }

    public void initComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        btnReset = new JButton();
        btnReset.setText("Resetear");
        btnReset.setBounds(50, 520, 100, 30);
        panel.add(btnReset);
        btnSalir = new JButton();
        btnSalir.setText("Salir");
        btnSalir.setBounds(450, 520, 100, 30);
        panel.add(btnSalir);
        lblTurno = new JLabel();
        lblTurno.setText("");
        lblJugador1 = new JLabel();
        lblJugador1.setText("Jugador 1: X");
        lblJugador1.setBounds(50, 50, 100, 30);
        panel.add(lblJugador1);
        lblJugador2 = new JLabel();
        lblJugador2.setText("Jugador 2: O");
        lblJugador2.setBounds(500, 50, 100, 30);
        panel.add(lblJugador2);

        tablero = new JTable(3, 3);
        tablero.setBounds(100, 100, 400, 400);
        panel.add(tablero);
        tablero.setRowSelectionAllowed(false);
        tablero.setColumnSelectionAllowed(false);
        tablero.setCellSelectionEnabled(true);
        tablero.setRowHeight(134);
        tablero.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablero.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablero.getColumnModel().getColumn(2).setPreferredWidth(100);

        //Botón Reset
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        //Botón Salir
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });


        //Aviso ganador
        dialogo = new JOptionPane();


        tablero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableroMouseClicked(evt);
            }
        });

    }

    private boolean comprobarGanadorX() {
        boolean ganador = false;
        if (tablero.getValueAt(0, 0) == "X" && tablero.getValueAt(0, 1) == "X" && tablero.getValueAt(0, 2) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");

            ganador = true;
        } else if (tablero.getValueAt(1, 0) == "X" && tablero.getValueAt(1, 1) == "X" && tablero.getValueAt(1, 2) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        } else if (tablero.getValueAt(2, 0) == "X" && tablero.getValueAt(2, 1) == "X" && tablero.getValueAt(2, 2) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        } else if (tablero.getValueAt(0, 0) == "X" && tablero.getValueAt(1, 0) == "X" && tablero.getValueAt(2, 0) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        } else if (tablero.getValueAt(0, 1) == "X" && tablero.getValueAt(1, 1) == "X" && tablero.getValueAt(2, 1) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        } else if (tablero.getValueAt(0, 2) == "X" && tablero.getValueAt(1, 2) == "X" && tablero.getValueAt(2, 2) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        } else if (tablero.getValueAt(0, 0) == "X" && tablero.getValueAt(1, 1) == "X" && tablero.getValueAt(2, 2) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        } else if (tablero.getValueAt(0, 2) == "X" && tablero.getValueAt(1, 1) == "X" && tablero.getValueAt(2, 0) == "X") {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            ganador = true;
        }
        return ganador;
    }

    private boolean comprobarGanadorO() {
        boolean ganador = false;
        if (tablero.getValueAt(0, 0) == "O" && tablero.getValueAt(0, 1) == "O" && tablero.getValueAt(0, 2) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(1, 0) == "O" && tablero.getValueAt(1, 1) == "O" && tablero.getValueAt(1, 2) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(2, 0) == "O" && tablero.getValueAt(2, 1) == "O" && tablero.getValueAt(2, 2) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(0, 0) == "O" && tablero.getValueAt(1, 0) == "O" && tablero.getValueAt(2, 0) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(0, 1) == "O" && tablero.getValueAt(1, 1) == "O" && tablero.getValueAt(2, 1) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(0, 2) == "O" && tablero.getValueAt(1, 2) == "O" && tablero.getValueAt(2, 2) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(0, 0) == "O" && tablero.getValueAt(1, 1) == "O" && tablero.getValueAt(2, 2) == "O") {
            ganador = true;
        } else if (tablero.getValueAt(0, 2) == "O" && tablero.getValueAt(1, 1) == "O" && tablero.getValueAt(2, 0) == "O") {
            ganador = true;
        }
        return ganador;
    }


    private void tableroMouseClicked(MouseEvent evt) {

        int fila = tablero.rowAtPoint(evt.getPoint());
        int columna = tablero.columnAtPoint(evt.getPoint());
        if (tablero.getValueAt(fila, columna) == null) {
            if (lblTurno.getText().equals("Turno de: X")) {
                tablero.setValueAt("X", fila, columna);
                lblTurno.setText("Turno de: O");
            } else {
                tablero.setValueAt("O", fila, columna);
                lblTurno.setText("Turno de: X");
            }
        }
        if (comprobarGanadorX()) {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 1!");
            btnResetActionPerformed(null);
        } else if (comprobarGanadorO()) {
            JOptionPane.showMessageDialog(null, "¡Ha ganado el jugador 2!");
            btnResetActionPerformed(null);
        }
    }

    private void btnSalirActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void btnResetActionPerformed(ActionEvent evt) {
        tablero.setValueAt(null, 0, 0);
        tablero.setValueAt(null, 0, 1);
        tablero.setValueAt(null, 0, 2);
        tablero.setValueAt(null, 1, 0);
        tablero.setValueAt(null, 1, 1);
        tablero.setValueAt(null, 1, 2);
        tablero.setValueAt(null, 2, 0);
        tablero.setValueAt(null, 2, 1);
        tablero.setValueAt(null, 2, 2);


    }
}
