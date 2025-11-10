package gui;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import mundo.ProductoMinibar;
import mundo.ConsumoMinibar;
import mundo.Checkin;

public class GestionMinibar extends javax.swing.JFrame {
    
    public GestionMinibar() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(null); // Quitar el ícono de Java
        inicializarProductos();
        cargarProductos();
        cargarHabitacionesOcupadas();
        calcularTotal();
    }
    
    private void inicializarProductos() {
        // Limpiar productos existentes para aplicar cambios
        ProductoMinibar.listaProductos.clear();
        
        // Productos básicos del minibar con precios actualizados
        ProductoMinibar.listaProductos.add(new ProductoMinibar("Coca Cola", 5000, 50, "Bebidas", "Refresco de cola 350ml"));
        ProductoMinibar.listaProductos.add(new ProductoMinibar("Agua", 3000, 100, "Bebidas", "Agua mineral 500ml"));
        ProductoMinibar.listaProductos.add(new ProductoMinibar("Cerveza", 4500, 50, "Bebidas", "Cerveza Aguila 355ml"));
        ProductoMinibar.listaProductos.add(new ProductoMinibar("Snacks", 5000, 50, "Alimentos", "Papas fritas 150g"));
        ProductoMinibar.listaProductos.add(new ProductoMinibar("Chocolate", 3000, 50, "Alimentos", "Barra de chocolate 100g"));
        ProductoMinibar.listaProductos.add(new ProductoMinibar("Jugo", 3500, 20, "Bebidas", "Jugo de naranja 250ml"));
    }
    
    private void cargarProductos() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione producto");
        
        for (ProductoMinibar producto : ProductoMinibar.listaProductos) {
            if (producto.getStock() > 0) {
                model.addElement(producto.toString());
            }
        }
        
        productosCombo.setModel(model);
    }
    
    private void cargarHabitacionesOcupadas() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccione habitación");
        
        for (Checkin checkin : Checkin.listaCheckins) {
            if (checkin.getEstado().equals("Activo")) {
                model.addElement("Hab. " + checkin.getNumeroHabitacion() + " - " + checkin.getNombre() + " " + checkin.getApellido());
            }
        }
        
        habitacionesCombo.setModel(model);
    }
    
    private void calcularTotal() {
        try {
            int cantidad = Integer.parseInt(cantidadField.getText());
            String productoSeleccionado = productosCombo.getSelectedItem().toString();
            
            if (!productoSeleccionado.equals("Seleccione producto")) {
                // Extraer precio del string del producto
                String[] partes = productoSeleccionado.split("\\$");
                if (partes.length >= 2) {
                    String precioStr = partes[1].split(" ")[0];
                    double precio = Double.parseDouble(precioStr);
                    double total = cantidad * precio;
                    totalField.setText(String.valueOf(total));
                }
            } else {
                totalField.setText("0");
            }
        } catch (Exception e) {
            totalField.setText("0");
        }
    }
    
    private void registrarConsumo() {
        try {
            if (productosCombo.getSelectedItem().toString().equals("Seleccione producto")) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un producto.");
                return;
            }
            
            if (habitacionesCombo.getSelectedItem().toString().equals("Seleccione habitación")) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una habitación.");
                return;
            }
            
            int cantidad = Integer.parseInt(cantidadField.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
                return;
            }
            
            // Extraer información del producto
            String productoSeleccionado = productosCombo.getSelectedItem().toString();
            String nombreProducto = productoSeleccionado.split(" - \\$")[0];
            double precio = Double.parseDouble(productoSeleccionado.split("\\$")[1].split(" ")[0]);
            
            // Extraer información de la habitación
            String habitacionSeleccionada = habitacionesCombo.getSelectedItem().toString();
            int numeroHabitacion = Integer.parseInt(habitacionSeleccionada.split("Hab\\. ")[1].split(" -")[0]);
            
            // Buscar el check-in para obtener la cédula
            long cedulaCliente = 0;
            for (Checkin checkin : Checkin.listaCheckins) {
                if (checkin.getNumeroHabitacion() == numeroHabitacion && checkin.getEstado().equals("Activo")) {
                    cedulaCliente = checkin.getCedula();
                    break;
                }
            }
            
            if (cedulaCliente == 0) {
                JOptionPane.showMessageDialog(this, "No se encontró un check-in activo para esta habitación.");
                return;
            }
            
            // Verificar stock
            ProductoMinibar producto = null;
            for (ProductoMinibar p : ProductoMinibar.listaProductos) {
                if (p.getNombre().equals(nombreProducto)) {
                    producto = p;
                    break;
                }
            }
            
            if (producto == null || producto.getStock() < cantidad) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock del producto seleccionado.");
                return;
            }
            
            // Registrar consumo
            String fechaActual = java.time.LocalDate.now().toString();
            ConsumoMinibar nuevoConsumo = new ConsumoMinibar(cedulaCliente, numeroHabitacion, 
                nombreProducto, cantidad, precio, fechaActual);
            
            ConsumoMinibar.listaConsumos.add(nuevoConsumo);
            
            // Reducir stock
            producto.reducirStock(cantidad);
            
            JOptionPane.showMessageDialog(this, "Consumo registrado exitosamente.\n" +
                "Producto: " + nombreProducto + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Total: $" + nuevoConsumo.getSubtotal());
            
            limpiarCampos();
            cargarProductos();
            cargarHabitacionesOcupadas();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad válida.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el consumo: " + e.getMessage());
        }
    }
    
    private void limpiarCampos() {
        productosCombo.setSelectedIndex(0);
        habitacionesCombo.setSelectedIndex(0);
        cantidadField.setText("1");
        totalField.setText("0");
    }

    //GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productosCombo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        habitacionesCombo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cantidadField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        totalField = new javax.swing.JTextField();
        registrarBtn = new javax.swing.JButton();
        limpiarBtn = new javax.swing.JButton();
        reiniciarProductosBtn = new javax.swing.JButton();
        regresarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Minibar");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("GESTIÓN DE MINIBAR");

        jLabel2.setText("Producto:");

        productosCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione producto" }));
        productosCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularTotal();
            }
        });

        jLabel3.setText("Habitación:");

        habitacionesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione habitación" }));

        jLabel4.setText("Cantidad:");

        cantidadField.setText("1");
        cantidadField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                calcularTotal();
            }
        });

        jLabel5.setText("Total:");

        totalField.setEditable(false);
        totalField.setText("0");

        registrarBtn.setText("Registrar Consumo");
        registrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBtnActionPerformed(evt);
            }
        });

        limpiarBtn.setText("Limpiar");
        limpiarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarBtnActionPerformed(evt);
            }
        });

        reiniciarProductosBtn.setText("Reiniciar Productos");
        reiniciarProductosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarProductosBtnActionPerformed(evt);
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
                                .addComponent(productosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(habitacionesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registrarBtn)
                            .addComponent(limpiarBtn)
                            .addComponent(reiniciarProductosBtn)
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
                            .addComponent(productosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(habitacionesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cantidadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registrarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(limpiarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(reiniciarProductosBtn)
                        .addGap(18, 18, 18)
                        .addComponent(regresarBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void registrarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        registrarConsumo();
    }

    private void limpiarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void reiniciarProductosBtnActionPerformed(java.awt.event.ActionEvent evt) {
        inicializarProductos();
        cargarProductos();
        JOptionPane.showMessageDialog(this, "Productos reiniciados con precios actualizados.");
    }

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        Menu menuPrincipal = new Menu();
        menuPrincipal.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionMinibar().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JTextField cantidadField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton limpiarBtn;
    private javax.swing.JComboBox<String> productosCombo;
    private javax.swing.JButton regresarBtn;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JButton reiniciarProductosBtn;
    private javax.swing.JTextField totalField;
    private javax.swing.JComboBox<String> habitacionesCombo;
}
