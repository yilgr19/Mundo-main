package gui;

import javax.swing.JOptionPane;
import mundo.SistemaLogin;

public class Login extends javax.swing.JFrame {
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null); // Quitar el ícono de Java
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void autenticarUsuario() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", 
                "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (SistemaLogin.autenticarUsuario(username, password)) {
            JOptionPane.showMessageDialog(this, 
                "Bienvenido " + SistemaLogin.obtenerNombreUsuario() + "\n" +
                "Rol: " + SistemaLogin.obtenerRolUsuario(), 
                "Login Exitoso", JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir menú principal
            this.setVisible(false);
            Menu menuPrincipal = new Menu();
            menuPrincipal.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "Usuario o contraseña incorrectos.\nPor favor intente nuevamente.", 
                "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            
            // Limpiar campos
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }
    
    
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        salirBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Login - Hotel");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA DE HOTEL");
        jLabel1.setToolTipText("");

        jLabel2.setText("Usuario:");

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField.requestFocus();
            }
        });

        jLabel3.setText("Contraseña:");

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autenticarUsuario();
            }
        });

        loginBtn.setText("Iniciar Sesión");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        salirBtn.setText("Salir");
        salirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(passwordField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Integer.MAX_VALUE)
                                .addComponent(loginBtn)
                                .addGap(18, 18, 18)
                                .addComponent(salirBtn)
                                .addGap(0, 0, Integer.MAX_VALUE)))
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn)
                    .addComponent(salirBtn))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        autenticarUsuario();
    }

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea salir del sistema?", 
            "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton salirBtn;
    private javax.swing.JTextField usernameField;
}
