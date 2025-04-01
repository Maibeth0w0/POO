package Vistas;

import Controller.ControllerPersona;
import modelos.Persona;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


public class VistaPrincipal extends JFrame {

    private final ControllerPersona controllerPersona;
    private JTabbedPane tabbedPane;
    private JPanel panelPersonas, panelCruds, panelListarFacultad;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtNombre, txtApellido, txtEmail;
    private JButton btnInscribir, btnEliminar, btnActualizar;
    // Los botones para abrir los CRUD de otras entidades
    private JButton btnAbrirCrudFacultad, btnAbrirCrudCurso, btnAbrirCrudPrograma, btnAbrirCrudEstudiante, btnAbrirCrudProfesor, btnAbrirCrudInscripcion , btnListarFacultad;

    public VistaPrincipal() {
        this.controllerPersona = new ControllerPersona();
        initComponents2();
    }

    private void initComponents2() {
        setTitle("Gestión de Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear JTabbedPane
        tabbedPane = new JTabbedPane();
        panelPersonas = new JPanel(new BorderLayout());
        panelCruds = new JPanel(new BorderLayout());
        panelListarFacultad = new JPanel(new BorderLayout());

        // --- Panel de Gestión de Entidades ---
        // Crear un panel para colocar los botones (uno para cada CRUD)
        JPanel panelBotonesCrud = new JPanel(new GridLayout(3, 1, 10, 10));
        btnAbrirCrudFacultad = new JButton("Abrir Crud Facultad");
        btnAbrirCrudCurso = new JButton("Abrir Crud Curso");
        btnAbrirCrudPrograma = new JButton("Abrir Crud Programa");
        btnAbrirCrudEstudiante = new JButton("Abrir Crud Estudiante");
        btnAbrirCrudProfesor = new JButton("Abrir Crud Profesor");
        btnAbrirCrudInscripcion = new JButton("Abrir Crud Inscripcion");
        btnListarFacultad = new JButton("listar facultades y programas");

        // Acciones: al pulsar se abre la ventana correspondiente
        btnAbrirCrudFacultad.addActionListener((ActionEvent e) -> {
            new Vistas.CrudFacultad().setVisible(true);
            // Opcional: this.dispose();
        });
        btnAbrirCrudCurso.addActionListener((ActionEvent e) -> {
            new Vistas.CrudCurso().setVisible(true);
            // Opcional: this.dispose();
        });
        btnAbrirCrudPrograma.addActionListener((ActionEvent e) -> {
            new Vistas.CrudPrograma().setVisible(true);
            // Opcional: this.dispose();
        });
        btnAbrirCrudEstudiante.addActionListener((ActionEvent e) -> {
            new Vistas.CrudEstudiante().setVisible(true);
        });
        btnAbrirCrudProfesor.addActionListener((ActionEvent e) -> {
            new Vistas.CrudProfesor().setVisible(true);
        });
        btnAbrirCrudInscripcion.addActionListener((ActionEvent e) -> {
            new Vistas.CrudInscripcion().setVisible(true);
        });
        btnListarFacultad.addActionListener((ActionEvent e) -> {
            new Vistas.MostrarFacultadesConProgramas().setVisible(true);
        });


        // Agregar botones al panel de botones
        panelBotonesCrud.add(btnAbrirCrudFacultad);
        panelBotonesCrud.add(btnAbrirCrudCurso);
        panelBotonesCrud.add(btnAbrirCrudPrograma);
        panelBotonesCrud.add(btnAbrirCrudEstudiante);
        panelBotonesCrud.add(btnAbrirCrudProfesor);
        panelBotonesCrud.add(btnAbrirCrudInscripcion);
        panelBotonesCrud.add(btnListarFacultad);

        // Agregar el panel de botones al panelCruds
        panelCruds.add(panelBotonesCrud, BorderLayout.CENTER);

        // --- Panel de Inscripciones de Personas ---
        JPanel panelInputs = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInputs.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelInputs.add(txtId);

        panelInputs.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelInputs.add(txtNombre);

        panelInputs.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelInputs.add(txtApellido);

        panelInputs.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelInputs.add(txtEmail);

        // Tabla de Personas
        String[] columnNames = {"ID", "Nombre", "Apellido", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panel de botones de Personas
        JPanel panelButtons = new JPanel();
        btnInscribir = new JButton("Inscribir");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");

        panelButtons.add(btnInscribir);
        panelButtons.add(btnEliminar);
        panelButtons.add(btnActualizar);

        actualizarTabla();

        btnInscribir.addActionListener((ActionEvent e) -> inscribirPersona());
        btnEliminar.addActionListener((ActionEvent e) -> eliminarPersona());
        btnActualizar.addActionListener((ActionEvent e) -> actualizarPersona());

        panelPersonas.add(panelInputs, BorderLayout.NORTH);
        panelPersonas.add(tableScrollPane, BorderLayout.CENTER);
        panelPersonas.add(panelButtons, BorderLayout.SOUTH);

        // --- Agregar pestañas al JTabbedPane ---
        //tabbedPane.addTab("Cursos Inscritos", new JPanel());
        tabbedPane.addTab("Inscripciones Personas", panelPersonas);
        tabbedPane.addTab("Gestión de Entidades", panelCruds);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void inscribirPersona() {
        try {
            Integer id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String email = txtEmail.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
                return;
            }

            controllerPersona.agragarPersonaLista(id, nombre, apellido, email);

            txtId.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtEmail.setText("");

            actualizarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Persona> personas = controllerPersona.listarPersonasBD();
        for (Persona p : personas) {
            tableModel.addRow(new Object[]{p.getId(), p.getNombres(), p.getApellidos(), p.getEmail()});
        }
    }

    private void eliminarPersona() {
        try {
            Integer id = Integer.parseInt(txtId.getText().trim());
            controllerPersona.eliminarPersona(id);
            actualizarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        }
    }

    private void actualizarPersona() {
        try {
            Integer id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String email = txtEmail.getText().trim();
            controllerPersona.actualizarPersona(id, nombre, apellido, email);
            actualizarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new VistaPrincipal().setVisible(true));
    }
}
