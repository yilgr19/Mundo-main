package gui;

import javax.swing.JOptionPane;
import mundo.OperacionesReservas;
import mundo.Reserva;

public class ConsultarReserva extends javax.swing.JFrame {
    
    private javax.swing.JTable tablaReservas;
    private javax.swing.JScrollPane scrollTablaReservas;
    private javax.swing.table.DefaultTableModel modeloTabla;
    
    public ConsultarReserva() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null);
        inicializarTabla();
        actualizarTablaReservas();
        
        pack();
        setSize(1000, 600);
    }
    
    private void inicializarTabla() {
        modeloTabla = new javax.swing.table.DefaultTableModel(
            new Object[]{"Nombre", "Apellido", "Cédula", "Teléfono", "F. Entrada", "H. Entrada", "F. Salida", "H. Salida", "Tipo Hab.", "Huéspedes", "Método Pago", "Habitación"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false; 
            }
        };
        
        tablaReservas = new javax.swing.JTable(modeloTabla);
        scrollTablaReservas = new javax.swing.JScrollPane(tablaReservas);
        
        getContentPane().setLayout(new java.awt.BorderLayout());
        
        javax.swing.JPanel panelBotones = new javax.swing.JPanel();
        panelBotones.setLayout(new javax.swing.BoxLayout(panelBotones, javax.swing.BoxLayout.Y_AXIS));
        
        javax.swing.JPanel panelTitulo = new javax.swing.JPanel();
        panelTitulo.add(jLabel1);
        
        javax.swing.JPanel panelControles = new javax.swing.JPanel();
        panelControles.add(mostrarTodasBtn);
        panelControles.add(actualizarBtn);
        panelControles.add(regresarBtn);
        
        panelBotones.add(panelTitulo);
        panelBotones.add(panelControles);
        
        getContentPane().add(panelBotones, java.awt.BorderLayout.NORTH);
        getContentPane().add(scrollTablaReservas, java.awt.BorderLayout.CENTER);
    }
    
    private void actualizarTablaReservas() {
        try {
            Reserva.listaReservas.clear();
            Reserva.listaReservas.addAll(OperacionesReservas.listarTodas());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las reservas: " + e.getMessage());
        }
        modeloTabla.setRowCount(0);
        if (Reserva.listaReservas.isEmpty()) {
            modeloTabla.addRow(new Object[]{
                "No hay", "reservas", "registradas", "", "", "", "", "", "", "", "", ""
            });
        } else {
            for (Reserva r : Reserva.listaReservas) {
                modeloTabla.addRow(new Object[]{
                    r.getNombre(), 
                    r.getApellido(), 
                    r.getCedula(), 
                    r.getTelefono(),
                    r.getFechaEntrada(), 
                    r.getHoraEntrada(),
                    r.getFechaSalida(), 
                    r.getHoraSalida(),
                    r.getTipoHabitacion(),
                    r.getNumHuespedes(),
                    r.getMetodoPago(),
                    r.getNumeroHabitacion()
                });
            }
        }
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mostrarTodasBtn = new javax.swing.JButton();
        actualizarBtn = new javax.swing.JButton();
        regresarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultar Reservas");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("CONSULTAR RESERVAS");

        mostrarTodasBtn.setText("Mostrar Todas");
        mostrarTodasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarTodasBtnActionPerformed(evt);
            }
        });

        actualizarBtn.setText("Actualizar");
        actualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarBtnActionPerformed(evt);
            }
        });

        regresarBtn.setText("Regresar");
        regresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mostrarTodasBtn)
                        .addGap(18, 18, 18)
                        .addComponent(actualizarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(regresarBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mostrarTodasBtn)
                    .addComponent(actualizarBtn)
                    .addComponent(regresarBtn))
                .addContainerGap(400, Short.MAX_VALUE))
        );

        pack();
    }

    private void mostrarTodasBtnActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarTablaReservas();
    }

    private void actualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarTablaReservas();
        JOptionPane.showMessageDialog(this, "Tabla actualizada.");
    }

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarReserva().setVisible(true);
            }
        });
    }

    private javax.swing.JButton actualizarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mostrarTodasBtn;
    private javax.swing.JButton regresarBtn;
}
