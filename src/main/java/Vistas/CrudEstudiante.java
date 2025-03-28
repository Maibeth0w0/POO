package Vistas;

import Controller.ControllerEstudiante;
import modelos.Estudiante;
import modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CrudEstudiante extends JFrame {

    private final ControllerEstudiante controllerEstudiante;

    // Componentes de la UI
    private JTextField txtCodigo, txtIdPersona, txtIdPrograma, txtPromedio;
    private JCheckBox chkActivo;
    private JButton btnCrear, btnEliminar, btnEditar;
    private JTable tableEstudiante;
    private DefaultTableModel tableModel;

    public CrudEstudiante() {
        controllerEstudiante = new ControllerEstudiante();
        setupUI();
        actualizarTabla();
    }

    private void setupUI() {
        setTitle("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("GESTIÓN DE ESTUDIANTES", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel de formulario (izquierda)
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("ID Persona:"));
        txtIdPersona = new JTextField();
        panelFormulario.add(txtIdPersona);

        panelFormulario.add(new JLabel("ID Programa:"));
        txtIdPrograma = new JTextField();
        panelFormulario.add(txtIdPrograma);

        panelFormulario.add(new JLabel("Promedio:"));
        txtPromedio = new JTextField();
        panelFormulario.add(txtPromedio);

        panelFormulario.add(new JLabel("Activo:"));
        chkActivo = new JCheckBox();
        panelFormulario.add(chkActivo);

        contentPane.add(panelFormulario, BorderLayout.WEST);

        // Panel de botones (abajo)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCrear = new JButton("Crear");
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        panelBotones.add(btnCrear);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // Tabla (centro)
        tableModel = new DefaultTableModel(new Object[]{"Código", "ID Persona", "ID Programa", "Promedio", "Activo"}, 0);
        tableEstudiante = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEstudiante);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Estudiantes"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Acciones de botones
        btnCrear.addActionListener(e -> crearEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnEditar.addActionListener(e -> editarEstudiante());
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Estudiante> estudiantes = controllerEstudiante.listarEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            Object idPrograma = (estudiante.getPrograma() != null) ? estudiante.getPrograma().getId() : "N/A";
            tableModel.addRow(new Object[]{
                    estudiante.getCodigo(),
                    estudiante.getId(),
                    idPrograma,
                    estudiante.getPromedio(),
                    estudiante.getActivo()
            });
        }
    }

    private void crearEstudiante() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            Integer idPersona = Integer.parseInt(txtIdPersona.getText().trim());
            int idPrograma = Integer.parseInt(txtIdPrograma.getText().trim());
            double promedio = Double.parseDouble(txtPromedio.getText().trim());
            boolean activo = chkActivo.isSelected();

            controllerEstudiante.agregarEstudiante(codigo, idPersona, idPrograma, promedio, activo);
            actualizarTabla();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void eliminarEstudiante() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            controllerEstudiante.eliminarEstudiante(codigo);
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido");
        }
    }

    private void editarEstudiante() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            Integer idPersona = Integer.parseInt(txtIdPersona.getText().trim());
            int idPrograma = Integer.parseInt(txtIdPrograma.getText().trim());
            double promedio = Double.parseDouble(txtPromedio.getText().trim());
            boolean activo = chkActivo.isSelected();

            controllerEstudiante.actualizarEstudiante(codigo, idPersona, idPrograma, promedio, activo);

            actualizarTabla();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtIdPersona.setText("");
        txtIdPrograma.setText("");
        txtPromedio.setText("");
        chkActivo.setSelected(false);
    }

}


