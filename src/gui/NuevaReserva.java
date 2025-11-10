
package gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mundo.Habitaciones;
import mundo.OperacionesHabitaciones;
import mundo.Reserva;


public class NuevaReserva extends javax.swing.JFrame {
    
    public NuevaReserva() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null); // Quitar el ícono de Java
        cargarHabitacionesDesdeBaseDeDatos();
        cargarHabitacionesDisponibles();
        
        // Probar el cálculo con las fechas de ejemplo
        java.awt.EventQueue.invokeLater(() -> {
            calcularDiasHospedaje();
        });
    }
    
    private void cargarHabitacionesDesdeBaseDeDatos() {
        try {
            List<Habitaciones> habitaciones = OperacionesHabitaciones.listarTodas();
            Habitaciones.listaHabitaciones.clear();
            Habitaciones.listaHabitaciones.addAll(habitaciones);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las habitaciones: " + e.getMessage());
        }
    }
    
    private void cargarHabitacionesDisponibles() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione habitación");
        
        for (Habitaciones hab : Habitaciones.listaHabitaciones) {
            if (hab.getEstado()) {
                model.addElement("Hab. " + hab.getNumero() + " - $" + hab.getPrecio());
            }
        }
        
        if (tipohabitacion != null) {
            DefaultComboBoxModel<String> tipoModel = (DefaultComboBoxModel<String>) tipohabitacion.getModel();
            tipoModel.removeAllElements();
            for (int i = 0; i < model.getSize(); i++) {
                tipoModel.addElement(model.getElementAt(i));
            }
        }
    }
   
    //GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fechaentrada = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        tipohabitacion = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numeroclientes = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        diasHospedaje = new javax.swing.JTextField();
        horaentrada = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        fechasalida = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        horasalida = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nombrereserva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        apellidoreserva = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cedulareserva = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        telefonoreserva = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        pago = new javax.swing.JComboBox<>();
        buscarreserva = new javax.swing.JButton();
        modificarreserva = new javax.swing.JButton();
        crearreserva = new javax.swing.JButton();
        eliminarreserva = new javax.swing.JButton();
        regresarmenu = new javax.swing.JButton();
        nuevoBtn = new javax.swing.JButton();

        jLabel4.setText("Fecha de entrada:");

        jButton3.setText("Reservar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Fecha de entrada:");
        jLabel1.setForeground(java.awt.Color.BLACK);

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 2, 24)); // NOI18N
        jLabel2.setText("Pago");
        jLabel2.setForeground(java.awt.Color.BLACK);

        fechaentrada.setText("0  -  0  -  0");
        fechaentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaentradaActionPerformed(evt);
            }
        });
        fechaentrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcularDiasHospedaje();
            }
        });
        fechaentrada.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                calcularDiasHospedaje();
            }
        });

        jLabel5.setText("Tipo de Habitacion: ");
        jLabel5.setForeground(java.awt.Color.BLACK);

        tipohabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doble", "Individual" }));
        tipohabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Doble(evt);
            }
        });

        jLabel6.setText("Fecha de Salida:");
        jLabel6.setForeground(java.awt.Color.BLACK);

        jLabel7.setText("Numero de Húespedes");
        jLabel7.setForeground(java.awt.Color.BLACK);

        numeroclientes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        jLabel8.setText("Hora:");
        jLabel8.setForeground(java.awt.Color.BLACK);

        jLabel17.setText("Días de Hospedaje:");
        jLabel17.setForeground(java.awt.Color.BLACK);

        diasHospedaje.setEditable(false);
        diasHospedaje.setText("0");

        horaentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaentradaActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        fechasalida.setText("0  -  0  -  0");
        fechasalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechasalidaActionPerformed(evt);
            }
        });
        fechasalida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcularDiasHospedaje();
            }
        });
        fechasalida.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                calcularDiasHospedaje();
            }
        });

        jLabel9.setText("Hora:");
        jLabel9.setForeground(java.awt.Color.BLACK);

        horasalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horasalidaActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 2, 24)); // NOI18N
        jLabel10.setText("NUEVA RESERVA");
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setForeground(java.awt.Color.BLACK);

        jLabel11.setText("Nombre");
        jLabel11.setForeground(java.awt.Color.BLACK);

        nombrereserva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombrereserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombrereservaActionPerformed(evt);
            }
        });

        jLabel12.setText("Apellido");
        jLabel12.setForeground(java.awt.Color.BLACK);

        apellidoreserva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        apellidoreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoreservaActionPerformed(evt);
            }
        });

        jLabel13.setText("Cedula");
        jLabel13.setForeground(java.awt.Color.BLACK);

        cedulareserva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cedulareserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulareservaActionPerformed(evt);
            }
        });

        jLabel14.setText("Telefono ");
        jLabel14.setForeground(java.awt.Color.BLACK);

        telefonoreserva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        telefonoreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoreservaActionPerformed(evt);
            }
        });


        jLabel16.setText("Metodo de Pago");
        jLabel16.setForeground(java.awt.Color.BLACK);

        pago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transferencia", "Efectivo", "Tarjeta" }));
        pago.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoDoble(evt);
            }
        });

        buscarreserva.setText("Buscar");
        buscarreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarreservaActionPerformed(evt);
            }
        });

        modificarreserva.setText("Modificar");
        modificarreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarreservaActionPerformed(evt);
            }
        });

        crearreserva.setText("Reservar");
        crearreserva.setSelected(true);
        crearreserva.setBackground(new java.awt.Color(128, 220, 128)); // Verde suave
        crearreserva.setForeground(java.awt.Color.WHITE); // Texto blanco
        crearreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearreservaActionPerformed(evt);
            }
        });

        eliminarreserva.setText("Eliminar");
        eliminarreserva.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        eliminarreserva.setBackground(new java.awt.Color(220, 128, 128)); // Rojo suave
        eliminarreserva.setForeground(java.awt.Color.WHITE); // Texto blanco
        eliminarreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarreservaActionPerformed(evt);
            }
        });

        regresarmenu.setText("Regresar");
        regresarmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarmenuActionPerformed(evt);
            }
        });

        nuevoBtn.setText("Nuevo");
        nuevoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        // Asegurar que todos los componentes sean visibles
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tipohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numeroclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diasHospedaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(fechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fechaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(horaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(horasalida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nombrereserva, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(apellidoreserva, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cedulareserva)
                                            .addComponent(telefonoreserva, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(crearreserva)
                                .addGap(15, 15, 15)
                                .addComponent(nuevoBtn)
                                .addGap(5, 5, 5)
                                .addComponent(buscarreserva)
                                .addGap(5, 5, 5)
                                .addComponent(eliminarreserva)
                                .addGap(5, 5, 5)
                                .addComponent(modificarreserva)
                                .addGap(5, 5, 5)
                                .addComponent(regresarmenu))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fechaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(horaentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11)
                                            .addComponent(nombrereserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fechasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(horasalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12)
                                            .addComponent(apellidoreserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tipohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(numeroclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(diasHospedaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13)
                                            .addComponent(cedulareserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(telefonoreserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crearreserva)
                    .addComponent(nuevoBtn)
                    .addComponent(buscarreserva)
                    .addComponent(eliminarreserva)
                    .addComponent(modificarreserva)
                    .addComponent(regresarmenu))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    private void fechaentradaActionPerformed(java.awt.event.ActionEvent evt) {
        calcularDiasHospedaje();
    }

    private void Doble(java.awt.event.ActionEvent evt) {
    }

    private void horaentradaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void fechasalidaActionPerformed(java.awt.event.ActionEvent evt) {
        calcularDiasHospedaje();
    }

    private void horasalidaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void nombrereservaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void apellidoreservaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cedulareservaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void telefonoreservaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void pagoDoble(java.awt.event.ActionEvent evt) {
    }

    private void buscarreservaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Verificar que se haya ingresado una cédula
            if (cedulareserva.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la cédula para buscar la reserva.");
                return;
            }
            
            long cedulaBuscar = Long.parseLong(cedulareserva.getText().trim());
            
            // Buscar la reserva en la lista
            Reserva reservaEncontrada = null;
            for (Reserva reserva : Reserva.listaReservas) {
                if (reserva.getCedula() == cedulaBuscar) {
                    reservaEncontrada = reserva;
                    break;
                }
            }
            
            if (reservaEncontrada != null) {
                // Llenar los campos con los datos de la reserva encontrada
                nombrereserva.setText(reservaEncontrada.getNombre());
                apellidoreserva.setText(reservaEncontrada.getApellido());
                telefonoreserva.setText(String.valueOf(reservaEncontrada.getTelefono()));
                fechaentrada.setText(reservaEncontrada.getFechaEntrada());
                fechasalida.setText(reservaEncontrada.getFechaSalida());
                
                // Separar hora y AM/PM para los campos correspondientes
                String[] horaEntradaParts = reservaEncontrada.getHoraEntrada().split(" ");
                if (horaEntradaParts.length >= 2) {
                    horaentrada.setText(horaEntradaParts[0]);
                    jComboBox2.setSelectedItem(horaEntradaParts[1]);
                }
                
                String[] horaSalidaParts = reservaEncontrada.getHoraSalida().split(" ");
                if (horaSalidaParts.length >= 2) {
                    horasalida.setText(horaSalidaParts[0]);
                    jComboBox3.setSelectedItem(horaSalidaParts[1]);
                }
                
                // Configurar el tipo de habitación mostrando la habitación específica
                mostrarHabitacionEnDropdown(reservaEncontrada.getNumeroHabitacion());
                numeroclientes.setValue(reservaEncontrada.getNumHuespedes());
                pago.setSelectedItem(reservaEncontrada.getMetodoPago());
                
                // Calcular días de hospedaje
                calcularDiasHospedaje();
                
                // Deshabilitar todos los campos para evitar edición accidental
                deshabilitarCampos();
                
                JOptionPane.showMessageDialog(this, "Reserva encontrada exitosamente. Habitación " + reservaEncontrada.getNumeroHabitacion() + " asignada. Use el botón 'Modificar' para editar.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna reserva con la cédula: " + cedulaBuscar);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la reserva: " + e.getMessage());
        }
    }

    private void modificarreservaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Verificar que se haya ingresado una cédula
            if (cedulareserva.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la cédula de la reserva a modificar.");
                return;
            }
            
            long cedulaModificar = Long.parseLong(cedulareserva.getText().trim());
            
            // Buscar la reserva en la lista
            Reserva reservaAModificar = null;
            for (int i = 0; i < Reserva.listaReservas.size(); i++) {
                Reserva reserva = Reserva.listaReservas.get(i);
                if (reserva.getCedula() == cedulaModificar) {
                    reservaAModificar = reserva;
                    break;
                }
            }
            
            if (reservaAModificar != null) {
                // Habilitar campos para edición
                habilitarCampos();
                
                // Llenar los campos con los datos actuales
                nombrereserva.setText(reservaAModificar.getNombre());
                apellidoreserva.setText(reservaAModificar.getApellido());
                telefonoreserva.setText(String.valueOf(reservaAModificar.getTelefono()));
                fechaentrada.setText(reservaAModificar.getFechaEntrada());
                fechasalida.setText(reservaAModificar.getFechaSalida());
                
                // Separar hora y AM/PM para los campos correspondientes
                String[] horaEntradaParts = reservaAModificar.getHoraEntrada().split(" ");
                if (horaEntradaParts.length >= 2) {
                    horaentrada.setText(horaEntradaParts[0]);
                    jComboBox2.setSelectedItem(horaEntradaParts[1]);
                }
                
                String[] horaSalidaParts = reservaAModificar.getHoraSalida().split(" ");
                if (horaSalidaParts.length >= 2) {
                    horasalida.setText(horaSalidaParts[0]);
                    jComboBox3.setSelectedItem(horaSalidaParts[1]);
                }
                
                // Configurar el tipo de habitación mostrando la habitación específica
                mostrarHabitacionEnDropdown(reservaAModificar.getNumeroHabitacion());
                numeroclientes.setValue(reservaAModificar.getNumHuespedes());
                pago.setSelectedItem(reservaAModificar.getMetodoPago());
                
                // Calcular días de hospedaje
                calcularDiasHospedaje();
                
                // Cambiar el texto del botón crearreserva a "Actualizar"
                crearreserva.setText("Actualizar");
                
                JOptionPane.showMessageDialog(this, "Reserva cargada para modificación. Habitación " + reservaAModificar.getNumeroHabitacion() + " asignada. Realice los cambios necesarios y haga clic en 'Actualizar'.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna reserva con la cédula: " + cedulaModificar);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la reserva para modificación: " + e.getMessage());
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void crearreservaActionPerformed(java.awt.event.ActionEvent evt) {
 try {
        String nombre = nombrereserva.getText().trim();
        String apellido = apellidoreserva.getText().trim();
        long cedula = Long.parseLong(cedulareserva.getText().trim());
        long telefono = Long.parseLong(telefonoreserva.getText().trim());
        String fechaEntrada = fechaentrada.getText().trim();
        String horaEntrada = horaentrada.getText().trim() + " " + jComboBox2.getSelectedItem().toString();
        String fechaSalida = fechasalida.getText().trim();
        String horaSalida = horasalida.getText().trim() + " " + jComboBox3.getSelectedItem().toString();
        String habitacionSeleccionada = tipohabitacion.getSelectedItem().toString();
        int numHuespedes = (int) numeroclientes.getValue();
        String metodoPago = pago.getSelectedItem().toString();

            if (habitacionSeleccionada.equals("Seleccione habitación")) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una habitación.");
                return;
            }

            int numeroHabitacion = extraerNumeroHabitacion(habitacionSeleccionada);

            // Verificar si es una actualización o creación nueva
            if ("Actualizar".equals(crearreserva.getText())) {
                // Modo actualización - buscar y actualizar la reserva existente
                boolean reservaEncontrada = false;
                for (Reserva reserva : Reserva.listaReservas) {
                    if (reserva.getCedula() == cedula) {
                        int habitacionAnterior = reserva.getNumeroHabitacion();
                        
                        reserva.setNombre(nombre);
                        reserva.setApellido(apellido);
                        reserva.setTelefono(telefono);
                        reserva.setFechaEntrada(fechaEntrada);
                        reserva.setHoraEntrada(horaEntrada);
                        reserva.setFechaSalida(fechaSalida);
                        reserva.setHoraSalida(horaSalida);
                        reserva.setTipoHabitacion(habitacionSeleccionada);
                        reserva.setNumHuespedes(numHuespedes);
                        reserva.setMetodoPago(metodoPago);
                        
                        if (habitacionAnterior != numeroHabitacion) {
                            liberarHabitacion(habitacionAnterior);
                            ocuparHabitacion(numeroHabitacion);
                            reserva.setNumeroHabitacion(numeroHabitacion);
                        }
                        
                        reservaEncontrada = true;
                        break;
                    }
                }
                
                if (reservaEncontrada) {
                    JOptionPane.showMessageDialog(this, "Reserva actualizada correctamente.");
                    crearreserva.setText("Reservar");
                    deshabilitarCampos();
                    cargarHabitacionesDisponibles();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: No se pudo encontrar la reserva para actualizar.");
                }
            } else {
                ocuparHabitacion(numeroHabitacion);

        Reserva nuevaReserva = new Reserva(
            nombre, apellido, cedula, telefono,
            fechaEntrada, horaEntrada, fechaSalida, horaSalida,
                    habitacionSeleccionada, numHuespedes, metodoPago, numeroHabitacion
        );

        Reserva.listaReservas.add(nuevaReserva);
                JOptionPane.showMessageDialog(this, "Reserva creada correctamente. Habitación " + numeroHabitacion + " asignada.");
                limpiarCampos();
                cargarHabitacionesDisponibles();
            }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Cédula o teléfono no válidos. Solo se permiten números.");
    } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la reserva: " + e.getMessage());
        }
    }
    
    private int extraerNumeroHabitacion(String habitacionTexto) {
        String[] partes = habitacionTexto.split(" ");
        if (partes.length >= 2) {
            return Integer.parseInt(partes[1]);
        }
        return 0;
    }
    
    private void ocuparHabitacion(int numeroHabitacion) {
        for (Habitaciones hab : Habitaciones.listaHabitaciones) {
            if (hab.getNumero() == numeroHabitacion) {
                hab.setEstado(false);
                break;
            }
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
    
    private void calcularDiasHospedaje() {
        try {
            String fechaEntrada = fechaentrada.getText().trim();
            String fechaSalida = fechasalida.getText().trim();
            
            // Verificar que las fechas no estén vacías o en formato por defecto
            if (!fechaEntrada.isEmpty() && !fechaSalida.isEmpty() && 
                !fechaEntrada.equals("0  -  0  -  0") && !fechaSalida.equals("0  -  0  -  0") &&
                !fechaEntrada.equals("0-0-0") && !fechaSalida.equals("0-0-0")) {
                
                int dias = calcularDiasEntreFechas(fechaEntrada, fechaSalida);
                diasHospedaje.setText(String.valueOf(dias));
                System.out.println("Fechas: " + fechaEntrada + " a " + fechaSalida + " = " + dias + " días");
            } else {
                diasHospedaje.setText("0");
            }
        } catch (Exception e) {
            diasHospedaje.setText("0");
            System.out.println("Error en calcularDiasHospedaje: " + e.getMessage());
        }
    }
    
    private int calcularDiasEntreFechas(String fechaEntrada, String fechaSalida) {
        try {
            // Intentar primero con formato "día-mes-año" (sin espacios)
            String[] partesEntrada = fechaEntrada.split("-");
            String[] partesSalida = fechaSalida.split("-");
            
            // Si no funciona con guiones, intentar con espacios "día - mes - año"
            if (partesEntrada.length < 3) {
                partesEntrada = fechaEntrada.split("  -  ");
                partesSalida = fechaSalida.split("  -  ");
            }
            
            if (partesEntrada.length >= 3 && partesSalida.length >= 3) {
                int diaEntrada = Integer.parseInt(partesEntrada[0].trim());
                int mesEntrada = Integer.parseInt(partesEntrada[1].trim());
                int añoEntrada = Integer.parseInt(partesEntrada[2].trim());
                
                int diaSalida = Integer.parseInt(partesSalida[0].trim());
                int mesSalida = Integer.parseInt(partesSalida[1].trim());
                int añoSalida = Integer.parseInt(partesSalida[2].trim());
                
                java.time.LocalDate fechaInicio = java.time.LocalDate.of(añoEntrada, mesEntrada, diaEntrada);
                java.time.LocalDate fechaFin = java.time.LocalDate.of(añoSalida, mesSalida, diaSalida);
                
                long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
                return Math.max(1, (int) dias);
            }
        } catch (Exception e) {
            System.out.println("Error calculando días: " + e.getMessage());
            return 0;
        }
        return 0;
    }
    
    private void mostrarHabitacionEnDropdown(int numeroHabitacion) {
        String habitacionTexto = "Hab. " + numeroHabitacion;
        
        for (Habitaciones hab : Habitaciones.listaHabitaciones) {
            if (hab.getNumero() == numeroHabitacion) {
                habitacionTexto += " - $" + hab.getPrecio();
                break;
            }
        }
        
        tipohabitacion.setSelectedItem(habitacionTexto);
    }

 
    private void eliminarreservaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Verificar que se haya ingresado una cédula
            if (cedulareserva.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la cédula de la reserva a eliminar.");
                return;
            }
            
            long cedulaEliminar = Long.parseLong(cedulareserva.getText().trim());
            
            // Buscar la reserva en la lista
            Reserva reservaAEliminar = null;
            int indiceReserva = -1;
            for (int i = 0; i < Reserva.listaReservas.size(); i++) {
                Reserva reserva = Reserva.listaReservas.get(i);
                if (reserva.getCedula() == cedulaEliminar) {
                    reservaAEliminar = reserva;
                    indiceReserva = i;
                    break;
                }
            }
            
            if (reservaAEliminar != null) {
                // Mostrar información de la reserva y pedir confirmación
                String mensajeConfirmacion = "¿Está seguro de que desea eliminar la siguiente reserva?\n\n" +
                    "Nombre: " + reservaAEliminar.getNombre() + " " + reservaAEliminar.getApellido() + "\n" +
                    "Cédula: " + reservaAEliminar.getCedula() + "\n" +
                    "Teléfono: " + reservaAEliminar.getTelefono() + "\n" +
                    "Fecha Entrada: " + reservaAEliminar.getFechaEntrada() + "\n" +
                    "Fecha Salida: " + reservaAEliminar.getFechaSalida() + "\n" +
                    "Tipo Habitación: " + reservaAEliminar.getTipoHabitacion() + "\n" +
                    "Número Huéspedes: " + reservaAEliminar.getNumHuespedes() + "\n" +
                    "Método Pago: " + reservaAEliminar.getMetodoPago();
                
                int opcion = JOptionPane.showConfirmDialog(
                    this, 
                    mensajeConfirmacion, 
                    "Confirmar Eliminación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (opcion == JOptionPane.YES_OPTION) {
                    liberarHabitacion(reservaAEliminar.getNumeroHabitacion());
                    Reserva.listaReservas.remove(indiceReserva);
                    JOptionPane.showMessageDialog(this, "Reserva eliminada exitosamente. Habitación " + reservaAEliminar.getNumeroHabitacion() + " liberada.");
                    limpiarCampos();
                    cargarHabitacionesDisponibles();
                } else {
                    JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna reserva con la cédula: " + cedulaEliminar);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la reserva: " + e.getMessage());
        }
    }

    private void regresarmenuActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    private void nuevoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        nombrereserva.setText("");
        apellidoreserva.setText("");
        cedulareserva.setText("");
        telefonoreserva.setText("");
        fechaentrada.setText("0  -  0  -  0");
        fechasalida.setText("0  -  0  -  0");
        horaentrada.setText("");
        horasalida.setText("");
        diasHospedaje.setText("0");
        cargarHabitacionesDisponibles();
        if (tipohabitacion.getItemCount() > 0) {
            tipohabitacion.setSelectedIndex(0);
        }
        numeroclientes.setValue(1);
        pago.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        habilitarCampos();
    }

    private void deshabilitarCampos() {
        nombrereserva.setEnabled(false);
        apellidoreserva.setEnabled(false);
        telefonoreserva.setEnabled(false);
        fechaentrada.setEnabled(false);
        fechasalida.setEnabled(false);
        horaentrada.setEnabled(false);
        horasalida.setEnabled(false);
        tipohabitacion.setEnabled(false);
        numeroclientes.setEnabled(false);
        pago.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        diasHospedaje.setEnabled(false);
    }

    private void habilitarCampos() {
        nombrereserva.setEnabled(true);
        apellidoreserva.setEnabled(true);
        telefonoreserva.setEnabled(true);
        fechaentrada.setEnabled(true);
        fechasalida.setEnabled(true);
        horaentrada.setEnabled(true);
        horasalida.setEnabled(true);
        tipohabitacion.setEnabled(true);
        numeroclientes.setEnabled(true);
        pago.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBox3.setEnabled(true);
        diasHospedaje.setEnabled(true);
    }

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaReserva().setVisible(true);
            }
        });
    }
           

    // Variables declaration - do not modify
    private javax.swing.JTextField apellidoreserva;
    private javax.swing.JButton buscarreserva;
    private javax.swing.JTextField cedulareserva;
    private javax.swing.JButton crearreserva;
    private javax.swing.JTextField diasHospedaje;
    private javax.swing.JButton eliminarreserva;
    private javax.swing.JButton regresarmenu;
    private javax.swing.JFormattedTextField fechaentrada;
    private javax.swing.JFormattedTextField fechasalida;
    private javax.swing.JTextField horaentrada;
    private javax.swing.JTextField horasalida;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton modificarreserva;
    private javax.swing.JTextField nombrereserva;
    private javax.swing.JSpinner numeroclientes;
    private javax.swing.JComboBox<String> pago;
    private javax.swing.JTextField telefonoreserva;
    private javax.swing.JComboBox<String> tipohabitacion;
    private javax.swing.JButton nuevoBtn;
}
