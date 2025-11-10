package gui;

import javax.swing.JOptionPane;
import mundo.Checkin;
import mundo.Habitaciones;
import mundo.ConsumoMinibar;

public class ConsultarCheckins extends javax.swing.JFrame {
    
    private javax.swing.JTable tablaCheckins;
    private javax.swing.JScrollPane scrollTablaCheckins;
    private javax.swing.table.DefaultTableModel modeloTabla;
    
    public ConsultarCheckins() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null);
        inicializarTabla();
        actualizarTablaCheckins();
        
        pack();
        setSize(1000, 600);
    }
    
    private void inicializarTabla() {
        modeloTabla = new javax.swing.table.DefaultTableModel(
            new Object[]{"Nombre", "Apellido", "Cédula", "Teléfono", "F. Ingreso", "F. Salida", "Días", "Habitación", "Alergias", "Estado", "Observaciones"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false; 
            }
        };
        
        tablaCheckins = new javax.swing.JTable(modeloTabla);
        scrollTablaCheckins = new javax.swing.JScrollPane(tablaCheckins);
        
        getContentPane().setLayout(new java.awt.BorderLayout());
        
        javax.swing.JPanel panelBotones = new javax.swing.JPanel();
        panelBotones.setLayout(new javax.swing.BoxLayout(panelBotones, javax.swing.BoxLayout.Y_AXIS));
        
        javax.swing.JPanel panelTitulo = new javax.swing.JPanel();
        panelTitulo.add(jLabel1);
        
        javax.swing.JPanel panelControles = new javax.swing.JPanel();
        panelControles.add(mostrarTodosBtn);
        panelControles.add(mostrarActivosBtn);
        panelControles.add(mostrarFinalizadosBtn);
        panelControles.add(actualizarBtn);
        panelControles.add(checkoutBtn);
        panelControles.add(regresarBtn);
        
        panelBotones.add(panelTitulo);
        panelBotones.add(panelControles);
        
        getContentPane().add(panelBotones, java.awt.BorderLayout.NORTH);
        getContentPane().add(scrollTablaCheckins, java.awt.BorderLayout.CENTER);
    }
    
    private void actualizarTablaCheckins() {
        modeloTabla.setRowCount(0);
        if (Checkin.listaCheckins.isEmpty()) {
            modeloTabla.addRow(new Object[]{
                "No hay", "check-ins", "registrados", "", "", "", "", "", "", "", ""
            });
        } else {
            for (Checkin c : Checkin.listaCheckins) {
                modeloTabla.addRow(new Object[]{
                    c.getNombre(), 
                    c.getApellido(), 
                    c.getCedula(), 
                    c.getTelefono(),
                    c.getFechaIngreso(), 
                    c.getFechaSalida(), 
                    c.getDiasHospedaje(),
                    c.getNumeroHabitacion(),
                    c.getAlergias(),
                    c.getEstado(),
                    c.getObservaciones()
                });
            }
        }
    }
    
    private void filtrarCheckinsActivos() {
        modeloTabla.setRowCount(0);
        boolean encontrados = false;
        for (Checkin c : Checkin.listaCheckins) {
            if (c.getEstado().equals("Activo")) {
                modeloTabla.addRow(new Object[]{
                    c.getNombre(), 
                    c.getApellido(), 
                    c.getCedula(), 
                    c.getTelefono(),
                    c.getFechaIngreso(), 
                    c.getFechaSalida(), 
                    c.getDiasHospedaje(),
                    c.getNumeroHabitacion(),
                    c.getAlergias(),
                    c.getEstado(),
                    c.getObservaciones()
                });
                encontrados = true;
            }
        }
        if (!encontrados) {
            modeloTabla.addRow(new Object[]{
                "No hay", "check-ins", "activos", "", "", "", "", "", "", "", ""
            });
        }
    }
    
    private void filtrarCheckinsFinalizados() {
        modeloTabla.setRowCount(0);
        boolean encontrados = false;
        for (Checkin c : Checkin.listaCheckins) {
            if (c.getEstado().equals("Finalizado")) {
                modeloTabla.addRow(new Object[]{
                    c.getNombre(), 
                    c.getApellido(), 
                    c.getCedula(), 
                    c.getTelefono(),
                    c.getFechaIngreso(), 
                    c.getFechaSalida(), 
                    c.getDiasHospedaje(),
                    c.getNumeroHabitacion(),
                    c.getAlergias(),
                    c.getEstado(),
                    c.getObservaciones()
                });
                encontrados = true;
            }
        }
        if (!encontrados) {
            modeloTabla.addRow(new Object[]{
                "No hay", "check-ins", "finalizados", "", "", "", "", "", "", "", ""
            });
        }
    }
    
    private void procesarCheckout() {
        int filaSeleccionada = tablaCheckins.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un check-in activo de la tabla.");
            return;
        }
        
        String cedulaStr = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
        long cedula = Long.parseLong(cedulaStr);
        
        Checkin checkinSeleccionado = null;
        for (Checkin c : Checkin.listaCheckins) {
            if (c.getCedula() == cedula && c.getEstado().equals("Activo")) {
                checkinSeleccionado = c;
                break;
            }
        }
        
        if (checkinSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "El check-in seleccionado no está activo.");
            return;
        }
        
        double consumoMinibar = calcularConsumoMinibar(checkinSeleccionado.getCedula());
        
        String mensajeConsumo = "CONSUMO DEL MINIBAR\n\n" +
            "Cliente: " + checkinSeleccionado.getNombre() + " " + checkinSeleccionado.getApellido() + "\n" +
            "Habitación: " + checkinSeleccionado.getNumeroHabitacion() + "\n\n";
        
        if (consumoMinibar > 0) {
            mensajeConsumo += "Productos consumidos:\n";
            for (ConsumoMinibar consumo : ConsumoMinibar.listaConsumos) {
                if (consumo.getCedulaCliente() == checkinSeleccionado.getCedula() && !consumo.isFacturado()) {
                    mensajeConsumo += "• " + consumo.getNombreProducto() + " x" + consumo.getCantidad() + 
                                    " = $" + consumo.getSubtotal() + "\n";
                }
            }
            mensajeConsumo += "\nTotal consumo minibar: $" + consumoMinibar + "\n\n";
        } else {
            mensajeConsumo += "No se registraron consumos del minibar.\n\n";
        }
        
        mensajeConsumo += "¿Proceder con el checkout?";
        
        int opcionConsumo = JOptionPane.showConfirmDialog(this, mensajeConsumo, 
            "Consumo Minibar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcionConsumo != JOptionPane.YES_OPTION) {
            return;
        }
        
        try {
            
            double precioHabitacion = 0;
            for (Habitaciones hab : Habitaciones.listaHabitaciones) {
                if (hab.getNumero() == checkinSeleccionado.getNumeroHabitacion()) {
                    precioHabitacion = hab.getPrecio();
                    break;
                }
            }
            
            double costoHospedaje = checkinSeleccionado.getDiasHospedaje() * precioHabitacion;
            double totalAPagar = costoHospedaje + consumoMinibar;
            
            String mensaje = "RESUMEN DE CHECK-OUT\n\n" +
                "Cliente: " + checkinSeleccionado.getNombre() + " " + checkinSeleccionado.getApellido() + "\n" +
                "Cédula: " + checkinSeleccionado.getCedula() + "\n" +
                "Habitación: " + checkinSeleccionado.getNumeroHabitacion() + "\n" +
                "Días hospedados: " + checkinSeleccionado.getDiasHospedaje() + "\n" +
                "Precio habitación/día: $" + precioHabitacion + "\n" +
                "Costo hospedaje: $" + costoHospedaje + "\n" +
                "Consumo minibar: $" + consumoMinibar + "\n" +
                "----------------------------------------\n" +
                "TOTAL A PAGAR: $" + totalAPagar + "\n\n" +
                "¿Confirmar el checkout?";
            
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar Check-out", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (opcion == JOptionPane.YES_OPTION) {
                ConsumoMinibar consumo = new ConsumoMinibar(0, 0, "", 0, 0, "");
                consumo.marcarConsumosComoFacturados(checkinSeleccionado.getCedula());
                
                liberarHabitacion(checkinSeleccionado.getNumeroHabitacion());
                
                checkinSeleccionado.finalizarCheckin();
                
                JOptionPane.showMessageDialog(this, "✅ CHECK-OUT COMPLETADO\n\n" +
                    "Cliente: " + checkinSeleccionado.getNombre() + " " + checkinSeleccionado.getApellido() + "\n" +
                    "Habitación: " + checkinSeleccionado.getNumeroHabitacion() + " liberada\n" +
                    "Días hospedados: " + checkinSeleccionado.getDiasHospedaje() + "\n" +
                    "Costo hospedaje: $" + costoHospedaje + "\n" +
                    "Consumo minibar: $" + consumoMinibar + "\n" +
                    "═══════════════════════════════════════\n" +
                    "TOTAL PAGADO: $" + totalAPagar);
                
                actualizarTablaCheckins();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El consumo del minibar debe ser un número válido.");
        }
    }
    
    private void liberarHabitacion(int numeroHabitacion) {
        for (Habitaciones hab : Habitaciones.listaHabitaciones) {
            if (hab.getNumero() == numeroHabitacion) {
                hab.setEstado(true);
                break;
            }
        }
    }
    
    private double calcularConsumoMinibar(long cedulaCliente) {
        double total = 0;
        for (ConsumoMinibar consumo : ConsumoMinibar.listaConsumos) {
            if (consumo.getCedulaCliente() == cedulaCliente && !consumo.isFacturado()) {
                total += consumo.getSubtotal();
            }
        }
        return total;
    }

    //GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mostrarTodosBtn = new javax.swing.JButton();
        mostrarActivosBtn = new javax.swing.JButton();
        mostrarFinalizadosBtn = new javax.swing.JButton();
        actualizarBtn = new javax.swing.JButton();
        checkoutBtn = new javax.swing.JButton();
        regresarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultar Check-ins");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("CONSULTAR CHECK-INS");

        mostrarTodosBtn.setText("Mostrar Todos");
        mostrarTodosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarTodosBtnActionPerformed(evt);
            }
        });

        mostrarActivosBtn.setText("Solo Activos");
        mostrarActivosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarActivosBtnActionPerformed(evt);
            }
        });

        mostrarFinalizadosBtn.setText("Solo Finalizados");
        mostrarFinalizadosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarFinalizadosBtnActionPerformed(evt);
            }
        });

        actualizarBtn.setText("Actualizar");
        actualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarBtnActionPerformed(evt);
            }
        });

        checkoutBtn.setText("Check-out");
        checkoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutBtnActionPerformed(evt);
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
                        .addComponent(mostrarTodosBtn)
                        .addGap(18, 18, 18)
                        .addComponent(mostrarActivosBtn)
                        .addGap(18, 18, 18)
                        .addComponent(mostrarFinalizadosBtn)
                        .addGap(18, 18, 18)
                        .addComponent(actualizarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(checkoutBtn)
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
                    .addComponent(mostrarTodosBtn)
                    .addComponent(mostrarActivosBtn)
                    .addComponent(mostrarFinalizadosBtn)
                    .addComponent(actualizarBtn)
                    .addComponent(checkoutBtn)
                    .addComponent(regresarBtn))
                .addContainerGap(400, Short.MAX_VALUE))
        );

        pack();
    }

    private void mostrarTodosBtnActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarTablaCheckins();
    }

    private void mostrarActivosBtnActionPerformed(java.awt.event.ActionEvent evt) {
        filtrarCheckinsActivos();
    }

    private void mostrarFinalizadosBtnActionPerformed(java.awt.event.ActionEvent evt) {
        filtrarCheckinsFinalizados();
    }

    private void actualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarTablaCheckins();
        JOptionPane.showMessageDialog(this, "Tabla actualizada.");
    }

    private void checkoutBtnActionPerformed(java.awt.event.ActionEvent evt) {
        procesarCheckout();
    }

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarCheckins().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton actualizarBtn;
    private javax.swing.JButton checkoutBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mostrarActivosBtn;
    private javax.swing.JButton mostrarFinalizadosBtn;
    private javax.swing.JButton mostrarTodosBtn;
    private javax.swing.JButton regresarBtn;
}
