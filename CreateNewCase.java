/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui;
import java.awt.event.WindowEvent;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.ui.controller.CentralController;
/**
 *
 * @author liushuai3
 */
public class CreateNewCase extends javax.swing.JFrame {
    CaseHistory caseHistoryForUpdate = null;
    // TODO createNewCase时，需要判断是否有重复的病历号。
    public void setChForUpdate(CaseHistory caseHistory) {
        caseHistoryForUpdate = caseHistory;
    }
    
    /**
     * Creates new form CaseHistoryModifyFrame
     */
    public CreateNewCase() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doctorNameLabel = new javax.swing.JLabel();
        medicalNoLabel = new javax.swing.JLabel();
        gravidaAgeLabel = new javax.swing.JLabel();
        apgarButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        gravidaNameLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        medicalNoInput = new javax.swing.JTextField();
        doctorInput = new javax.swing.JTextField();
        gravidaInput = new javax.swing.JTextField();
        ageInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gravidaCommentInput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("新建病历");
        setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        setResizable(false);

        doctorNameLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        doctorNameLabel.setText("请输入大夫姓名");

        medicalNoLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        medicalNoLabel.setText("请输入住院号");

        gravidaAgeLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        gravidaAgeLabel.setText("请输入产妇年龄");

        apgarButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        apgarButton.setText("保存并APGAR");
        apgarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apgarButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        saveButton.setText("保存并退出");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        gravidaNameLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        gravidaNameLabel.setText("请输入产妇姓名");

        cancelButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        cancelButton.setText("取消并退出");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        medicalNoInput.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        medicalNoInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicalNoInputActionPerformed(evt);
            }
        });

        doctorInput.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N

        gravidaInput.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N

        ageInput.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N

        jLabel1.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 0));
        jLabel1.setText("(请输入数字)");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setText("(请输入数字)");

        jLabel2.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel2.setText("备注");

        gravidaCommentInput.setColumns(20);
        gravidaCommentInput.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        gravidaCommentInput.setRows(5);
        gravidaCommentInput.setText("其他信息");
        jScrollPane1.setViewportView(gravidaCommentInput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gravidaNameLabel)
                        .addGap(29, 29, 29)
                        .addComponent(gravidaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gravidaAgeLabel)
                        .addGap(18, 18, 18)
                        .addComponent(ageInput, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addGap(142, 142, 142)
                        .addComponent(apgarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(medicalNoLabel)
                            .addComponent(jLabel2))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(medicalNoInput, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(doctorNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(doctorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravidaNameLabel)
                    .addComponent(gravidaAgeLabel)
                    .addComponent(gravidaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medicalNoLabel)
                    .addComponent(doctorNameLabel)
                    .addComponent(medicalNoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doctorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(40, 40, 40))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(apgarButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private boolean validInput() {
        String medicalNoValue = medicalNoInput.getText();
        String ageValue = ageInput.getText();
        
        if (medicalNoValue != null && medicalNoValue.trim().length() > 0 && ageValue != null && ageValue.trim().length() > 0 
                && doctorInput.getText() != null && doctorInput.getText().trim().length() > 0
                && gravidaInput.getText() != null && gravidaInput.getText().trim().length() > 0 ) {
            try {
                Integer.parseInt(medicalNoValue);
                Integer.parseInt(ageValue);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        
        }
        return false;
    }
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (! validInput()) {
            CentralController.showCommonMessageBox();    
            return;
        }
        this.dispose();
        CentralController.saveCaseAndExit(medicalNoInput.getText(), doctorInput.getText(), gravidaInput.getText(), ageInput.getText(), gravidaCommentInput.getText());
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if (CentralController.shouldContinueMessageBox("是否要关闭", "请确认您取消创建病历和录入产妇信息，并关闭本窗口？")){
            this.dispose();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void apgarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apgarButtonActionPerformed
        if (! validInput()) {
            CentralController.showCommonMessageBox();    
            return;
        }
        this.dispose();
        CentralController.saveCaseAndShowBabyInputWindow(medicalNoInput.getText(), doctorInput.getText(), gravidaInput.getText(), ageInput.getText(), gravidaCommentInput.getText());
        
    }//GEN-LAST:event_apgarButtonActionPerformed

    private void medicalNoInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicalNoInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicalNoInputActionPerformed

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
            java.util.logging.Logger.getLogger(CreateNewCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateNewCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateNewCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateNewCase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateNewCase().setVisible(true);
            }
        });
    }
    
    @Override  
    protected void processWindowEvent(WindowEvent e) {  
        if (e.getID() == WindowEvent.WINDOW_CLOSING)  {
            if (CentralController.shouldContinueMessageBox("是否要关闭", "请确认您取消创建病历和录入产妇信息，并关闭本窗口？")){
                this.dispose(); //直接返回，阻止默认动作，阻止窗口关闭  
            }
         }  else {
            super.processWindowEvent(e); //该语句会执行窗口事件的默认动作(如：隐藏)  
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ageInput;
    private javax.swing.JButton apgarButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField doctorInput;
    private javax.swing.JLabel doctorNameLabel;
    private javax.swing.JLabel gravidaAgeLabel;
    private javax.swing.JTextArea gravidaCommentInput;
    private javax.swing.JTextField gravidaInput;
    private javax.swing.JLabel gravidaNameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField medicalNoInput;
    private javax.swing.JLabel medicalNoLabel;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
