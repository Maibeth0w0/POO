package Vistas;

import Controller.ControllerInscripcion;
import modelos.Inscripcion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CrudInscripcion extends JFrame {

    private final ControllerInscripcion controllerInscripcion;

    // Campos de la interfaz (los 5 parámetros)
    private JTextField txtIdInscripcion, txtIdCurso, txtCodigoEstudiante, txtAnno, txtSemestre;
    private JButton btnCrear, btnEliminar, btnEditar;
    private JTable tableInscripcion;
    private DefaultTableModel tableModel;

    public CrudInscripcion() {
        controllerInscripcion = new ControllerInscripcion();
        setupUI();
        actualizarTabla();
    }

    private void setupUI() {
        setTitle("Gestión de Inscripciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("GESTIÓN DE INSCRIPCIONES", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel de formulario con 5 filas
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de Inscripción"));

        panelFormulario.add(new JLabel("ID Inscripción:"));
        txtIdInscripcion = new JTextField();
        panelFormulario.add(txtIdInscripcion);

        panelFormulario.add(new JLabel("ID Curso:"));
        txtIdCurso = new JTextField();
        panelFormulario.add(txtIdCurso);

        panelFormulario.add(new JLabel("Código Estudiante:"));
        txtCodigoEstudiante = new JTextField();
        panelFormulario.add(txtCodigoEstudiante);

        panelFormulario.add(new JLabel("Año:"));
        txtAnno = new JTextField();
        panelFormulario.add(txtAnno);

        panelFormulario.add(new JLabel("Semestre:"));
        txtSemestre = new JTextField();
        panelFormulario.add(txtSemestre);

        contentPane.add(panelFormulario, BorderLayout.WEST);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCrear = new JButton("Crear");
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        panelBotones.add(btnCrear);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // Tabla para mostrar inscripciones (5 columnas)
        tableModel = new DefaultTableModel(new Object[]{"ID Inscripción", "ID Curso", "Código Estudiante", "Año", "Semestre"}, 0);
        tableInscripcion = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableInscripcion);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Inscripciones"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Configuración de acciones
        btnCrear.addActionListener(e -> crearInscripcion());
        btnEliminar.addActionListener(e -> eliminarInscripcion());
        btnEditar.addActionListener(e -> editarInscripcion());
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Inscripcion> inscripciones = controllerInscripcion.listarInscripciones();
        for (Inscripcion inscripcion : inscripciones) {
            tableModel.addRow(new Object[]{
                    inscripcion.getIdInscripcion(),
                    inscripcion.getCurso().getId(),
                    inscripcion.getEstudiante().getCodigo(),
                    inscripcion.getAnno(),
                    inscripcion.getSemestre()
            });
        }
    }

    private void crearInscripcion() {
        try {
            int idInscripcion = Integer.parseInt(txtIdInscripcion.getText().trim());
            int idCurso = Integer.parseInt(txtIdCurso.getText().trim());
            int codigoEstudiante = Integer.parseInt(txtCodigoEstudiante.getText().trim());
            int anno = Integer.parseInt(txtAnno.getText().trim());
            int semestre = Integer.parseInt(txtSemestre.getText().trim());

            controllerInscripcion.agregarInscripcion(idInscripcion, idCurso, codigoEstudiante, anno, semestre);
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void eliminarInscripcion() {
        try {
            int idInscripcion = Integer.parseInt(txtIdInscripcion.getText().trim());
            controllerInscripcion.eliminarInscripcion(idInscripcion);
            JOptionPane.showMessageDialog(this, "Inscripción eliminada exitosamente");
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void editarInscripcion() {
        try {
            int idInscripcion = Integer.parseInt(txtIdInscripcion.getText().trim());
            int idCurso = Integer.parseInt(txtIdCurso.getText().trim());
            int codigoEstudiante = Integer.parseInt(txtCodigoEstudiante.getText().trim());
            int anno = Integer.parseInt(txtAnno.getText().trim());
            int semestre = Integer.parseInt(txtSemestre.getText().trim());

            controllerInscripcion.actualizarInscripcion(idInscripcion, idCurso, codigoEstudiante, anno, semestre);
            JOptionPane.showMessageDialog(this, "Inscripción actualizada exitosamente");
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void limpiarCampos() {
        txtIdInscripcion.setText("");
        txtIdCurso.setText("");
        txtCodigoEstudiante.setText("");
        txtAnno.setText("");
        txtSemestre.setText("");
    }

}
