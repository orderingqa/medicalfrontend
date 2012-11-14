/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ls.jtsk.model.Apgar;
import ls.jtsk.model.Apgarinterval;
import ls.jtsk.model.Baby;
import ls.jtsk.model.Cases;
import ls.jtsk.model.Gender;

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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        apgarScoreTable = new javax.swing.JTable();

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
        setTitle("修改病历");

        jButton2.setText("保存并打印");

        jButton3.setText("保存");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("取消");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit(evt);
            }
        });

        jLabel1.setText("住院号");

        medicalNoLabel.setText("0000001");

        jLabel3.setText("主治医师");

        doctorNameLabel.setText("王大夫");

        jLabel5.setText("产妇姓名");

        gravidaNameLabel.setText("王菲");

        jLabel7.setText("产妇年龄");

        gravidaAgeLabel.setText("30");

        jLabel9.setText("婴儿性别");

        babyGenderLabel.setText("男");

        jLabel11.setText("出生时间");

        babyBirthDateLabel.setText("2012-05-18 18:30");

        apgarScoreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"心率(次/分)", null, "", null},
                {"呼吸", null, "", null},
                {"肌张力", null, null, null},
                {"对刺激反应、怪像", null, null, null},
                {"皮肤颜色", null, null, null},
                {"总分", null, null, null}
            },
            new String [] {
                "APGAR评分项", "1分钟分数", "5分钟分数", "10分钟分数"
            }
        ));
        jScrollPane1.setViewportView(apgarScoreTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(medicalNoLabel)
                            .addComponent(gravidaNameLabel))
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(gravidaAgeLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(doctorNameLabel))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(35, 35, 35)
                        .addComponent(babyGenderLabel)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(babyBirthDateLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)
                        .addGap(144, 144, 144)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(medicalNoLabel)
                    .addComponent(jLabel3)
                    .addComponent(doctorNameLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(gravidaNameLabel)
                    .addComponent(jLabel7)
                    .addComponent(gravidaAgeLabel))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(babyGenderLabel)
                    .addComponent(jLabel11)
                    .addComponent(babyBirthDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void exit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit
        this.dispose();
    }//GEN-LAST:event_exit

    private void fillUIWithCaseData(Cases existCase) {
        if (existCase != null) {
            medicalNoLabel.setText(new Integer(existCase.getGravida().getMedicNo()).toString());
            doctorNameLabel.setText(existCase.getDoctor().getDoctorName());
            gravidaNameLabel.setText(existCase.getGravida().getName());
            gravidaAgeLabel.setText(new Integer(existCase.getGravida().getAge()).toString());
            if (existCase.getGravida().getBabys() != null && existCase.getGravida().getBabys().size() > 0) {
                Baby baby = (Baby) existCase.getGravida().getBabys().iterator().next();
                babyBirthDateLabel.setText(baby.getBirthTime());
                babyGenderLabel.setText(baby.getGender() == Gender.BOY ? "男" : "女");
                if (baby.getApgars() != null && baby.getApgars().size() > 0) {
                    apgarScoreTable.setModel(new tableModel(baby.getApgars().toArray()));
                }
            } else {
                babyBirthDateLabel.setText("");
                babyGenderLabel.setText("");
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
    
    private class tableModel  implements TableModel {

        String[] columnStrings = new String [] {"apgar评分项", "1分钟分数", "5分钟分数", "10分钟分数"};
        String[] rowTitle = new String[] {"心率(次/分)", "呼吸", "肌张力", "对刺激反应、怪象", "皮肤颜色", "总分"};
        Object[] apgars = null;
                
        public tableModel(Object[] apgars){
            this.apgars = apgars;
        }
        
        @Override
        public int getRowCount() {
            return 6;
        }

        @Override
        public int getColumnCount() {
            return columnStrings.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnStrings[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String apgarResult = null;
            switch (columnIndex) {
                case 0 :
                    apgarResult = rowTitle[rowIndex];
                    break;
                case 1:
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 1);
                    break;
                case 2:
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 5);
                    break;
                case 3:
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 10);
                    break;
                default: ;
            }
            return apgarResult;
        }

        
        // 这个函数应该给所有的打印使用
        private String getApgarValueFromIndex(int apgarTypeIndex, Object[] apgars, int apgarInterval) {
            String apgarString = null;
            Apgar ap = null;
            for (int i=0; i<apgars.length; i++) {
                if (((Apgar) apgars[i]).getApgarInterval() == apgarInterval) {
                    ap = (Apgar) apgars[i];
                }
            }
            if (ap == null) return "";
            switch (apgarTypeIndex) {
                case 0:
                    apgarString = ap.getPulse() < 0 ? "未打分" : new Integer(ap.getPulse()).toString();
                    break;
                case 1:
                    apgarString = ap.getRespiration() < 0 ? "未打分" : new Integer(ap.getPulse()).toString();
                    break;
                case 2:
                    apgarString = ap.getActivity() < 0 ? "未打分" : new Integer(ap.getPulse()).toString();
                    break;
                case 3:
                    apgarString = ap.getGrimace() < 0 ? "未打分" : new Integer(ap.getPulse()).toString();
                    break;
                case 4:
                    apgarString = ap.getAppearance() < 0 ? "未打分" : new Integer(ap.getPulse()).toString();
                    break;
                case 5:
                    // TODO 总分           
                    break;
                default: ;                      
            } 
            return apgarString;
        }
                      
                
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
//            throw new UnsupportedOperationException("Not supported yet.");
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
                new ViewExistingCase(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable apgarScoreTable;
    private javax.swing.JLabel babyBirthDateLabel;
    private javax.swing.JLabel babyGenderLabel;
    private javax.swing.JLabel doctorNameLabel;
    private javax.swing.JLabel gravidaAgeLabel;
    private javax.swing.JLabel gravidaNameLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel medicalNoLabel;
    // End of variables declaration//GEN-END:variables
}
