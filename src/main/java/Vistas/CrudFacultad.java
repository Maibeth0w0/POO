package Vistas;

import Controller.ControllerFacultad;
import modelos.Facultad;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CrudFacultad extends JFrame {

    private final ControllerFacultad controllerFacultad;

    // Componentes de la UI
    private JTextField txtIdFacultad, txtNombreFacultad, txtIdDecano;
    private JButton btnGuardar, btnEliminar, btnEditar;
    private JTable tableFacultad;
    private DefaultTableModel tableModel;

    /**
     * Constructor: Inicializa la interfaz y carga los datos.
     */
    public CrudFacultad() {
        controllerFacultad = new ControllerFacultad();
        setupUI();
        actualizarTabla();
    }

    /**
     * Configura la interfaz gráfica de la ventana.
     */
    private void setupUI() {
        setTitle("Gestión de Facultades");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Panel contenedor con BorderLayout
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Título principal
        JLabel lblTitulo = new JLabel("GESTIÓN DE FACULTADES", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel del formulario (lado izquierdo)
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de Facultad"));

        panelFormulario.add(new JLabel("ID Facultad:"));
        txtIdFacultad = new JTextField();
        panelFormulario.add(txtIdFacultad);

        panelFormulario.add(new JLabel("Nombre Facultad:"));
        txtNombreFacultad = new JTextField();
        panelFormulario.add(txtNombreFacultad);

        panelFormulario.add(new JLabel("ID Decano:"));
        txtIdDecano = new JTextField();
        panelFormulario.add(txtIdDecano);

        contentPane.add(panelFormulario, BorderLayout.WEST);

        // Panel de botones (abajo)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // Tabla de Facultades (centro)
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "ID Decano"}, 0);
        tableFacultad = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableFacultad);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Facultades"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Configurar acciones de botones
        btnGuardar.addActionListener(e -> guardarFacultad());
        btnEliminar.addActionListener(e -> eliminarFacultad());
        btnEditar.addActionListener(e -> editarFacultad());
    }

    /**
     * Actualiza la tabla con los datos obtenidos.
     */
    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Facultad> facultades = controllerFacultad.listarFacultadBD();
        for (Facultad facultad : facultades) {
            Object idDecano = (facultad.getDecano() != null) ? facultad.getDecano().getId() : "N/A";
            tableModel.addRow(new Object[]{facultad.getId(), facultad.getNombre(), idDecano});
        }
    }

    /**
     * Guarda una nueva Facultad.
     */
    private void guardarFacultad() {
        try {
            int id = Integer.parseInt(txtIdFacultad.getText().trim());
            String nombre = txtNombreFacultad.getText().trim();
            int idDecano = Integer.parseInt(txtIdDecano.getText().trim());
            controllerFacultad.agregarFacultad(id, nombre, idDecano);
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese valores válidos.");
        }
    }

    /**
     * Elimina la Facultad con el ID indicado.
     */
    private void eliminarFacultad() {
        try {
            int id = Integer.parseInt(txtIdFacultad.getText().trim());
            controllerFacultad.eliminarFacultad(id);
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese un ID válido.");
        }
    }

    /**
     * Edita la Facultad con los nuevos datos.
     */
    private void editarFacultad() {
        try {
            int id = Integer.parseInt(txtIdFacultad.getText().trim());
            String nombre = txtNombreFacultad.getText().trim();
            int idDecano = Integer.parseInt(txtIdDecano.getText().trim());
            controllerFacultad.actualizarFacultad(id, nombre, idDecano);
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Ingrese valores válidos.");
        }
    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiarCampos() {
        txtIdFacultad.setText("");
        txtNombreFacultad.setText("");
        txtIdDecano.setText("");
    }

    private void mostrarListaFacultad(){
        controllerFacultad.mostrarFacultadConProgramas();
    }

}
