package Vistas;

import Controller.ControllerCurso;
import modelos.Curso;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CrudCurso extends JFrame {

    private final ControllerCurso controllerCurso;

    // Componentes de la UI
    private JTextField txtIdCurso, txtNombreCurso, txtIdPrograma;
    private JCheckBox chkActivo;
    private JButton btnCrear, btnEliminar, btnEditar;
    private JTable tableCurso;
    private DefaultTableModel tableModel;

    public CrudCurso() {
        controllerCurso = new ControllerCurso();
        setupUI();
        actualizarTabla();
    }

    private void setupUI() {
        setTitle("Gestión de Cursos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("GESTIÓN DE CURSOS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel de formulario (izquierda)
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Curso"));

        panelFormulario.add(new JLabel("ID Curso:"));
        txtIdCurso = new JTextField();
        panelFormulario.add(txtIdCurso);

        panelFormulario.add(new JLabel("Nombre Curso:"));
        txtNombreCurso = new JTextField();
        panelFormulario.add(txtNombreCurso);

        panelFormulario.add(new JLabel("ID Programa:"));
        txtIdPrograma = new JTextField();
        panelFormulario.add(txtIdPrograma);

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
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "ID Programa", "Activo"}, 0);
        tableCurso = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCurso);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Cursos"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Acciones de botones
        btnCrear.addActionListener(e -> crearCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());
        btnEditar.addActionListener(e -> editarCurso());
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Curso> cursos = controllerCurso.listarCursos();
        for (Curso curso : cursos) {
            Object idProg = (curso.getPrograma() != null && curso.getPrograma().getId() != null) ? curso.getPrograma().getId() : "N/A";
            tableModel.addRow(new Object[]{
                curso.getId(),
                curso.getNombre(),
                idProg,
                curso.getActivo()
            });
        }
    }

    private void crearCurso() {
        try {
            int id = Integer.parseInt(txtIdCurso.getText().trim());
            String nombre = txtNombreCurso.getText().trim();
            int idProg = Integer.parseInt(txtIdPrograma.getText().trim());
            boolean activo = chkActivo.isSelected();

            boolean ok = controllerCurso.crearCurso(id, nombre, idProg, activo);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Curso creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear curso");
            }
            actualizarTabla();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void eliminarCurso() {
        try {
            int id = Integer.parseInt(txtIdCurso.getText().trim());
            boolean ok = controllerCurso.eliminarCurso(id);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar curso");
            }
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido");
        }
    }

    private void editarCurso() {
        try {
            int id = Integer.parseInt(txtIdCurso.getText().trim());
            String nombre = txtNombreCurso.getText().trim();
            int idProg = Integer.parseInt(txtIdPrograma.getText().trim());
            boolean activo = chkActivo.isSelected();

            boolean ok = controllerCurso.actualizarCurso(id, nombre, idProg, activo);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar curso");
            }
            actualizarTabla();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos");
        }
    }

    private void limpiarCampos() {
        txtIdCurso.setText("");
        txtNombreCurso.setText("");
        txtIdPrograma.setText("");
        chkActivo.setSelected(false);
    }

}
