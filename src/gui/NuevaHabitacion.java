package gui;

import javax.swing.JOptionPane;
import mundo.Habitaciones;

public class NuevaHabitacion extends javax.swing.JFrame {

    public NuevaHabitacion() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null); 
        textdisponi.setEnabled(false);
        actualizarDisponibilidad();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textnumhabi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textpreciohabi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textdisponi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textdescripcionhab = new javax.swing.JTextArea();
        boleanestado = new javax.swing.JComboBox<>();
        Buttonguardar = new javax.swing.JButton();
        Buttoneliminar = new javax.swing.JButton();
        Buttonbuscar = new javax.swing.JButton();
        Buttonnuevo = new javax.swing.JButton();
        Buttonregresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("HABITACION");

        jLabel2.setText("Numero");

        textnumhabi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textnumhabiActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio");

        jLabel4.setText("Descripcion");

        jLabel5.setText("Estado");

        jLabel6.setText("Disponibilidad");

        textdescripcionhab.setColumns(20);
        textdescripcionhab.setRows(5);
        jScrollPane1.setViewportView(textdescripcionhab);

        boleanestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desocupado", "Ocupado" }));
        boleanestado.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Ocupado(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        boleanestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Desocupado(evt);
            }
        });

        Buttonguardar.setText("Guardar");
        Buttonguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonguardarActionPerformed(evt);
            }
        });

        Buttoneliminar.setText("Eliminar");
        Buttoneliminar.setBackground(new java.awt.Color(220, 128, 128)); // Rojo suave
        Buttoneliminar.setForeground(java.awt.Color.WHITE); // Texto blanco
        Buttoneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtoneliminarActionPerformed(evt);
            }
        });

        Buttonbuscar.setText("Buscar");
        Buttonbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonbuscarActionPerformed(evt);
            }
        });

        Buttonnuevo.setText("Nuevo");
        Buttonnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonnuevoActionPerformed(evt);
            }
        });

        Buttonregresar.setText("Regresar");
        Buttonregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonregresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(boleanestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textnumhabi)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textpreciohabi, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Buttonguardar)
                                    .addComponent(Buttoneliminar)
                                    .addComponent(Buttonbuscar)
                                    .addComponent(Buttonnuevo)
                                    .addComponent(Buttonregresar)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textdisponi, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(textnumhabi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textpreciohabi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Buttonguardar)
                        .addGap(18, 18, 18)
                        .addComponent(Buttoneliminar)
                        .addGap(18, 18, 18)
                        .addComponent(Buttonbuscar)
                        .addGap(18, 18, 18)
                        .addComponent(Buttonnuevo)
                        .addGap(18, 18, 18)
                        .addComponent(Buttonregresar)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(boleanestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textdisponi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }

    private void textnumhabiActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void Desocupado(java.awt.event.ActionEvent evt) {
        actualizarDisponibilidad();
    }

    private void Ocupado(javax.swing.event.AncestorEvent evt) {
    }

    private void ButtonguardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (textnumhabi.getText().trim().isEmpty() || textpreciohabi.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.");
                return;
            }

            int numero = Integer.parseInt(textnumhabi.getText().trim());
            
            boolean habitacionExiste = false;
            for (Habitaciones hab : Habitaciones.listaHabitaciones) {
                if (hab.getNumero() == numero) {
                    habitacionExiste = true;
                    break;
                }
            }

            if (habitacionExiste) {
                JOptionPane.showMessageDialog(this, "Ya existe una habitación con ese número.");
                return;
            }

            long precio = Long.parseLong(textpreciohabi.getText().trim());
            String descripcion = textdescripcionhab.getText().trim();
            boolean estado = boleanestado.getSelectedIndex() == 0;

            Habitaciones nuevaHabitacion = new Habitaciones(numero, precio, descripcion, estado);
            Habitaciones.listaHabitaciones.add(nuevaHabitacion);

            JOptionPane.showMessageDialog(this, "Habitación guardada correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número y precio deben ser valores numéricos válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la habitación: " + e.getMessage());
        }
    }

    private void ButtoneliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (textnumhabi.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el número de habitación a eliminar.");
                return;
            }

            int numero = Integer.parseInt(textnumhabi.getText().trim());
            
            Habitaciones habitacionAEliminar = null;
            int indice = -1;
            for (int i = 0; i < Habitaciones.listaHabitaciones.size(); i++) {
                Habitaciones hab = Habitaciones.listaHabitaciones.get(i);
                if (hab.getNumero() == numero) {
                    habitacionAEliminar = hab;
                    indice = i;
                    break;
                }
            }

            if (habitacionAEliminar != null) {
                String mensajeConfirmacion = "¿Está seguro de que desea eliminar la habitación?\n\n" +
                    "Número: " + habitacionAEliminar.getNumero() + "\n" +
                    "Precio: " + habitacionAEliminar.getPrecio() + "\n" +
                    "Estado: " + habitacionAEliminar.getDisponibilidad();

                int opcion = JOptionPane.showConfirmDialog(
                    this, 
                    mensajeConfirmacion, 
                    "Confirmar Eliminación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    Habitaciones.listaHabitaciones.remove(indice);
                    JOptionPane.showMessageDialog(this, "Habitación eliminada exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna habitación con el número: " + numero);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número debe ser un valor numérico válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la habitación: " + e.getMessage());
        }
    }

    private void ButtonbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (textnumhabi.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el número de habitación a buscar.");
                return;
            }

            int numero = Integer.parseInt(textnumhabi.getText().trim());
            
            Habitaciones habitacionEncontrada = null;
            for (Habitaciones hab : Habitaciones.listaHabitaciones) {
                if (hab.getNumero() == numero) {
                    habitacionEncontrada = hab;
                    break;
                }
            }

            if (habitacionEncontrada != null) {
                textpreciohabi.setText(String.valueOf(habitacionEncontrada.getPrecio()));
                textdescripcionhab.setText(habitacionEncontrada.getDescripcion());
                boleanestado.setSelectedIndex(habitacionEncontrada.getEstado() ? 0 : 1);
                actualizarDisponibilidad();

                JOptionPane.showMessageDialog(this, "Habitación encontrada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna habitación con el número: " + numero);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número debe ser un valor numérico válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la habitación: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        textnumhabi.setText("");
        textpreciohabi.setText("");
        textdescripcionhab.setText("");
        boleanestado.setSelectedIndex(0);
        actualizarDisponibilidad();
        textnumhabi.requestFocus();
    }

    private void actualizarDisponibilidad() {
        if (boleanestado.getSelectedIndex() == 0) {
            textdisponi.setText("Disponible");
        } else {
            textdisponi.setText("Ocupado");
        }
    }

    private void ButtonnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void ButtonregresarActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaHabitacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton Buttonbuscar;
    private javax.swing.JButton Buttoneliminar;
    private javax.swing.JButton Buttonguardar;
    private javax.swing.JButton Buttonnuevo;
    private javax.swing.JButton Buttonregresar;
    private javax.swing.JComboBox<String> boleanestado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textdescripcionhab;
    private javax.swing.JTextField textdisponi;
    private javax.swing.JTextField textnumhabi;
    private javax.swing.JTextField textpreciohabi;
}
