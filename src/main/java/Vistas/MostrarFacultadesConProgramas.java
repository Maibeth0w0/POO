package Vistas;

import Controller.ControllerFacultad;
import DataAccessObject.FacultadDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarFacultadesConProgramas extends JFrame {

    private JTextArea txtResultado;
    private JButton btnMostrar;

    public MostrarFacultadesConProgramas() {
        setTitle("Facultades y Programas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear componentes
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        btnMostrar = new JButton("Mostrar Facultades y Programas");

        // Agregar acción al botón
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFacultadesConProgramas();
            }
        });

        // Agregar componentes al Frame
        add(scrollPane, BorderLayout.CENTER);
        add(btnMostrar, BorderLayout.SOUTH);
    }

    private void mostrarFacultadesConProgramas() {
        ControllerFacultad controllerFacultad = new ControllerFacultad();
        controllerFacultad.mostrarFacultadConProgramas();
    }

}

