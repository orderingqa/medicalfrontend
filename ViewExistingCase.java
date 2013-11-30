/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.print.Printable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.model.Apgar;
import ls.jtsk.model.Apgarinterval;
import ls.jtsk.model.Baby;
import ls.jtsk.model.Cases;
import ls.jtsk.model.Gender;
import ls.jtsk.ui.controller.CentralController;
import ls.jtsk.ui.model.ApgarTableModel;
import ls.jtsk.ui.utility.JTablePrinter;
import ls.jtsk.ui.utility.TablePrintable;

/**
 *
 * @author liushuai
 */
public class ViewExistingCase extends javax.swing.JFrame {
    Cases existCase = null;
    /**
     * Creates new form CaseHistoryViewFrame
     */
    public ViewExistingCase(Cases existCase) {
        initComponents();
        this.existCase = existCase;
        fillUIWithCaseData(existCase);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        medicalNoLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        doctorNameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        gravidaNameLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        gravidaAgeLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        babyGenderLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        babyBirthDateLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        apgarScoreTable = new javax.swing.JTable() {
            @Override
            public Printable getPrintable(PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat) {
                return new TablePrintable(this, printMode, headerFormat, footerFormat);
            }
        };
        printCaseButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        commentLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jieshengrenNameLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("查看病历");
        setResizable(false);

        jButton4.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jButton4.setText("退出");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setText("住院号");

        medicalNoLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        medicalNoLabel.setText("0000001");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel3.setText("计分人员");

        doctorNameLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        doctorNameLabel.setText("王大夫");

        jLabel5.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel5.setText("产母姓名");

        gravidaNameLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        gravidaNameLabel.setText("王菲");

        jLabel7.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel7.setText("岁");

        gravidaAgeLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        gravidaAgeLabel.setText("30");

        jLabel9.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel9.setText("婴儿性别");

        babyGenderLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        babyGenderLabel.setText("男");

        jLabel11.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel11.setText("出生时间");

        babyBirthDateLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        babyBirthDateLabel.setText("2012-05-18 18:30");

        apgarScoreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(apgarScoreTable);

        printCaseButton.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        printCaseButton.setText("打印");
        printCaseButton.setEnabled(false);
        printCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printCaseButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel2.setText("主要诊断");

        commentLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        commentLabel.setText("其他信息");

        jLabel8.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel8.setText("产母年龄");

        jLabel4.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel4.setText("接生人员");

        jieshengrenNameLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jieshengrenNameLabel.setText("王大夫");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gravidaNameLabel)
                                    .addComponent(medicalNoLabel)
                                    .addComponent(jieshengrenNameLabel))
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(babyBirthDateLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(gravidaAgeLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel7))
                                        .addComponent(doctorNameLabel))))
                            .addComponent(babyGenderLabel)
                            .addComponent(commentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(printCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(gravidaNameLabel)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(medicalNoLabel)
                            .addComponent(jLabel4)
                            .addComponent(doctorNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jieshengrenNameLabel))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(babyGenderLabel)
                            .addComponent(jLabel11)
                            .addComponent(babyBirthDateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(commentLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gravidaAgeLabel)
                        .addGap(591, 591, 591))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit
        this.dispose();
    }//GEN-LAST:event_exit

    private void printCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printCaseButtonActionPerformed
//        if (existCase != null) {
//            if (existCase.getGravida().getBabys() != null && existCase.getGravida().getBabys().size() > 0) {
//                Baby baby = (Baby) existCase.getGravida().getBabys().iterator().next();
//                JTable apgarTable = new JTable();
//                apgarTable.setModel(new ApgarTableModel(baby.getApgars().toArray()));
//        TODO [待总]要想打印出东西，这个东西必须已经在swing上画出来，能看到才能打印。
        if (existCase != null) {
//                System.err.println(CentralController.getPrintableCasesTitle(existCase));
                JTablePrinter.printJTable(apgarScoreTable, CentralController.getPrintableCasesTitle(existCase), 
                        "日期： "+new SimpleDateFormat("yyyy年MM月dd日").format(new Date())+"        大夫签名：__________________");
        }
//            }
//        }
    }//GEN-LAST:event_printCaseButtonActionPerformed

    private void fillUIWithCaseData(Cases existCase) {
        if (existCase != null) {
            medicalNoLabel.setText(new Integer(existCase.getGravida().getMedicNo()).toString());
            doctorNameLabel.setText(existCase.getDoctor().getDoctorName());
            jieshengrenNameLabel.setText(existCase.getDoctor().getJieshengrenName());
            gravidaNameLabel.setText(existCase.getGravida().getName());
            gravidaAgeLabel.setText(new Integer(existCase.getGravida().getAge()).toString());
            commentLabel.setText(existCase.getGravida().getComment());
            if (existCase.getGravida().getBabys() != null && existCase.getGravida().getBabys().size() > 0) {
                Baby baby = (Baby) existCase.getGravida().getBabys().iterator().next();
                babyBirthDateLabel.setText(baby.getBirthTime());
                babyGenderLabel.setText(baby.getGender() == Gender.BOY ? "男" : "女");
//              if (baby.getApgars() != null && baby.getApgars().size() > 0) { 
// TODO [待总] 只用baby.getApgars()不为空来决定显示，就解决了如果一个打分也没做，那么我们会统一显示成没有打分
// 所有的显示逻辑都由ApgarTableModel来控制。
                if (baby.getApgars() != null) {
                    apgarScoreTable.setModel(new ApgarTableModel(baby.getApgars().toArray()));
                    CentralController.EnableLargerTableFontSize(apgarScoreTable);
                    printCaseButton.setEnabled(true);
//                    apgarScoreTable.setBorder(new MatteBorder(1, 0, 1, 0, Color.RED));
//                    JTableHeader thdExam = apgarScoreTable.getTableHeader();                  
//                    thdExam.setPreferredSize(new Dimension(thdExam.getWidth(), apgarScoreTable.getRowHeight()*3));
//                    apgarScoreTable.setRowHeight(apgarScoreTable.getRowHeight()*3);
                }
            } else {
                babyBirthDateLabel.setText("");
                babyGenderLabel.setText("");
                printCaseButton.setEnabled(false);
            }
            
//            Object[] tableModel = new Object [][] {
//                {"心率(次/分)", null, "", null},
//                {"呼吸", null, "", null},
//                {"肌张力", null, null, null},
//                {"对刺激反应、怪像", null, null, null},
//                {"皮肤颜色", null, null, null},
//                {"总分", null, null, null}
//            };
        }
    }
    
    // 组织窗口被所有的子窗口关闭
    @Override  
    protected void processWindowEvent(WindowEvent e) {  
        if (e.getID() == WindowEvent.WINDOW_CLOSING)  
            this.dispose(); //直接返回，阻止默认动作，阻止窗口关闭  
        else {
            super.processWindowEvent(e); //该语句会执行窗口事件的默认动作(如：隐藏)  
        }
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
            java.util.logging.Logger.getLogger(ViewExistingCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewExistingCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewExistingCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewExistingCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewExistingCase(CasesHelper.getCasesById(1)).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable apgarScoreTable;
    private javax.swing.JLabel babyBirthDateLabel;
    private javax.swing.JLabel babyGenderLabel;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JLabel doctorNameLabel;
    private javax.swing.JLabel gravidaAgeLabel;
    private javax.swing.JLabel gravidaNameLabel;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jieshengrenNameLabel;
    private javax.swing.JLabel medicalNoLabel;
    private javax.swing.JButton printCaseButton;
    // End of variables declaration//GEN-END:variables
}
