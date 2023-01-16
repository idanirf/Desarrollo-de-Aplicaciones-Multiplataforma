package src.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View extends JFrame {

    public View() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bloc de Notas");
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setResizable(false);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/src/View/icono.png"))).getImage());


        JMenuBar menu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemCerrar = new JMenuItem("Cerrar");
        JMenuItem nuevoItemnuevo = new JMenuItem("Nuevo");
        JMenuItem abrirItemAbrir = new JMenuItem("Abrir");
        JMenuItem guardarItemGuardar = new JMenuItem("Guardar");
        JMenuItem guardarComoItemGuardarComo = new JMenuItem("Guardar Como");
        JMenuItem imprimirItemImprimir = new JMenuItem("Imprimir");
        JFileChooser filech = new JFileChooser();



        menu.add(menuArchivo);
        menuArchivo.add(menuItemCerrar);
        menuArchivo.add(nuevoItemnuevo);
        menuArchivo.add(abrirItemAbrir);
        menuArchivo.add(guardarItemGuardar);
        menuArchivo.add(guardarComoItemGuardarComo);
        menuArchivo.add(imprimirItemImprimir);
        this.setJMenuBar(menu);

        JMenu menuEdicion = new JMenu("Edición");
        JMenuItem deshacerItemDeshacer = new JMenuItem("Deshacer");
        JMenuItem cortarItemCortar = new JMenuItem("Cortar");
        JMenuItem copiarItemCopiar = new JMenuItem("Copiar");
        JMenuItem pegarItemPegar = new JMenuItem("Pegar");
        JMenuItem eliminarItemEliminar = new JMenuItem("Eliminar");

        menu.add(menuEdicion);
        menuEdicion.add(deshacerItemDeshacer);
        menuEdicion.add(cortarItemCortar);
        menuEdicion.add(copiarItemCopiar);
        menuEdicion.add(pegarItemPegar);
        menuEdicion.add(eliminarItemEliminar);

        JMenu menuFormato = new JMenu("Formato");
        JMenuItem fuenteItemFuente = new JMenuItem("Fuente");
        JMenuItem colorItemColor = new JMenuItem("Color");

        menu.add(menuFormato);
        menuFormato.add(fuenteItemFuente);
        menuFormato.add(colorItemColor);

        JMenu menuVer = new JMenu("Ver");
        JMenuItem barraDeEstadoItemBarraDeEstado = new JMenuItem("Barra de Estado");

        menu.add(menuVer);
        menuVer.add(barraDeEstadoItemBarraDeEstado);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem acercaDeItemAcercaDe = new JMenuItem("Acerca de");
        JMenuItem verAyudaItemVerAyuda = new JMenuItem("Ver Ayuda");

        menu.add(menuAyuda);
        menuAyuda.add(acercaDeItemAcercaDe);
        menuAyuda.add(verAyudaItemVerAyuda);

        JTextArea textArea = new JTextArea();
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setTabSize(4);
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
        textArea.setFont(new java.awt.Font("Arial", 0, 14));
        textArea.setCaretColor(new java.awt.Color(0, 0, 0));
        textArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textArea.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        textArea.setSelectionColor(new java.awt.Color(0, 0, 0));


        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 10, 500, 500);
        add(scrollPane);

        pack();

        menuItemCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        nuevoItemnuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        abrirItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==abrirItemAbrir){
                    int openFile = filech.showOpenDialog(View.this);
                    if (openFile== JFileChooser.APPROVE_OPTION){
                        File file = filech.getSelectedFile();
                        FileInputStream fileRecent;
                        int  sizeArchivo = Long.valueOf(file.length()).intValue();
                        byte readFile[] = new byte[sizeArchivo];
                        try {
                            fileRecent = new FileInputStream(file.getPath());
                            fileRecent.read(readFile);
                            textArea.append(new String(readFile));
                        } catch (IOException  ex) {
                            System.out.println("Error");
                        }
                        textArea.setCaretPosition(textArea.getDocument().getLength());

                    }
                }
            }
        });

        guardarItemGuardar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showSaveDialog(View.this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            }
        });


        guardarComoItemGuardarComo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == guardarComoItemGuardarComo) {
                            int openFile = filech.showOpenDialog(View.this);
                            if (openFile == JFileChooser.APPROVE_OPTION) {
                                File file = filech.getSelectedFile();
                                FileOutputStream fileRecent;
                                String content = new String();
                                try {
                                    fileRecent = new FileOutputStream(file.getPath());
                                    content = new String(textArea.getText());
                                    fileRecent.write(content.getBytes());
                                    fileRecent.close();

                                } catch (IOException ee) {
                                    throw new RuntimeException(ee);
                                }
                            }
                        }
                    }
                });

        imprimirItemImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    System.out.println("Error al imprimir");
                }
            }
        });

        deshacerItemDeshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
                        @Override
                        public void undoableEditHappened(UndoableEditEvent e) {
                            UndoManager undoManager = new UndoManager();
                            undoManager.addEdit(e.getEdit());
                        }
                    });
                } catch (CannotUndoException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        cortarItemCortar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        copiarItemCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        pegarItemPegar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        eliminarItemEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.replaceSelection("");
            }
        });


        acercaDeItemAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(View.this, "Editor de Texto, Realizado por Daniel Rodríguez Fernández 2DAM", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        verAyudaItemVerAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(View.this, "Ayuda te mandamos a una web de ayuda del bloc de notas idanirf", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                deshacerItemDeshacer.setEnabled(true);
            }
        });

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                deshacerItemDeshacer.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                deshacerItemDeshacer.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                deshacerItemDeshacer.setEnabled(true);
            }
        });

        menuItemCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        pack();
    }
}
