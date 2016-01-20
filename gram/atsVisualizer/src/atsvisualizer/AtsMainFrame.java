/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atsvisualizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author filipeoliveira
 */
public class AtsMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form AtsMainFrame
     */
    
    ArrayList <atsMethod> methods;
    
    public AtsMainFrame() {
        methods = new ArrayList <atsMethod>();
        File fileWithMetrics = new File("metrics_to_gui.txt");
        try {
            read (fileWithMetrics);
        } catch (IOException ex) {
            Logger.getLogger(AtsMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        fillList();
        int lastIndex = this.methods.size() -1 ;
        this.lista_metricas.setSelectedIndex(lastIndex);
       // updatePainel(lastIndex);
    }
    
    public void read(File file) throws IOException{
    Scanner scanner = new Scanner(file);
    while(scanner.hasNext()){
        String[] tokens = scanner.nextLine().split(";");
        if ( tokens.length >= 10 ){
            String method_description = tokens[0];
     double ats_CYCLO_PER_LOC = Double.parseDouble(tokens[1]);
     double ats_LOC_PER_OPERATION = Double.parseDouble(tokens[2]);
     double ats_NOM_PER_CLASS = Double.parseDouble(tokens[3]);
    double ats_NOC_PER_PACKAGE = Double.parseDouble(tokens[4]);
    double ats_CALLS_PER_OPERATION = Double.parseDouble(tokens[5]);
    double ats_FANOUT_PER_CALL = Double.parseDouble(tokens[6]);
    double ats_ANDC = Double.parseDouble(tokens[7]);
    double ats_AHH = Double.parseDouble(tokens[8]);
    double ats_CYCLO = Double.parseDouble(tokens[9]);
    double ats_LOC = Double.parseDouble(tokens[10]);
    double ats_NOM = Double.parseDouble(tokens[11]);
    double ats_NOC = Double.parseDouble(tokens[12]);
    double ats_NOP = Double.parseDouble(tokens[13]);
    double ats_FANOUT = Double.parseDouble(tokens[14]);
    double ats_CALLS = Double.parseDouble(tokens[15]);
    double ats_VOLUME = Double.parseDouble(tokens[16]);
    double ats_DIFFICULTY = Double.parseDouble(tokens[17]);
    double ats_EFFORT = Double.parseDouble(tokens[18]);
    double ats_BUGS = Double.parseDouble(tokens[19]);
        double ats_MAINTAIN = Double.parseDouble(tokens[20]);
    double  ats_NOCOM = Double.parseDouble(tokens[21]);
    double ats_IPL = Double.parseDouble(tokens[22]);
    double   ats_UNARG = Double.parseDouble(tokens[23]);
    double  ats_UNDEC = Double.parseDouble(tokens[24]);
    double  ats_NEGNEG = Double.parseDouble(tokens[25]);
  double   ats_NEGIF = Double.parseDouble(tokens[26]);
    double   ats_NARGS = Double.parseDouble(tokens[27]);
    String standardValues = tokens[28];
            atsMethod newM = new atsMethod ( method_description,  ats_CYCLO_PER_LOC, ats_LOC_PER_OPERATION, 
    ats_NOM_PER_CLASS, ats_NOC_PER_PACKAGE, ats_CALLS_PER_OPERATION,
    ats_FANOUT_PER_CALL, ats_ANDC, ats_AHH , ats_CYCLO, ats_LOC, ats_NOM,
                    ats_NOC, ats_NOP, ats_FANOUT, ats_CALLS, 
                    ats_VOLUME, ats_DIFFICULTY, ats_EFFORT, ats_BUGS, ats_MAINTAIN,
                    ats_NOCOM , ats_IPL ,   ats_UNARG , ats_UNDEC ,    ats_NEGNEG ,ats_NEGIF ,
                    ats_NARGS,
                    standardValues );
            this.methods.add(newM);
            System.out.println("added new ats method: " + method_description );
       }
}
    }
    
    public void fillList (){
        DefaultListModel dlm = new DefaultListModel();
        for (atsMethod temp : methods) {
			dlm.addElement(temp.methodDescription);
		}
        this.lista_metricas.setModel(dlm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_metricas = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        nop_label = new javax.swing.JLabel();
        noc_label = new javax.swing.JLabel();
        nom_label = new javax.swing.JLabel();
        cyclo_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        calls_label = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        andc = new javax.swing.JTextField();
        fanout_label = new javax.swing.JLabel();
        ahh = new javax.swing.JTextField();
        noc_nop = new javax.swing.JTextField();
        nom_per_noc = new javax.swing.JTextField();
        loc_per_nom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cyclo_per_loc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        calls_per_nom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fanout_per_calls = new javax.swing.JTextField();
        estrela_1 = new javax.swing.JLabel();
        estrela_2 = new javax.swing.JLabel();
        estrela_3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        maintain_index = new javax.swing.JTextField();
        loc = new javax.swing.JTextField();
        score = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        unarg = new javax.swing.JTextField();
        undec = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nocomm = new javax.swing.JTextField();
        negneg = new javax.swing.JTextField();
        negif = new javax.swing.JTextField();
        ipl = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        volume = new javax.swing.JTextField();
        difficulty = new javax.swing.JTextField();
        effort = new javax.swing.JTextField();
        bugs = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        nargs = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setMinimumSize(new java.awt.Dimension(200, 27));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(204, 140));

        lista_metricas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista_metricas.setMaximumSize(new java.awt.Dimension(200, 85));
        lista_metricas.setMinimumSize(new java.awt.Dimension(200, 85));
        lista_metricas.setPreferredSize(new java.awt.Dimension(200, 85));
        lista_metricas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_metricasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista_metricas);

        jSplitPane2.setLeftComponent(jScrollPane1);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("FANOUT");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 440, -1, -1));

        nop_label.setText("jLabel11");
        jPanel1.add(nop_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, -1, -1));

        noc_label.setText("jLabel11");
        jPanel1.add(noc_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, -1, -1));

        nom_label.setText("jLabel11");
        jPanel1.add(nom_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, -1, -1));

        cyclo_label.setText("jLabel11");
        jPanel1.add(cyclo_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, -1, -1));

        jLabel3.setText("NOP (N. of Packages)");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, -1, -1));

        calls_label.setText("jLabel11");
        jPanel1.add(calls_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, -1, -1));

        jLabel4.setText("NOC (N. of Classes)");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, -1, -1));

        andc.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(andc, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 100, -1));

        fanout_label.setText("jLabel11");
        jPanel1.add(fanout_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 440, -1, -1));

        ahh.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ahh, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 100, -1));

        noc_nop.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(noc_nop, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 80, -1));

        nom_per_noc.setForeground(new java.awt.Color(255, 255, 255));
        nom_per_noc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_per_nocActionPerformed(evt);
            }
        });
        jPanel1.add(nom_per_noc, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 100, -1));

        loc_per_nom.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(loc_per_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 120, -1));

        jLabel5.setText("NOM ( Nº of Operations)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, -1, -1));

        jLabel6.setText("LOC ( Lines of Code)");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        cyclo_per_loc.setForeground(new java.awt.Color(255, 255, 255));
        cyclo_per_loc.setCaretColor(new java.awt.Color(255, 255, 255));
        cyclo_per_loc.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        cyclo_per_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cyclo_per_locActionPerformed(evt);
            }
        });
        jPanel1.add(cyclo_per_loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 100, -1));

        jLabel8.setText("NOM");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel1.setText("ANDC");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, -1, -1));

        calls_per_nom.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(calls_per_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 100, -1));

        jLabel2.setText("AHH");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, -1, -1));

        jLabel9.setText("CALLS (Nº Op. Calls)");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, -1, -1));

        jLabel7.setText("CYCLO (Cyclomatic Complexity)");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, -1, -1));

        fanout_per_calls.setForeground(new java.awt.Color(255, 255, 255));
        fanout_per_calls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fanout_per_callsActionPerformed(evt);
            }
        });
        jPanel1.add(fanout_per_calls, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 400, 100, -1));

        estrela_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atsvisualizer/estrela_ats.png"))); // NOI18N
        jPanel1.add(estrela_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 6, -1, -1));

        estrela_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atsvisualizer/estrela_ats.png"))); // NOI18N
        jPanel1.add(estrela_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 6, -1, -1));

        estrela_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atsvisualizer/estrela_ats.png"))); // NOI18N
        jPanel1.add(estrela_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 6, -1, -1));

        jLabel12.setText("NEGNEG - Nº Negation of Negation NEGNEG");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, -1, -1));

        maintain_index.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(maintain_index, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 100, -1));
        jPanel1.add(loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 101, -1));

        score.setText("jLabel11");
        jPanel1.add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 85, -1, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel13.setText("Refactorable Smells");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel15.setText("Overview Pyramid");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 119, -1, -1));

        jLabel16.setText("AHH — Average Hierarchy Height");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        jLabel17.setText("FANOUT — Number of Called Classes");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, -1, -1));

        jLabel11.setText("ANDC — Average Number of Derived Classes");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel18.setText("Aditional Metrics");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jLabel19.setText("NUMBER OF COMMENTS");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, -1));

        jLabel20.setText("UNARG - Nº Unused Arguments UNARG");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));

        jLabel21.setText("UNDEC - Nº Unused Declarations UNDEC");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, -1));

        jLabel22.setText("NEGIF - Nº Negation of If Condition NEGIF");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, -1, 40));

        unarg.setForeground(new java.awt.Color(255, 255, 255));
        unarg.setText("jTextField1");
        jPanel1.add(unarg, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 110, -1));

        undec.setText("jTextField1");
        jPanel1.add(undec, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 550, 110, -1));

        jLabel23.setText("MAINTAINABILITY INDEX");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, -1, -1));
        jPanel1.add(nocomm, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, 100, -1));

        negneg.setText("jTextField2");
        jPanel1.add(negneg, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 520, 110, -1));

        negif.setText("jTextField2");
        jPanel1.add(negif, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 550, 110, -1));
        jPanel1.add(ipl, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 120, 100, -1));

        jLabel24.setText("INSTRUCTION PATH LENGTH");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, -1, -1));

        jLabel25.setText("HALSTEAD VOLUME");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, -1, -1));

        jLabel26.setText("HALSTEAD DIFFICULTY");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 210, -1, -1));

        jLabel27.setText("HALSTEAD EFFORT");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, -1, -1));

        jLabel28.setText("NUMBER OF ARGUMENTS");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, -1, -1));
        jPanel1.add(volume, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 160, 100, -1));
        jPanel1.add(difficulty, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, 100, -1));
        jPanel1.add(effort, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, 100, -1));
        jPanel1.add(bugs, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, 100, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atsvisualizer/pyramind.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel29.setText("HALSTEAD BUGS");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, -1, -1));

        nargs.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(nargs, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 300, 100, -1));

        jSplitPane2.setRightComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1246, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fanout_per_callsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fanout_per_callsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fanout_per_callsActionPerformed

    private void lista_metricasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_metricasValueChanged
       int indice = this.lista_metricas.getSelectedIndex();
        System.out.println("selected " + indice);
        updatePainel(indice);
    }//GEN-LAST:event_lista_metricasValueChanged

    private void cyclo_per_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cyclo_per_locActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cyclo_per_locActionPerformed

    private void nom_per_nocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_per_nocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom_per_nocActionPerformed

    
     private void updatePainel(int indice) {
       atsMethod seleccionado = this.methods.get(indice);
       this.score.setText( "Score: " + seleccionado.getScore() + " out of " + seleccionado.getMaxScore() );
       int numeroEstrelas = seleccionado.getNumberStars();
       this.estrela_1.setVisible(false);
       this.estrela_2.setVisible(false);
       this.estrela_3.setVisible(false);
         if (numeroEstrelas >= 1 ) {
             this.estrela_1.setVisible(true);
         }
         if (numeroEstrelas >= 2 ) {
             this.estrela_2.setVisible(true);
         }
         if (numeroEstrelas >= 3 ) {
             this.estrela_3.setVisible(true);
         }
       
       this.cyclo_per_loc.setText(Double.toString(seleccionado.CYCLO_PER_LOC.registered_value));
       this.cyclo_per_loc.setBackground (seleccionado.CYCLO_PER_LOC.getColor());
       this.loc_per_nom.setText(Double.toString(seleccionado.LOC_PER_OPERATION.registered_value));
      this.loc_per_nom.setBackground((seleccionado.LOC_PER_OPERATION.getColor()));

       this.nom_per_noc.setText(Double.toString(seleccionado.NOM_PER_CLASS.registered_value));
       this.nom_per_noc.setBackground (seleccionado.NOM_PER_CLASS.getColor());
       
       this.noc_nop.setText(Double.toString(seleccionado.NOC_PER_PACKAGE.registered_value));
       this.noc_nop.setBackground (seleccionado.NOC_PER_PACKAGE.getColor());
               
       this.ahh.setText(Double.toString(seleccionado.AHH.registered_value));
       this.ahh.setBackground (seleccionado.AHH.getColor());
               
       this.andc.setText(Double.toString(seleccionado.ANDC.registered_value));
       this.andc.setBackground (seleccionado.ANDC.getColor());
               
       this.fanout_per_calls.setText(Double.toString(seleccionado.FANOUT_PER_CALL.registered_value));
     
       this.fanout_per_calls.setBackground (seleccionado.FANOUT_PER_CALL.getColor());
               
       this.calls_per_nom.setText(Double.toString(seleccionado.CALLS_PER_OPERATION.registered_value));
       this.calls_per_nom.setBackground (seleccionado.CALLS_PER_OPERATION.getColor());
               
       this.maintain_index.setText(Double.toString(seleccionado.MAINTAIN_INDEX.registered_value));
        
       this.maintain_index.setBackground (seleccionado.MAINTAIN_INDEX.getColor());
       this.nocomm.setText(Double.toString(seleccionado.NOCOM.registered_value));
       this.ipl.setText(Double.toString(seleccionado.IPL.registered_value));
       this.volume.setText(Double.toString(seleccionado.VOLUME.registered_value));
       this.difficulty.setText(Double.toString(seleccionado.DIFFICULTY.registered_value));
       this.effort.setText(Double.toString(seleccionado.EFFORT.registered_value));
       this.bugs.setText(Double.toString(seleccionado.BUGS.registered_value));
       this.loc.setText(Double.toString(seleccionado.LOC.registered_value));
       this.loc.setBackground (seleccionado.LOC.getColor());
       
       this.unarg.setText(Double.toString(seleccionado.UNARG.registered_value));
       this.unarg.setBackground (seleccionado.UNARG.getColor());

       this.undec.setText(Double.toString(seleccionado.UNDEC.registered_value));
       this.negneg.setText(Double.toString(seleccionado.NEGNEG.registered_value));
       this.negif.setText(Double.toString(seleccionado.NEGIF.registered_value));
       this.nargs.setText(Double.toString(seleccionado.NARGS.registered_value));
       this.nargs.setBackground (seleccionado.NARGS.getColor());

       this.cyclo_label.setText(Double.toString(seleccionado.CYCLO.registered_value));
       this.nom_label.setText(Double.toString(seleccionado.NOM.registered_value));
       this.noc_label.setText(Double.toString(seleccionado.NOC.registered_value));
       this.nop_label.setText(Double.toString(seleccionado.NOP.registered_value));
       this.fanout_label.setText(Double.toString(seleccionado.FANOUT.registered_value));
       this.calls_label.setText(Double.toString(seleccionado.CALLS.registered_value));

            
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AtsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtsMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtsMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ahh;
    private javax.swing.JTextField andc;
    private javax.swing.JTextField bugs;
    private javax.swing.JLabel calls_label;
    private javax.swing.JTextField calls_per_nom;
    private javax.swing.JLabel cyclo_label;
    private javax.swing.JTextField cyclo_per_loc;
    private javax.swing.JTextField difficulty;
    private javax.swing.JTextField effort;
    private javax.swing.JLabel estrela_1;
    private javax.swing.JLabel estrela_2;
    private javax.swing.JLabel estrela_3;
    private javax.swing.JLabel fanout_label;
    private javax.swing.JTextField fanout_per_calls;
    private javax.swing.JTextField ipl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JList lista_metricas;
    private javax.swing.JTextField loc;
    private javax.swing.JTextField loc_per_nom;
    private javax.swing.JTextField maintain_index;
    private javax.swing.JTextField nargs;
    private javax.swing.JTextField negif;
    private javax.swing.JTextField negneg;
    private javax.swing.JLabel noc_label;
    private javax.swing.JTextField noc_nop;
    private javax.swing.JTextField nocomm;
    private javax.swing.JLabel nom_label;
    private javax.swing.JTextField nom_per_noc;
    private javax.swing.JLabel nop_label;
    private javax.swing.JLabel score;
    private javax.swing.JTextField unarg;
    private javax.swing.JTextField undec;
    private javax.swing.JTextField volume;
    // End of variables declaration//GEN-END:variables

   
}
