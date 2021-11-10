package View;

import BD.Entities.Aluno;
import BD.Entities.Reserva;
import BD.Entities.Sala;
import BD.controllers.AlunoJpaController;
import Utility.CellRenderer;
import java.awt.Font;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal(String user) {
        initComponents();
        this.user = user;
        setExtendedState(MAXIMIZED_BOTH);
        configurarTabela();
        listaHorarios.setCellRenderer(new CellRenderer(14));
        setIconImage(new ImageIcon(getClass().getResource("/Images/Facamp_FavIcon.png")).getImage());
        setLocationRelativeTo(null);

    }

    private String user;
    private String data;
    private String horario;
    private String lousa;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
    public DateTimeFormatter formatCod = DateTimeFormatter.ofPattern("ddMMyy");
    private LocalDateTime Hoje = LocalDateTime.now();
    private LocalDateTime Amanha = LocalDateTime.now().plusDays(1);

    private static final String EMAIL_PATTERN
            = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

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

    public String gerarFakeCod(int x) {
        if (x < 10) {
            return "0" + x + getDate().format(formatCod).toString() + listaHorarios.getSelectedValue().substring(0, 2);
        } else {
            return x + getDate().format(formatCod).toString() + listaHorarios.getSelectedValue().substring(0, 2);
        }

    }

    public static boolean isValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public List<Sala> checarSalas() {
        EntityManager em = Persistence.createEntityManagerFactory("BibliotecaTestePU").createEntityManager();
        List<Sala> salasSLousa = em.createNamedQuery("Sala.findByLousa").setParameter("lousa", "Não").getResultList();
        List<Sala> salaLousa = em.createNamedQuery("Sala.findByLousa").setParameter("lousa", "Sim").getResultList();
        List<Sala> salas = new ArrayList<>();
        List<Sala> salasDisponiveis = new ArrayList<>();
        List<Reserva> reservas = em.createNamedQuery("Reserva.findAll").getResultList();
        boolean existe = false;
        int qntdAlunos = tabelaNomes.getRowCount();

        if (botaoLousaSim.isSelected()) {
            salas = salaLousa;
        } else if (botaoLousaNao.isSelected()) {
            salas = salasSLousa;
        }

        boolean aux;
        for (Sala z : salas) {
            String fakeCod = gerarFakeCod(z.getNumSala());
            for (Reserva x : reservas) {
                if ((x.getReservaPK().getCodReserva().equals(fakeCod))) {
                    existe = true;
                }
            }
            if (!existe && Integer.parseInt(z.getLotacao()) >= tabelaNomes.getRowCount()) {
                salasDisponiveis.add(z);
            }
            existe = false;

        }
        return salasDisponiveis;
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
        emailAluno = new javax.swing.JTextField();
        raAluno = new javax.swing.JFormattedTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        botaoLousaSim = new javax.swing.JToggleButton();
        botaoLousaNao = new javax.swing.JToggleButton();
        jLabel10 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FACAMP");
        setUndecorated(true);

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
        jLabel1.setText("Para utilizar as salas da biblioteca, preencha os campos e busque uma sala disponível");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
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
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(botaoDataHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(botaoDataAmanha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoDataAmanha, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(botaoDataHoje, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(1222, 90));

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
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
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 52, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tabelaNomes.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        tabelaNomes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "RA", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaNomes.setRowHeight(30);
        tabelaNomes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelaNomes);
        if (tabelaNomes.getColumnModel().getColumnCount() > 0) {
            tabelaNomes.getColumnModel().getColumn(0).setResizable(false);
            tabelaNomes.getColumnModel().getColumn(1).setResizable(false);
            tabelaNomes.getColumnModel().getColumn(2).setResizable(false);
        }

        rmvAluno.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        rmvAluno.setForeground(new java.awt.Color(102, 102, 102));
        rmvAluno.setText("Remover Aluno");
        rmvAluno.setEnabled(false);
        rmvAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmvAlunoActionPerformed(evt);
            }
        });

        addAluno.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        addAluno.setForeground(new java.awt.Color(102, 102, 102));
        addAluno.setText("Adicionar Aluno");
        addAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAlunoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel7.setText("RA:");

        nomeAluno.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        nomeAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nomeAlunoFocusLost(evt);
            }
        });
        nomeAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeAlunoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel8.setText("Nome:");

        jLabel9.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel9.setText("E-mail:");

        emailAluno.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        emailAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailAlunoFocusLost(evt);
            }
        });
        emailAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailAlunoActionPerformed(evt);
            }
        });

        try {
            raAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        raAluno.setToolTipText("");
        raAluno.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        raAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                raAlunoFocusLost(evt);
            }
        });
        raAluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                raAlunoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeAluno)
                                    .addComponent(raAluno)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailAluno)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(addAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rmvAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(raAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(nomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(addAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rmvAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(1222, 90));

        jLabel6.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Precisa de Lousa? ");

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

        jLabel10.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Obs: Para mais de 4 alunos, apenas salas com lousa");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoLousaSim, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoLousaNao, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoLousaNao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoLousaSim, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );

        btnVoltar.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(102, 102, 102));
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {

            //Checa se tem algum campo vazio
            if ((!botaoDataAmanha.isSelected() && !botaoDataHoje.isSelected())
                    || (!botaoLousaNao.isSelected() && !botaoLousaSim.isSelected())
                    || tabelaNomes.getModel().getRowCount() == 0
                    || listaHorarios.getSelectedIndex() == -1) {

                throw new Exception("Preencha todos os dados");

            } else {
                //Cria variaveis necessarias
                AlunoJpaController ac = new AlunoJpaController(Persistence.createEntityManagerFactory("BibliotecaTestePU"));
                EntityManager em = Persistence.createEntityManagerFactory("BibliotecaTestePU").createEntityManager();
                boolean existe = false;
                List<Aluno> teste = em.createNamedQuery("Aluno.findAll").getResultList();

                //Adiona aluno no banco caso não exista
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

                //Checa se alunos já tem reserva no dia selecionado
                boolean disponivel = true;
                String alunosRepetidos = "";
                List<Reserva> alunosReservados = em.createNamedQuery("Reserva.findByData").setParameter("data", dtf.format(getDate()).toString()).getResultList();
                for (Reserva r : alunosReservados) {
                    for (int i = 0; i < tabelaNomes.getRowCount(); i++) {
                        for (Aluno a : r.getAlunoList()) {
                            if (a.getRa().equals(tabelaNomes.getValueAt(i, 1))) {
                                disponivel = false;
                                alunosRepetidos = a.getNome() + ", " + alunosRepetidos;
                            }
                        }
                    }
                }
                if (!disponivel) {
                    throw new Exception("O(s) aluno(s) " + alunosRepetidos.substring(0, alunosRepetidos.length() - 2) + " já têm reserva para esse dia!");
                }

                //Checa se existem salas disponiveis nas condições selecionadas
                if (checarSalas().size() == 0) {
                    throw new Exception("Não há salas disponíveis nessas condições");
                }

                //Checa se horario é válido
                if (botaoDataHoje.isSelected()) {
                    if (Integer.parseInt(listaHorarios.getSelectedValue().substring(0, 2)) < Hoje.getHour()) {
                        throw new Exception("Horário Inválido");
                    }
                }

                ReservaSala rs = new ReservaSala(this, user);
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

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja mesmo sair?", "Selecione uma opção", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            if (user.equals("Alunos")) {
                TelaLogin tl = new TelaLogin();
                tl.setVisible(true);
                this.dispose();
            } else {
                TelaAdmin ta = new TelaAdmin();
                ta.setVisible(true);
                this.dispose();
            }
        }

    }//GEN-LAST:event_btnVoltarActionPerformed

    private void addAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAlunoActionPerformed
        try {

            if (nomeAluno.getText().equals("") || raAluno.getText().equals("") || emailAluno.getText().equals("")) {
                throw new Exception("Preencha os campos para adicionar o aluno");
            }

            if (!isValid(emailAluno.getText())) {
                throw new Exception("O e-mail inserido não é válido");
            }

            for (int i = 0; i < tabelaNomes.getRowCount(); i++) {
                if (raAluno.getText().toString().equals(tabelaNomes.getValueAt(i, 1))) {
                    throw new Exception("Aluno já adicionado");
                }
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
            if (modelo.getRowCount() > 4) {
                botaoLousaNao.setSelected(false);
                botaoLousaSim.setSelected(true);
                botaoLousaNao.setEnabled(false);
                botaoLousaSim.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addAlunoActionPerformed

    private void rmvAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmvAlunoActionPerformed
        try {
            DefaultTableModel modelo = ((DefaultTableModel) tabelaNomes.getModel());
            if (tabelaNomes.getSelectedRow() == -1) {
                throw new Exception("Selecione um aluno para remover");
            }
            modelo.removeRow(tabelaNomes.getSelectedRow());

            if (modelo.getRowCount() < 6) {

                addAluno.setEnabled(true);
            }

            if (modelo.getRowCount() < 2) {

                rmvAluno.setEnabled(false);
            }
            if (modelo.getRowCount() <= 4) {
                botaoLousaNao.setEnabled(true);
                botaoLousaSim.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_rmvAlunoActionPerformed

    private void emailAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAlunoActionPerformed


    }//GEN-LAST:event_emailAlunoActionPerformed

    private void nomeAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeAlunoActionPerformed


    }//GEN-LAST:event_nomeAlunoActionPerformed

    private void raAlunoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_raAlunoKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            EntityManager em = Persistence.createEntityManagerFactory("BibliotecaTestePU").createEntityManager();
            try {
                Aluno a = (Aluno) em.createNamedQuery("Aluno.findByRa").setParameter("ra", raAluno.getText().toString()).getSingleResult();
                nomeAluno.setText(a.getNome().toString());
                emailAluno.setText(a.getEmail().toString());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Impossível localizar aluno, primeiro é necessário cadastrar no banco", "Aluno não cadastrado", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_raAlunoKeyPressed

    private void nomeAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomeAlunoFocusLost
        try {
            String nome = nomeAluno.getText();

            String words[] = nome.split("\s");
            String capitalizeStr = "";

            for (String word : words) {
                // Capitalize first letter
                String firstLetter = word.substring(0, 1);
                // Get remaining letter
                String remainingLetters = word.substring(1);
                capitalizeStr += firstLetter.toUpperCase() + remainingLetters + " ";
            }
            nomeAluno.setText(capitalizeStr);
        } catch (Exception e) {

        }

    }//GEN-LAST:event_nomeAlunoFocusLost

    private void emailAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAlunoFocusLost

    }//GEN-LAST:event_emailAlunoFocusLost

    private void raAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_raAlunoFocusLost

    }//GEN-LAST:event_raAlunoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAluno;
    private javax.swing.JToggleButton botaoDataAmanha;
    private javax.swing.JToggleButton botaoDataHoje;
    private javax.swing.JToggleButton botaoLousaNao;
    private javax.swing.JToggleButton botaoLousaSim;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JTextField emailAluno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JFormattedTextField raAluno;
    private javax.swing.JButton rmvAluno;
    private javax.swing.JTable tabelaNomes;
    // End of variables declaration//GEN-END:variables
}
