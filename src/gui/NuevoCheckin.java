package gui;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import mundo.Checkin;
import mundo.Habitaciones;
import mundo.Reserva;

public class NuevoCheckin extends javax.swing.JFrame {
    
    public NuevoCheckin() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null);
        cargarHabitacionesDisponibles();
        calcularDiasHospedaje();
    }
    
    private void cargarHabitacionesDisponibles() {
        cargarHabitacionesDisponibles(null);
    }
    
    private void cargarHabitacionesDisponibles(Long cedulaCliente) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione habitación");
        
        for (Habitaciones hab : Habitaciones.listaHabitaciones) {
            boolean habitacionDisponible = false;
            
            if (hab.getEstado()) {
                habitacionDisponible = true;
            }
            
            else if (cedulaCliente != null && estaHabitacionReservadaPorCliente(hab.getNumero(), cedulaCliente)) {
                habitacionDisponible = true;
            }
            
            if (habitacionDisponible) {
                model.addElement("Hab. " + hab.getNumero() + " - $" + hab.getPrecio());
            }
        }
        
        if (habitacionCheckin != null) {
            DefaultComboBoxModel<String> habitacionModel = (DefaultComboBoxModel<String>) habitacionCheckin.getModel();
            habitacionModel.removeAllElements();
            for (int i = 0; i < model.getSize(); i++) {
                habitacionModel.addElement(model.getElementAt(i));
            }
        }
    }
    
    private boolean estaHabitacionReservadaPorCliente(int numeroHabitacion, long cedulaCliente) {
        for (Reserva reserva : Reserva.listaReservas) {
            if (reserva.getNumeroHabitacion() == numeroHabitacion && reserva.getCedula() == cedulaCliente) {
                return true;
            }
        }
        return false;
    }
    
    private void actualizarHabitacionesSegunCedula() {
        try {
            String cedulaStr = cedulaField.getText().trim();
            if (!cedulaStr.isEmpty()) {
                long cedula = Long.parseLong(cedulaStr);
                cargarHabitacionesDisponibles(cedula);
            } else {
                cargarHabitacionesDisponibles(null);
            }
        } catch (NumberFormatException e) {
            cargarHabitacionesDisponibles(null);
        }
    }
    
    private void calcularDiasHospedaje() {
        try {
            String fechaIngreso = fechaIngresoField.getText().trim();
            String fechaSalida = fechaSalidaField.getText().trim();
            
            if (!fechaIngreso.isEmpty() && !fechaSalida.isEmpty() && 
                !fechaIngreso.equals("0  -  0  -  0") && !fechaSalida.equals("0  -  0  -  0") &&
                !fechaIngreso.equals("0-0-0") && !fechaSalida.equals("0-0-0")) {
                
                int dias = calcularDiasEntreFechas(fechaIngreso, fechaSalida);
                diasHospedajeField.setText(String.valueOf(dias));
            } else {
                diasHospedajeField.setText("0");
            }
        } catch (Exception e) {
            diasHospedajeField.setText("0");
        }
    }
    
    private int calcularDiasEntreFechas(String fechaIngreso, String fechaSalida) {
        try {
            String[] partesIngreso = fechaIngreso.split("-");
            String[] partesSalida = fechaSalida.split("-");
            
            if (partesIngreso.length < 3) {
                partesIngreso = fechaIngreso.split("  -  ");
                partesSalida = fechaSalida.split("  -  ");
            }
            
            if (partesIngreso.length >= 3 && partesSalida.length >= 3) {
                int diaIngreso = Integer.parseInt(partesIngreso[0].trim());
                int mesIngreso = Integer.parseInt(partesIngreso[1].trim());
                int añoIngreso = Integer.parseInt(partesIngreso[2].trim());
                
                int diaSalida = Integer.parseInt(partesSalida[0].trim());
                int mesSalida = Integer.parseInt(partesSalida[1].trim());
                int añoSalida = Integer.parseInt(partesSalida[2].trim());
                
                java.time.LocalDate fechaInicio = java.time.LocalDate.of(añoIngreso, mesIngreso, diaIngreso);
                java.time.LocalDate fechaFin = java.time.LocalDate.of(añoSalida, mesSalida, diaSalida);
                
                long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
                return Math.max(1, (int) dias);
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
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
    
    private void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        cedulaField.setText("");
        telefonoField.setText("");
        fechaIngresoField.setText("0  -  0  -  0");
        fechaSalidaField.setText("0  -  0  -  0");
        alergiasField.setText("");
        observacionesField.setText("");
        diasHospedajeField.setText("0");
        cargarHabitacionesDisponibles();
        if (habitacionCheckin.getItemCount() > 0) {
            habitacionCheckin.setSelectedIndex(0);
        }
    }
    
    private void deshabilitarCampos() {
        nombreField.setEnabled(false);
        apellidoField.setEnabled(false);
        cedulaField.setEnabled(false);
        telefonoField.setEnabled(false);
        fechaIngresoField.setEnabled(false);
        fechaSalidaField.setEnabled(false);
        alergiasField.setEnabled(false);
        observacionesField.setEnabled(false);
        habitacionCheckin.setEnabled(false);
        diasHospedajeField.setEnabled(false);
    }
    
    private void habilitarCampos() {
        nombreField.setEnabled(true);
        apellidoField.setEnabled(true);
        cedulaField.setEnabled(true);
        telefonoField.setEnabled(true);
        fechaIngresoField.setEnabled(true);
        fechaSalidaField.setEnabled(true);
        alergiasField.setEnabled(true);
        observacionesField.setEnabled(true);
        habitacionCheckin.setEnabled(true);
        diasHospedajeField.setEnabled(true);
    }

    //GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cedulaField = new javax.swing.JTextField();
        cedulaField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                actualizarHabitacionesSegunCedula();
            }
        });
        jLabel5 = new javax.swing.JLabel();
        telefonoField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fechaIngresoField = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        fechaSalidaField = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        habitacionCheckin = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        alergiasField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        diasHospedajeField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        observacionesField = new javax.swing.JTextField();
        guardarCheckinBtn = new javax.swing.JButton();
        buscarCheckinBtn = new javax.swing.JButton();
        eliminarCheckinBtn = new javax.swing.JButton();
        nuevoCheckinBtn = new javax.swing.JButton();
        finalizarCheckinBtn = new javax.swing.JButton();
        regresarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Check-in");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("CHECK-IN DE HUÉSPEDES");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("Cédula:");

        jLabel5.setText("Teléfono:");

        jLabel6.setText("Fecha de Ingreso:");

        fechaIngresoField.setText("0  -  0  -  0");
        fechaIngresoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcularDiasHospedaje();
            }
        });
        fechaIngresoField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                calcularDiasHospedaje();
            }
        });

        jLabel7.setText("Fecha de Salida:");

        fechaSalidaField.setText("0  -  0  -  0");
        fechaSalidaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calcularDiasHospedaje();
            }
        });
        fechaSalidaField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                calcularDiasHospedaje();
            }
        });

        jLabel8.setText("Habitación:");

        habitacionCheckin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione habitación" }));

        jLabel9.setText("Alergias:");

        jLabel10.setText("Días de Hospedaje:");

        diasHospedajeField.setEditable(false);
        diasHospedajeField.setText("0");

        jLabel11.setText("Observaciones:");

        guardarCheckinBtn.setText("Guardar Check-in");
        guardarCheckinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCheckinBtnActionPerformed(evt);
            }
        });

        buscarCheckinBtn.setText("Buscar");
        buscarCheckinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCheckinBtnActionPerformed(evt);
            }
        });

        eliminarCheckinBtn.setText("Eliminar");
        eliminarCheckinBtn.setBackground(new java.awt.Color(220, 128, 128)); // Rojo suave
        eliminarCheckinBtn.setForeground(java.awt.Color.WHITE); // Texto blanco
        eliminarCheckinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCheckinBtnActionPerformed(evt);
            }
        });

        nuevoCheckinBtn.setText("Nuevo");
        nuevoCheckinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoCheckinBtnActionPerformed(evt);
            }
        });

        finalizarCheckinBtn.setText("Finalizar Check-in");
        finalizarCheckinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarCheckinBtnActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaIngresoField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaSalidaField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(habitacionCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(alergiasField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(diasHospedajeField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(observacionesField)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(guardarCheckinBtn)
                            .addComponent(buscarCheckinBtn)
                            .addComponent(eliminarCheckinBtn)
                            .addComponent(nuevoCheckinBtn)
                            .addComponent(finalizarCheckinBtn)
                            .addComponent(regresarBtn))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(fechaIngresoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(fechaSalidaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(habitacionCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(alergiasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(diasHospedajeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(guardarCheckinBtn)
                        .addGap(18, 18, 18)
                        .addComponent(buscarCheckinBtn)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarCheckinBtn)
                        .addGap(18, 18, 18)
                        .addComponent(nuevoCheckinBtn)
                        .addGap(18, 18, 18)
                        .addComponent(finalizarCheckinBtn)
                        .addGap(18, 18, 18)
                        .addComponent(regresarBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void guardarCheckinBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (nombreField.getText().trim().isEmpty() || apellidoField.getText().trim().isEmpty() ||
                cedulaField.getText().trim().isEmpty() || telefonoField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.");
                return;
            }

            String habitacionSeleccionada = habitacionCheckin.getSelectedItem().toString();
            if (habitacionSeleccionada.equals("Seleccione habitación")) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una habitación.");
                return;
            }

            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            long cedula = Long.parseLong(cedulaField.getText().trim());
            long telefono = Long.parseLong(telefonoField.getText().trim());
            String fechaIngreso = fechaIngresoField.getText().trim();
            String fechaSalida = fechaSalidaField.getText().trim();
            String alergias = alergiasField.getText().trim();
            String observaciones = observacionesField.getText().trim();
            int numeroHabitacion = extraerNumeroHabitacion(habitacionSeleccionada);

            for (Checkin checkin : Checkin.listaCheckins) {
                if (checkin.getCedula() == cedula && checkin.getEstado().equals("Activo")) {
                    JOptionPane.showMessageDialog(this, "Ya existe un check-in activo para esta cédula.");
                    return;
                }
            }

            for (Checkin checkin : Checkin.listaCheckins) {
                if (checkin.getNumeroHabitacion() == numeroHabitacion && checkin.getEstado().equals("Activo")) {
                    JOptionPane.showMessageDialog(this, "La habitación " + numeroHabitacion + " ya está ocupada por otro check-in activo.");
                    return;
                }
            }
            
            boolean reservaEncontrada = false;
            for (Reserva reserva : Reserva.listaReservas) {
                if (reserva.getNumeroHabitacion() == numeroHabitacion && reserva.getCedula() == cedula) {
                    reservaEncontrada = true;
                    break;
                }
            }
            
            if (!reservaEncontrada) {
                boolean habitacionDisponible = false;
                for (Habitaciones hab : Habitaciones.listaHabitaciones) {
                    if (hab.getNumero() == numeroHabitacion && hab.getEstado()) {
                        habitacionDisponible = true;
                        break;
                    }
                }
                if (!habitacionDisponible) {
                    JOptionPane.showMessageDialog(this, "La habitación " + numeroHabitacion + " no está disponible.");
                    return;
                }
            }

            Checkin nuevoCheckin = new Checkin(nombre, apellido, cedula, telefono,
                    fechaIngreso, fechaSalida, alergias, numeroHabitacion, observaciones);

            Checkin.listaCheckins.add(nuevoCheckin);
            ocuparHabitacion(numeroHabitacion);

            String mensaje = "Check-in registrado exitosamente.\nHabitación " + numeroHabitacion + " asignada.";
            if (reservaEncontrada) {
                mensaje += "\n\n✅ Habitación asignada según reserva previa.";
            }
            
            JOptionPane.showMessageDialog(this, mensaje);
            limpiarCampos();
            cargarHabitacionesDisponibles();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cédula o teléfono no válidos. Solo se permiten números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el check-in: " + e.getMessage());
        }
    }

    private void buscarCheckinBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (cedulaField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la cédula para buscar el check-in.");
                return;
            }

            long cedulaBuscar = Long.parseLong(cedulaField.getText().trim());

            Checkin checkinEncontrado = null;
            for (Checkin checkin : Checkin.listaCheckins) {
                if (checkin.getCedula() == cedulaBuscar) {
                    checkinEncontrado = checkin;
                    break;
                }
            }

            if (checkinEncontrado != null) {
                nombreField.setText(checkinEncontrado.getNombre());
                apellidoField.setText(checkinEncontrado.getApellido());
                telefonoField.setText(String.valueOf(checkinEncontrado.getTelefono()));
                fechaIngresoField.setText(checkinEncontrado.getFechaIngreso());
                fechaSalidaField.setText(checkinEncontrado.getFechaSalida());
                alergiasField.setText(checkinEncontrado.getAlergias());
                observacionesField.setText(checkinEncontrado.getObservaciones());
                diasHospedajeField.setText(String.valueOf(checkinEncontrado.getDiasHospedaje()));

                cargarHabitacionesDisponibles(checkinEncontrado.getCedula());
                
                String habitacionTexto = "Hab. " + checkinEncontrado.getNumeroHabitacion();
                for (Habitaciones hab : Habitaciones.listaHabitaciones) {
                    if (hab.getNumero() == checkinEncontrado.getNumeroHabitacion()) {
                        habitacionTexto += " - $" + hab.getPrecio();
                        break;
                    }
                }
                habitacionCheckin.setSelectedItem(habitacionTexto);

                if (checkinEncontrado.getEstado().equals("Activo")) {
                    deshabilitarCampos();
                    JOptionPane.showMessageDialog(this, "Check-in encontrado. Estado: " + checkinEncontrado.getEstado());
                } else {
                    JOptionPane.showMessageDialog(this, "Check-in encontrado. Estado: " + checkinEncontrado.getEstado());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún check-in con la cédula: " + cedulaBuscar);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el check-in: " + e.getMessage());
        }
    }

    private void eliminarCheckinBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (cedulaField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la cédula del check-in a eliminar.");
                return;
            }

            long cedulaEliminar = Long.parseLong(cedulaField.getText().trim());

            Checkin checkinAEliminar = null;
            int indiceCheckin = -1;
            for (int i = 0; i < Checkin.listaCheckins.size(); i++) {
                Checkin checkin = Checkin.listaCheckins.get(i);
                if (checkin.getCedula() == cedulaEliminar) {
                    checkinAEliminar = checkin;
                    indiceCheckin = i;
                    break;
                }
            }

            if (checkinAEliminar != null) {
                String mensajeConfirmacion = "¿Está seguro de que desea eliminar el siguiente check-in?\n\n" +
                    "Nombre: " + checkinAEliminar.getNombre() + " " + checkinAEliminar.getApellido() + "\n" +
                    "Cédula: " + checkinAEliminar.getCedula() + "\n" +
                    "Habitación: " + checkinAEliminar.getNumeroHabitacion() + "\n" +
                    "Estado: " + checkinAEliminar.getEstado();

                int opcion = JOptionPane.showConfirmDialog(
                    this, 
                    mensajeConfirmacion, 
                    "Confirmar Eliminación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    liberarHabitacion(checkinAEliminar.getNumeroHabitacion());
                    Checkin.listaCheckins.remove(indiceCheckin);
                    JOptionPane.showMessageDialog(this, "Check-in eliminado exitosamente. Habitación " + checkinAEliminar.getNumeroHabitacion() + " liberada.");
                    limpiarCampos();
                    cargarHabitacionesDisponibles();
                } else {
                    JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún check-in con la cédula: " + cedulaEliminar);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el check-in: " + e.getMessage());
        }
    }

    private void nuevoCheckinBtnActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
        habilitarCampos();
    }

    private void finalizarCheckinBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (cedulaField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese la cédula del check-in a finalizar.");
                return;
            }

            long cedulaFinalizar = Long.parseLong(cedulaField.getText().trim());

            Checkin checkinAFinalizar = null;
            for (Checkin checkin : Checkin.listaCheckins) {
                if (checkin.getCedula() == cedulaFinalizar && checkin.getEstado().equals("Activo")) {
                    checkinAFinalizar = checkin;
                    break;
                }
            }

            if (checkinAFinalizar != null) {
                String mensajeConfirmacion = "¿Está seguro de que desea finalizar el check-in?\n\n" +
                    "Nombre: " + checkinAFinalizar.getNombre() + " " + checkinAFinalizar.getApellido() + "\n" +
                    "Habitación: " + checkinAFinalizar.getNumeroHabitacion() + "\n" +
                    "Días hospedados: " + checkinAFinalizar.getDiasHospedaje();

                int opcion = JOptionPane.showConfirmDialog(
                    this, 
                    mensajeConfirmacion, 
                    "Finalizar Check-in", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    liberarHabitacion(checkinAFinalizar.getNumeroHabitacion());
                    checkinAFinalizar.finalizarCheckin();
                    JOptionPane.showMessageDialog(this, "Check-in finalizado exitosamente. Habitación " + checkinAFinalizar.getNumeroHabitacion() + " liberada.");
                    limpiarCampos();
                    cargarHabitacionesDisponibles();
                } else {
                    JOptionPane.showMessageDialog(this, "Finalización cancelada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún check-in activo con la cédula: " + cedulaFinalizar);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener solo números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al finalizar el check-in: " + e.getMessage());
        }
    }

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoCheckin().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JTextField alergiasField;
    private javax.swing.JButton buscarCheckinBtn;
    private javax.swing.JTextField cedulaField;
    private javax.swing.JTextField diasHospedajeField;
    private javax.swing.JButton eliminarCheckinBtn;
    private javax.swing.JFormattedTextField fechaIngresoField;
    private javax.swing.JFormattedTextField fechaSalidaField;
    private javax.swing.JButton finalizarCheckinBtn;
    private javax.swing.JButton guardarCheckinBtn;
    private javax.swing.JComboBox<String> habitacionCheckin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton nuevoCheckinBtn;
    private javax.swing.JTextField observacionesField;
    private javax.swing.JButton regresarBtn;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField telefonoField;
}
