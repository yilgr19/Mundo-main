
package gui;

import javax.swing.JOptionPane;
import mundo.Cliente;
import mundo.OperacionesClientes;


public class NuevoCliente extends javax.swing.JFrame {
  
    public NuevoCliente() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null);
    }

   
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textcedula = new javax.swing.JTextField();
        textnombre = new javax.swing.JTextField();
        textapellido = new javax.swing.JTextField();
        texttelefono = new javax.swing.JTextField();
        textdireccion = new javax.swing.JTextField();
        textcorreo = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        Buttoneliminar = new javax.swing.JButton();
        Buttonbuscar = new javax.swing.JButton();
        Buttonnuevo = new javax.swing.JButton();
        Buttonregresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 18));
        jLabel1.setText("REGISTRA CLIENTE");

        jLabel2.setText("Cedula");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Telefono");

        jLabel6.setText("Direccion");

        jLabel7.setText("Correo");

        textcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textcedulaActionPerformed(evt);
            }
        });

        textapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textapellidoActionPerformed(evt);
            }
        });

        textdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textdireccionActionPerformed(evt);
            }
        });

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        Buttoneliminar.setText("Eliminar");
        Buttoneliminar.setBackground(new java.awt.Color(220, 128, 128));
        Buttoneliminar.setForeground(java.awt.Color.WHITE);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(textcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textnombre))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textapellido))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textcorreo)
                            .addComponent(texttelefono)
                            .addComponent(textdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardar)
                    .addComponent(Buttoneliminar)
                    .addComponent(Buttonbuscar)
                    .addComponent(Buttonnuevo)
                    .addComponent(Buttonregresar))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(guardar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Buttoneliminar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Buttonbuscar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(texttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Buttonnuevo)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Buttonregresar)
                .addGap(15, 15, 15))
        );

        pack();
    }

    private void textcedulaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void textapellidoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void textdireccionActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {
       try {
         String  ced=this.textcedula.getText();
        ced=ced.trim();
        String  nom=this.textnombre.getText();
        nom=nom.trim();
        String  ap=this.textapellido.getText();
        ap=ap.trim();
        String  tel=this.texttelefono.getText();
        tel=tel.trim();
        String  dir=this.textdireccion.getText();
        dir=dir.trim();
        String  ema=this.textcorreo.getText();
        ema=ema.trim();

        Cliente c = new Cliente(ced,nom,ap,tel,dir,ema);
        OperacionesClientes.guardar(c);   
         
       
        JOptionPane.showMessageDialog(this, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
      
        this.textcedula.setText("");
        this.textnombre.setText("");
        this.textapellido.setText("");
        this.texttelefono.setText("");
        this.textdireccion.setText("");
        this.textcorreo.setText("");
        
               
      }
       catch(Exception e) {  
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " , "Error", JOptionPane.ERROR_MESSAGE);
       } 
        
    }

    private void ButtoneliminarActionPerformed(java.awt.event.ActionEvent evt) {
         try {
        String cedula = this.textcedula.getText().trim();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente a eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                                                        "¿Está seguro de eliminar al cliente con cédula: " + cedula + "?", 
                                                        "Confirmar Eliminación", 
                                                        JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = OperacionesClientes.eliminar(cedula); 
            
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un cliente con la cédula ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al intentar eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }                                           

    }

    private void ButtonbuscarActionPerformed(java.awt.event.ActionEvent evt) {
     try {
        String cedula = this.textcedula.getText().trim();
        
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente a buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Cliente clienteEncontrado = OperacionesClientes.buscar(cedula); 
        
        if (clienteEncontrado != null) { 
            this.textnombre.setText(clienteEncontrado.getNombre());
            this.textapellido.setText(clienteEncontrado.getApellido());
            this.texttelefono.setText(clienteEncontrado.getTelefono());
            this.textdireccion.setText(clienteEncontrado.getDireccion());
             
            
            JOptionPane.showMessageDialog(this, "Cliente encontrado y datos cargados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            this.textnombre.setText("");
            this.textapellido.setText("");
            this.texttelefono.setText("");
            this.textdireccion.setText("");
            this.textcorreo.setText("");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }                                           

    }

    private void ButtonnuevoActionPerformed(java.awt.event.ActionEvent evt) {                                 
      limpiarCampos();
      this.textcedula.requestFocus();          
    }

    private void ButtonregresarActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    private void limpiarCampos() {
        this.textcedula.setText("");
        this.textnombre.setText("");
        this.textapellido.setText("");
        this.texttelefono.setText("");
        this.textdireccion.setText("");
        this.textcorreo.setText("");
    }
    
    public static void main(String args[]) {
        
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
          java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new NuevoCliente().setVisible(true);
        }
    });
} 

    private javax.swing.JButton Buttonbuscar;
    private javax.swing.JButton Buttoneliminar;
    private javax.swing.JButton Buttonnuevo;
    private javax.swing.JButton Buttonregresar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField textapellido;
    private javax.swing.JTextField textcedula;
    private javax.swing.JTextField textcorreo;
    private javax.swing.JTextField textdireccion;
    private javax.swing.JTextField textnombre;
    private javax.swing.JTextField texttelefono;
}
