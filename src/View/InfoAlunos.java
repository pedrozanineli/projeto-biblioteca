package View;

import BD.entities.Aluno;
import BD.entities.Reserva;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class InfoAlunos extends javax.swing.JFrame {

    TelaAdmin ta;
    private String codigo;

    public InfoAlunos(TelaAdmin ta, String codigo) {
        setIconImage(new ImageIcon(getClass().getResource("/Images/Facamp_FavIcon.png")).getImage());
        setLocationRelativeTo(null);
        this.ta = ta;
        this.codigo = codigo;
        initComponents();
        configurarTabela();
        popularTabelaAlunos(codigo);
    }

    public void configurarTabela() {
        tabela.getTableHeader().setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
        tabela.setRowHeight(30);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(210);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(148);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(210);
    }

    public void popularTabelaAlunos(String cod) {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoBibliotecaPU").createEntityManager();
        List<Aluno> reservas = em.createNamedQuery("Reserva.findByRaEmReserva").setParameter("codReserva", cod).getResultList();
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        
        System.out.println(reservas);
        for (Aluno x : reservas) {
            //Aluno x = (Aluno) em.createNamedQuery("Aluno.findByRa").setParameter("ra", y.getAlunora().toString()).getSingleResult();
            modelo.addRow(new Object[]{x.getNome(), x.getRa(), x.getEmail()});
        }
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Info Alunos");
        setResizable(false);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Ra", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(1).setResizable(false);
            tabela.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
         * @param args the command line arguments
         */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
