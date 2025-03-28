package Vistas;

import Controller.ControllerProfesor;
import modelos.Profesor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CrudProfesor extends JFrame {

    private final ControllerProfesor controllerProfesor;

    // Componentes de la UI
    private JTextField txtIdProfesor, txtIdPersona, txtTipoContrato;
    private JButton btnCrear, btnEliminar, btnEditar;
    private JTable tableProfesor;
    private DefaultTableModel tableModel;

    public CrudProfesor() {
        controllerProfesor = new ControllerProfesor();
        setupUI();
        actualizarTabla();
    }

    private void setupUI() {
        setTitle("Gestión de Profesores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // 🔹 Título
        JLabel lblTitulo = new JLabel("GESTIÓN DE PROFESORES", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // 🔹 Panel de formulario (izquierda)
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Profesor"));

        panelFormulario.add(new JLabel("ID Profesor:"));
        txtIdProfesor = new JTextField();
        panelFormulario.add(txtIdProfesor);

        panelFormulario.add(new JLabel("ID Persona:"));
        txtIdPersona = new JTextField();
        panelFormulario.add(txtIdPersona);

        panelFormulario.add(new JLabel("Tipo de Contrato:"));
        txtTipoContrato = new JTextField();
        panelFormulario.add(txtTipoContrato);

        contentPane.add(panelFormulario, BorderLayout.WEST);

        // 🔹 Panel de botones (abajo)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCrear = new JButton("Crear");
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        panelBotones.add(btnCrear);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // 🔹 Tabla de Profesores (centro)
        tableModel = new DefaultTableModel(new Object[]{"ID Profesor", "ID Persona", "Tipo Contrato"}, 0);
        tableProfesor = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableProfesor);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Profesores"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Acciones de botones
        btnCrear.addActionListener(e -> crearProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());
        btnEditar.addActionListener(e -> editarProfesor());
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Profesor> profesores = controllerProfesor.listarProfesores();
        for (Profesor profesor : profesores) {
            tableModel.addRow(new Object[]{
                    profesor.getId(),
                    profesor.getId(), // ID Persona (heredado de Persona)
                    profesor.getTipoContrato()
            });
        }
    }

    private void crearProfesor() {
        try {
            int id = Integer.parseInt(txtIdProfesor.getText().trim());
            Integer idPersona = Integer.parseInt(txtIdPersona.getText());
            String tipoContrato = txtTipoContrato.getText().trim();

            boolean ok = controllerProfesor.agregarProfesor(id, idPersona, tipoContrato);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Profesor creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear profesor");
            }
            actualizarTabla();
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void eliminarProfesor() {
        try {
            int id = Integer.parseInt(txtIdProfesor.getText().trim());
            boolean ok = controllerProfesor.eliminarProfesor(id);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar profesor");
            }
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido");
        }
    }

    private void editarProfesor() {
        try {
            int id = Integer.parseInt(txtIdProfesor.getText().trim());
            Integer idPersona = Integer.parseInt(txtIdPersona.getText());
            String tipoContrato = txtTipoContrato.getText().trim();

            boolean ok = controllerProfesor.actualizarProfesor(id, idPersona, tipoContrato);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Profesor actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar profesor");
            }
            actualizarTabla();
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void limpiarCampos() {
        txtIdProfesor.setText("");
        txtIdPersona.setText("");
        txtTipoContrato.setText("");
    }

}
