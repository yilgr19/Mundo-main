
package gui;

import java.util.List;
import javax.swing.JOptionPane;
import mundo.Habitaciones;
import mundo.OperacionesHabitaciones;
import mundo.SistemaLogin;

public class Menu extends javax.swing.JFrame {
   
    public Menu() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null); // Quitar el ícono de Java
        actualizarInformacionUsuario();
        cargarHabitacionesIniciales();
    }
    
    private void actualizarInformacionUsuario() {
        String titulo = "SISTEMA DE HOTEL - " + SistemaLogin.obtenerNombreUsuario();
        this.setTitle(titulo);
    }

    private void cargarHabitacionesIniciales() {
        try {
            List<Habitaciones> habitaciones = OperacionesHabitaciones.listarTodas();
            Habitaciones.listaHabitaciones.clear();
            Habitaciones.listaHabitaciones.addAll(habitaciones);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las habitaciones: " + e.getMessage());
        }
    }
    


    //GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menu1 = new javax.swing.JMenu();
        salir = new javax.swing.JMenuItem();
        menu2 = new javax.swing.JMenu();
        registrar = new javax.swing.JMenuItem();
        menu3 = new javax.swing.JMenu();
        reservar = new javax.swing.JMenuItem();
        menu4 = new javax.swing.JMenu();
        menu5 = new javax.swing.JMenu();
        menu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menu1.setText("Archivo");

        cerrarSesion = new javax.swing.JMenuItem();
        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        menu1.add(cerrarSesion);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        menu1.add(salir);

        jMenuBar1.add(menu1);

        menu2.setText("Cliente");

        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        menu2.add(registrar);

        jMenuBar1.add(menu2);

        menu3.setText("Reservas");

        reservar.setText("Reservar");
        reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarActionPerformed(evt);
            }
        });
        menu3.add(reservar);

        consultarReservasItem = new javax.swing.JMenuItem();
        consultarReservasItem.setText("Consultar Reservas");
        consultarReservasItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarReservasItemActionPerformed(evt);
            }
        });
        menu3.add(consultarReservasItem);

        jMenuBar1.add(menu3);

        menu4.setText("Habitacion");
        
        registrarhabitacion = new javax.swing.JMenuItem();
        registrarhabitacion.setText("Registrar");
        registrarhabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarhabitacionActionPerformed(evt);
            }
        });
        menu4.add(registrarhabitacion);
        
        jMenuBar1.add(menu4);

        menu5.setText("Restaurante");
        jMenuBar1.add(menu5);

        menu6.setText("Check-in");
        
        nuevoCheckinItem = new javax.swing.JMenuItem();
        nuevoCheckinItem.setText("Nuevo Check-in");
        nuevoCheckinItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoCheckinItemActionPerformed(evt);
            }
        });
        menu6.add(nuevoCheckinItem);
        
        consultarCheckinsItem = new javax.swing.JMenuItem();
        consultarCheckinsItem.setText("Consultar Check-ins");
        consultarCheckinsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarCheckinsItemActionPerformed(evt);
            }
        });
        menu6.add(consultarCheckinsItem);
        
        jMenuBar1.add(menu6);

        menu8 = new javax.swing.JMenu();
        menu8.setText("Minibar");
        
        gestionMinibarItem = new javax.swing.JMenuItem();
        gestionMinibarItem.setText("Gestión Minibar");
        gestionMinibarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionMinibarItemActionPerformed(evt);
            }
        });
        menu8.add(gestionMinibarItem);
        
        jMenuBar1.add(menu8);

        menu7 = new javax.swing.JMenu();
        menu7.setText("Reportes");
        jMenuBar1.add(menu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {
    this.setVisible(false);
    NuevoCliente ventana = new NuevoCliente(); 
    ventana.setVisible(true);                 
    ventana.setLocationRelativeTo(this);  
    }

    private void reservarActionPerformed(java.awt.event.ActionEvent evt) {
        NuevaReserva nueva = new NuevaReserva();
        this.setVisible(false);
        nueva.setVisible(true);
    }

    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea cerrar sesión?", 
            "Cerrar Sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            SistemaLogin.cerrarSesion();
            this.setVisible(false);
            Login login = new Login();
            login.setVisible(true);
        }
    }

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea salir del sistema?", 
            "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void registrarhabitacionActionPerformed(java.awt.event.ActionEvent evt) {
        if (!SistemaLogin.tienePermiso("GESTION_HABITACIONES")) {
            JOptionPane.showMessageDialog(this, 
                "No tiene permisos para gestionar habitaciones.\nSolo los Administradores pueden acceder a esta función.", 
                "Sin Permisos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        NuevaHabitacion habitacion = new NuevaHabitacion();
        this.setVisible(false);
        habitacion.setVisible(true);
    }

    private void nuevoCheckinItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (!SistemaLogin.tienePermiso("CHECKIN_CHECKOUT")) {
            JOptionPane.showMessageDialog(this, 
                "No tiene permisos para gestionar check-ins.\nSolo los Recepcionistas y Administradores pueden acceder a esta función.", 
                "Sin Permisos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        NuevoCheckin checkin = new NuevoCheckin();
        this.setVisible(false);
        checkin.setVisible(true);
    }

    private void consultarCheckinsItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (!SistemaLogin.tienePermiso("CHECKIN_CHECKOUT")) {
            JOptionPane.showMessageDialog(this, 
                "No tiene permisos para consultar check-ins.\nSolo los Recepcionistas y Administradores pueden acceder a esta función.", 
                "Sin Permisos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ConsultarCheckins consultar = new ConsultarCheckins();
        this.setVisible(false);
        consultar.setVisible(true);
    }

    private void consultarReservasItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (!SistemaLogin.tienePermiso("RESERVAS")) {
            JOptionPane.showMessageDialog(this, 
                "No tiene permisos para consultar reservas.\nSolo los Recepcionistas y Administradores pueden acceder a esta función.", 
                "Sin Permisos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ConsultarReserva consultar = new ConsultarReserva();
        this.setVisible(false);
        consultar.setVisible(true);
    }

    private void gestionMinibarItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (!SistemaLogin.tienePermiso("MINIBAR")) {
            JOptionPane.showMessageDialog(this, 
                "No tiene permisos para gestionar el minibar.\nSolo los Recepcionistas y Administradores pueden acceder a esta función.", 
                "Sin Permisos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        GestionMinibar minibar = new GestionMinibar();
        this.setVisible(false);
        minibar.setVisible(true);
    }

   
    public static void main(String args[]) {
         java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menu1;
    private javax.swing.JMenu menu2;
    private javax.swing.JMenu menu3;
    private javax.swing.JMenu menu4;
    private javax.swing.JMenu menu5;
    private javax.swing.JMenu menu6;
    private javax.swing.JMenu menu7;
    private javax.swing.JMenu menu8;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenuItem consultarCheckinsItem;
    private javax.swing.JMenuItem consultarReservasItem;
    private javax.swing.JMenuItem gestionMinibarItem;
    private javax.swing.JMenuItem nuevoCheckinItem;
    private javax.swing.JMenuItem registrar;
    private javax.swing.JMenuItem registrarhabitacion;
    private javax.swing.JMenuItem reservar;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
