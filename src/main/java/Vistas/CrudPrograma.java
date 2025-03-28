package Vistas;

import Controller.ControllerPrograma;
import modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * ✅ Ventana para gestionar Programas.
 */
public class CrudPrograma extends JFrame {

    private final ControllerPrograma controllerPrograma;

    // Componentes UI
    private JTextField txtIdPrograma, txtNombrePrograma, txtDuracion, txtRegistro, txtFacultadId;
    private JButton btnGuardar, btnEliminar, btnEditar;
    private JTable tablePrograma;
    private DefaultTableModel tableModel;

    /**
     * ✅ Constructor: Inicializa la interfaz y carga los datos.
     */
    public CrudPrograma() {
        controllerPrograma = new ControllerPrograma();
        setupUI();
        actualizarTabla();
    }

    /**
     * ✅ Configura la interfaz gráfica.
     */
    private void setupUI() {
        setTitle("Gestión de Programas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Panel contenedor con BorderLayout
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // 🔹 Título principal
        JLabel lblTitulo = new JLabel("GESTIÓN DE PROGRAMAS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // 🔹 Panel de formulario (izquierda)
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Programa"));

        panelFormulario.add(new JLabel("ID Programa:"));
        txtIdPrograma = new JTextField();
        panelFormulario.add(txtIdPrograma);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombrePrograma = new JTextField();
        panelFormulario.add(txtNombrePrograma);

        panelFormulario.add(new JLabel("Duración:"));
        txtDuracion = new JTextField();
        panelFormulario.add(txtDuracion);

        panelFormulario.add(new JLabel("Registro:"));
        txtRegistro = new JTextField();
        panelFormulario.add(txtRegistro);

        panelFormulario.add(new JLabel("ID Facultad:"));
        txtFacultadId = new JTextField();
        panelFormulario.add(txtFacultadId);

        contentPane.add(panelFormulario, BorderLayout.WEST);

        // 🔹 Panel de Botones (abajo)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // 🔹 Tabla de Programas (centro)
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Duración", "Registro", "ID Facultad"}, 0);
        tablePrograma = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePrograma);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Programas"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Configurar acciones de botones
        btnGuardar.addActionListener(e -> guardarPrograma());
        btnEliminar.addActionListener(e -> eliminarPrograma());
        btnEditar.addActionListener(e -> editarPrograma());
    }

    /**
     * ✅ Actualiza la tabla con los datos obtenidos.
     */
    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Programa> programas = controllerPrograma.listarProgramasLista();
        System.out.println("programas" + programas);

        for (Programa programa : programas) {
            Object idFacultad = (programa.getFacultad() != null) ? programa.getFacultad().getId() : "N/A";
            tableModel.addRow(new Object[]{programa.getId(), programa.getNombre(), programa.getDuracion(), programa.getRegistro(), idFacultad});
        }
    }

    /**
     * ✅ Guarda un nuevo Programa.
     */
    private void guardarPrograma() {
        try {
            int id = Integer.parseInt(txtIdPrograma.getText().trim());
            String nombre = txtNombrePrograma.getText().trim();
            double duracion = Double.parseDouble(txtDuracion.getText().trim());
            String registro = txtRegistro.getText().trim();
            int idFacultad = Integer.parseInt(txtFacultadId.getText().trim());

            controllerPrograma.agregarPrograma(id, nombre, duracion, registro, idFacultad);
            actualizarTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese valores válidos.");
        }
    }

    /**
     * ✅ Elimina un Programa.
     */
    private void eliminarPrograma() {
        try {
            int id = Integer.parseInt(txtIdPrograma.getText().trim());
            controllerPrograma.eliminarPrograma(id);
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID válido.");
        }
    }

    /**
     * ✅ Edita un Programa.
     */
    private void editarPrograma() {
        try {
            int id = Integer.parseInt(txtIdPrograma.getText().trim());
            String nombre = txtNombrePrograma.getText().trim();
            double duracion = Double.parseDouble(txtDuracion.getText().trim());
            String registro = txtRegistro.getText().trim();
            int idFacultad = Integer.parseInt(txtFacultadId.getText().trim());

            controllerPrograma.actualizarPrograma(id, nombre, duracion, registro, idFacultad);
            actualizarTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese valores válidos.");
        }
    }

    /**
     * ✅ Limpia los campos del formulario.
     */
    private void limpiarCampos() {
        txtIdPrograma.setText("");
        txtNombrePrograma.setText("");
        txtDuracion.setText("");
        txtRegistro.setText("");
        txtFacultadId.setText("");
    }

}
