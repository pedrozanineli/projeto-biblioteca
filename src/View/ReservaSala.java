package View;

import BD.controllers.ReservaJpaController;
import BD.controllers.SalaJpaController;
import BD.entities.Aluno;
import BD.entities.Reserva;
import BD.entities.Sala;
import Utility.CellRenderer;
import java.util.List;
import javax.persistence.Persistence;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ReservaSala extends javax.swing.JFrame {
    
    private TelaPrincipal tp;
    
    public ReservaSala(TelaPrincipal tp) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Images/Facamp_FavIcon.png")).getImage());
        this.tp = tp;
        //listaSalas.setCellRenderer(new CellRenderer(18));
        setExtendedState(MAXIMIZED_BOTH);
        popularLista();
        labelTexto.setText("Para " + tp.getTexto() + ", as seguintes salas indicadas abaixo estão disponíveis, selecione para continuar.");
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelTexto = new javax.swing.JLabel();
        btnVoltar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaSalas = new javax.swing.JList<>();
        btnAgendar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACAMP");

        jPanel3.setBackground(new java.awt.Color(40, 91, 139));

        jLabel3.setFont(new java.awt.Font("Roboto Condensed", 1, 70)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FACAMP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(102, 102, 102));

        labelTexto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTexto.setForeground(new java.awt.Color(102, 102, 102));
        labelTexto.setText("Default Text");
        labelTexto.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(labelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        btnVoltar1.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        btnVoltar1.setForeground(new java.awt.Color(102, 102, 102));
        btnVoltar1.setText("Voltar");
        btnVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar1ActionPerformed(evt);
            }
        });

        listaSalas.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        listaSalas.setForeground(new java.awt.Color(102, 102, 102));
        listaSalas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaSalas.setFixedCellHeight(100);
        listaSalas.setFixedCellWidth(315);
        listaSalas.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        listaSalas.setVisibleRowCount(4);
        jScrollPane1.setViewportView(listaSalas);

        btnAgendar.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        btnAgendar.setForeground(new java.awt.Color(102, 102, 102));
        btnAgendar.setText("Agendar");
        btnAgendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgendar, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVoltar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAgendar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void popularLista() {
        DefaultListModel<Sala> modelo = new DefaultListModel();
        listaSalas.setModel(modelo);
        SalaJpaController sc = new SalaJpaController(Persistence.createEntityManagerFactory("ProjetoBibliotecaPU"));
        //List<Sala> salas = sc.findSalaEntities();
        List<Sala> salas = tp.checarSalas();
        for (Sala sala : salas) {
            modelo.addElement(sala);
        }
    }
    
    public String codGerado;
    
    //Gerar codigo de reserva
    public String gerarCod (){
        if (Integer.parseInt(listaSalas.getSelectedValue().toString())<10){
            return codGerado = "0" + listaSalas.getSelectedValue().toString() + tp.getDate().format(tp.formatCod) + (tp.getHorario()).substring(0,2);
        }else{
            return codGerado = listaSalas.getSelectedValue().toString() + tp.getDate().format(tp.formatCod) + (tp.getHorario()).substring(0,2);
        }
    }

    public JList<Sala> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(JList<Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }
    
    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        this.setVisible(false);
        tp.setVisible(true);
    }//GEN-LAST:event_btnVoltar1ActionPerformed

    private void btnAgendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarActionPerformed
         try {
            for (int i = 0; i < tp.getTabelaNomes().getRowCount(); i++) {
                String ra = tp.getTabelaNomes().getValueAt(i, 1).toString();
                Aluno a = new Aluno();
                a.setRa(ra);
                ReservaJpaController rc = new ReservaJpaController(Persistence.createEntityManagerFactory("ProjetoBibliotecaPU"));
                Reserva reserva = new Reserva(gerarCod(),tp.getData(), tp.getHorario(), a, listaSalas.getSelectedValue());
                rc.create(reserva);
            }
            
            SucessoReserva sr = new SucessoReserva(tp);
            sr.setVisible(true);
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgendarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendar;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTexto;
    private javax.swing.JList<Sala> listaSalas;
    // End of variables declaration//GEN-END:variables
}