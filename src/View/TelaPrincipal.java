package View;

import BD.controllers.AlunoJpaController;
import BD.controllers.ReservaJpaController;
import BD.entities.Aluno;
import BD.entities.Reserva;
import BD.entities.Sala;
import Utility.CellRenderer;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/Images/Facamp_FavIcon.png")).getImage());
        configurarTabela();
        listaHorarios.setCellRenderer(new CellRenderer(14));

    }

    private String data;
    private String horario;
    private String lousa;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");
    public DateTimeFormatter formatCod = DateTimeFormatter.ofPattern("MMdd");
    private LocalDateTime Hoje = LocalDateTime.now();
    private LocalDateTime Amanha = LocalDateTime.now().plusDays(1);

    private String x = String.valueOf(Hoje);
    private String y = String.valueOf(Amanha);

    public void configurarTabela() {
        tabelaNomes.getTableHeader().setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
        tabelaNomes.setRowHeight(30);
        tabelaNomes.getColumnModel().getColumn(0).setPreferredWidth(210);
        tabelaNomes.getColumnModel().getColumn(1).setPreferredWidth(148);
        tabelaNomes.getColumnModel().getColumn(2).setPreferredWidth(210);
    }

    //Retorna um LocalDateTime
    public LocalDateTime getDate() {
        LocalDateTime date;
        if (botaoDataAmanha.isSelected()) {
            date = Amanha;
        } else {
            date = Hoje;
        }
        return date;
    }

    //Configura texto para tela de reserva (ReservaSala)
    public String getTexto() {
        if (botaoDataAmanha.isSelected()) {
            setData(dtf.format(Amanha));
        } else {
            setData(dtf.format(Hoje));
        }

        if (botaoLousaSim.isSelected()) {
            setLousa("com lousa");
        } else {
            setLousa("sem lousa");
        }
        horario = listaHorarios.getSelectedValue();
        return data + " às " + horario + " " + lousa;
    }

    //Getters e Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLousa() {
        return lousa;
    }

    public void setLousa(String lousa) {
        this.lousa = lousa;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public JTable getTabelaNomes() {
        return tabelaNomes;
    }

    public void setTabelaNomes(JTable tabelaNomes) {
        this.tabelaNomes = tabelaNomes;
    }

    public String gerarFakeCod(int x) {
        if (x < 10) {
            return "0" + x + getDate().format(formatCod).toString() + listaHorarios.getSelectedValue().substring(0,2);
        } else {
            return x + getDate().format(formatCod).toString() + listaHorarios.getSelectedValue().substring(0,2);
        }

    }

    public List<Sala> checarSalas() {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoBibliotecaPU").createEntityManager();
        List<Sala> salasSLousa = em.createNamedQuery("Sala.findByLousa").setParameter("lousa", "Não").getResultList();
        List<Sala> salaLousa = em.createNamedQuery("Sala.findByLousa").setParameter("lousa", "Sim").getResultList();
        List<Sala> salas = new ArrayList<Sala>();
        List<Sala> salasDisponiveis = new ArrayList<Sala>();
        List<Reserva> reservas = em.createNamedQuery("Reserva.findAll").getResultList();
        boolean existe = false;
        if(botaoLousaSim.isSelected()){
            salas = salaLousa;
        }else if (botaoLousaNao.isSelected()){
            salas = salasSLousa;
        }
        
        boolean aux;
        for (Sala z : salas) {
            String fakeCod = gerarFakeCod(z.getNumSala());
            for (Reserva x : reservas) {
                if ((x.getCodReserva().equals(fakeCod))) {
                    existe = true;
                }
            }
            if (!existe) {
                salasDisponiveis.add(z);  
            }
            existe = false;

        }
        return salasDisponiveis;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botaoDataHoje = new javax.swing.JToggleButton();
        botaoDataAmanha = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaHorarios = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaNomes = new javax.swing.JTable();
        rmvAluno = new javax.swing.JButton();
        addAluno = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        nomeAluno = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        raAluno = new javax.swing.JTextField();
        emailAluno = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        botaoLousaSim = new javax.swing.JToggleButton();
        botaoLousaNao = new javax.swing.JToggleButton();
        btnVoltar1 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACAMP");

        jPanel1.setBackground(new java.awt.Color(40, 91, 139));

        jLabel3.setFont(new java.awt.Font("Roboto Condensed", 1, 70)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FACAMP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1222, 90));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Para utilizar as salas da biblioteca, escolha a partir dos horários disponíveis uma sala que não tenha sido reservada ainda.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        btnBuscar.setBackground(new java.awt.Color(189, 189, 189));
        btnBuscar.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(102, 102, 102));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(1222, 90));
        jPanel7.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Data");

        botaoDataHoje.setBackground(new java.awt.Color(189, 189, 189));
        botaoDataHoje.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        botaoDataHoje.setForeground(new java.awt.Color(102, 102, 102));
        botaoDataHoje.setText("Hoje");
        botaoDataHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDataHojeActionPerformed(evt);
            }
        });

        botaoDataAmanha.setBackground(new java.awt.Color(189, 189, 189));
        botaoDataAmanha.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        botaoDataAmanha.setForeground(new java.awt.Color(102, 102, 102));
        botaoDataAmanha.setText("Amanhã");
        botaoDataAmanha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDataAmanhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(botaoDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(botaoDataAmanha, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoDataAmanha, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(botaoDataHoje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(1222, 90));

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Horário");

        listaHorarios.setBackground(new java.awt.Color(220, 220, 220));
        listaHorarios.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        listaHorarios.setForeground(new java.awt.Color(102, 102, 102));
        listaHorarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00\t", "17:00\t", "18:00\t", "19:00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaHorarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaHorarios.setFixedCellHeight(50);
        listaHorarios.setFixedCellWidth(215);
        listaHorarios.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        listaHorarios.setSelectionBackground(new java.awt.Color(40, 91, 139));
        jScrollPane1.setViewportView(listaHorarios);

        jLabel5.setFont(new java.awt.Font("Roboto Condensed", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 42, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tabelaNomes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelaNomes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "RA", "Email"
            }
        ));
        tabelaNomes.setRowHeight(30);
        jScrollPane2.setViewportView(tabelaNomes);

        rmvAluno.setForeground(new java.awt.Color(102, 102, 102));
        rmvAluno.setText("Remover Aluno");
        rmvAluno.setEnabled(false);
        rmvAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmvAlunoActionPerformed(evt);
            }
        });

        addAluno.setForeground(new java.awt.Color(102, 102, 102));
        addAluno.setText("Adicionar Aluno");
        addAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAlunoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel7.setText("Nome:");

        nomeAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nomeAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeAlunoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel8.setText("RA:");

        jLabel9.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel9.setText("E-mail:");

        raAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        raAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raAlunoActionPerformed(evt);
            }
        });

        emailAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailAlunoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(raAluno)
                                    .addComponent(nomeAluno)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailAluno)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(addAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rmvAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(nomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(raAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(addAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rmvAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(1222, 90));

        jLabel6.setFont(new java.awt.Font("Roboto Condensed", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Precisa de Lousa?");

        botaoLousaSim.setBackground(new java.awt.Color(189, 189, 189));
        botaoLousaSim.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        botaoLousaSim.setForeground(new java.awt.Color(102, 102, 102));
        botaoLousaSim.setText("Sim");
        botaoLousaSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLousaSimActionPerformed(evt);
            }
        });

        botaoLousaNao.setBackground(new java.awt.Color(189, 189, 189));
        botaoLousaNao.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        botaoLousaNao.setForeground(new java.awt.Color(102, 102, 102));
        botaoLousaNao.setText("Não");
        botaoLousaNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLousaNaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(botaoLousaSim, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(botaoLousaNao, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoLousaNao, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(botaoLousaSim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );

        btnVoltar1.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        btnVoltar1.setForeground(new java.awt.Color(102, 102, 102));
        btnVoltar1.setText("Voltar");
        btnVoltar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltar1MouseClicked(evt);
            }
        });
        btnVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnVoltar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            if ((!botaoDataAmanha.isSelected() && !botaoDataHoje.isSelected())
                    || (!botaoLousaNao.isSelected() && !botaoLousaSim.isSelected())
                    || tabelaNomes.getModel().getRowCount() == 0
                    || listaHorarios.getSelectedIndex() == -1) {

                throw new Exception("Preencha todos os dados");

            } else {
                AlunoJpaController ac = new AlunoJpaController(Persistence.createEntityManagerFactory("ProjetoBibliotecaPU"));
                EntityManager em = Persistence.createEntityManagerFactory("ProjetoBibliotecaPU").createEntityManager();
                boolean existe = false;
                //Aluno teste = (Aluno) em.createNamedQuery("Aluno.findByRa").getResultList();
                List<Aluno> teste = em.createNamedQuery("Aluno.findAll").getResultList();

                for (int i = 0; i < tabelaNomes.getRowCount(); i++) {

                    Aluno aluno = new Aluno(tabelaNomes.getValueAt(i, 1).toString(), tabelaNomes.getValueAt(i, 0).toString(), tabelaNomes.getValueAt(i, 2).toString());

                    for (Aluno x : teste) {
                        if ((x.getRa().equals(aluno.getRa()))) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        ac.create(aluno);
                    }

                    existe = false;
                }
                ReservaSala rs = new ReservaSala(this);
                this.setVisible(false);
                rs.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void botaoDataHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDataHojeActionPerformed
        botaoDataAmanha.setSelected(false);
    }//GEN-LAST:event_botaoDataHojeActionPerformed

    private void botaoDataAmanhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDataAmanhaActionPerformed
        botaoDataHoje.setSelected(false);

    }//GEN-LAST:event_botaoDataAmanhaActionPerformed

    private void botaoLousaSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLousaSimActionPerformed
        botaoLousaNao.setSelected(false);
    }//GEN-LAST:event_botaoLousaSimActionPerformed

    private void botaoLousaNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLousaNaoActionPerformed
        botaoLousaSim.setSelected(false);
    }//GEN-LAST:event_botaoLousaNaoActionPerformed

    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja mesmo sair?");
        if (resposta == JOptionPane.YES_OPTION) {
            TelaLogin tl = new TelaLogin();
            tl.setVisible(true);
            TelaPrincipal.super.dispose();
        }
    }//GEN-LAST:event_btnVoltar1ActionPerformed

    private void addAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAlunoActionPerformed
        try {

            if (nomeAluno.getText().equals("") || raAluno.getText().equals("") || emailAluno.getText().equals("")) {
                throw new Exception("Preencha os campos para adicionar o aluno");
            }

            DefaultTableModel modelo = ((DefaultTableModel) tabelaNomes.getModel());
            modelo.addRow(new Object[]{nomeAluno.getText(), raAluno.getText(), emailAluno.getText()});

            emailAluno.setText("");
            raAluno.setText("");
            nomeAluno.setText("");

            if (modelo.getRowCount() >= 6) {

                addAluno.setEnabled(false);
            }
            if (modelo.getRowCount() >= 1) {

                rmvAluno.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addAlunoActionPerformed

    private void rmvAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmvAlunoActionPerformed
        DefaultTableModel modelo = ((DefaultTableModel) tabelaNomes.getModel());

        modelo.setRowCount(modelo.getRowCount() - 1);

        if (modelo.getRowCount() < 6) {

            addAluno.setEnabled(true);
        }

        if (modelo.getRowCount() < 2) {

            rmvAluno.setEnabled(false);
        }
    }//GEN-LAST:event_rmvAlunoActionPerformed

    private void emailAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAlunoActionPerformed


    }//GEN-LAST:event_emailAlunoActionPerformed

    private void nomeAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeAlunoActionPerformed


    }//GEN-LAST:event_nomeAlunoActionPerformed

    private void raAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_raAlunoActionPerformed

    }//GEN-LAST:event_raAlunoActionPerformed

    private void btnVoltar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltar1MouseClicked
        if (evt.getClickCount() == 1){
            
        }
    }//GEN-LAST:event_btnVoltar1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAluno;
    private javax.swing.JToggleButton botaoDataAmanha;
    private javax.swing.JToggleButton botaoDataHoje;
    private javax.swing.JToggleButton botaoLousaNao;
    private javax.swing.JToggleButton botaoLousaSim;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JTextField emailAluno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaHorarios;
    private javax.swing.JTextField nomeAluno;
    private javax.swing.JTextField raAluno;
    private javax.swing.JButton rmvAluno;
    private javax.swing.JTable tabelaNomes;
    // End of variables declaration//GEN-END:variables
}