/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui;

import java.awt.Font;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.helper.CustomerHelper;
import ls.jtsk.model.Cases;
import ls.jtsk.ui.controller.CentralController;

/**
 *
 * @author liushuai
 */


// TODO 待总结，我手动的将所有的eclipse下面引用的jar文件拷贝到一个lib下，然后修正log4j.properties，然后在将apgar.db文件拷贝到外层，这样实现了在netbean下面
// 运行程序。

public class CaseHistory extends javax.swing.JFrame {
    
    // TODO 这个需要控制不能为-1，以及需要控制什么时候不能上一页，什么时候不能下一页。
    private int currentPage = 0;
    private int numbersPerPage = 15;
    // 该list用来作为table和model进行通信的媒介。基于选择的表行，我们从这个list中取出数据，从而往后面传递。
    List <Cases> modelList = null;
//    Cases currentCase = null;
    // TODO 采用currentSelectIndex而不是上面的currentCase来标记当前鼠标选择。
//    原因是如果用currentCase来标记当前鼠标位置，那么当新加Cases而造成model变化后，查看当前Case详情时，会是model变化前的那个指向的case
    int currentSelectIndex = 0;
    // TODO case history 需要title，title应该是输入的医院和大夫名字。
    /**
     * Creates new form CaseHistory
     */
    public CaseHistory() {
        initComponents();
        fillCaseListAtFirstTime();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        caseListTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        createCaseButton = new javax.swing.JButton();
        viewExistingCasesButton = new javax.swing.JButton();
        modifyCaseButton = new javax.swing.JButton();
        deleteCaseButton = new javax.swing.JButton();
        apgarScoreButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        previousPageButton = new javax.swing.JButton();
        nextPageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("阿氏评分法自动评分仪PC版");
        setResizable(false);

        caseListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        caseListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                caseSelectFunction(evt);
            }
        });
        jScrollPane1.setViewportView(caseListTable);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setText("单位");

        titleLabel.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        titleLabel.setText("宣武医院妇产科");

        createCaseButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        createCaseButton.setText("新建病历");
        createCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCaseButtonActionPerformed(evt);
            }
        });

        viewExistingCasesButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        viewExistingCasesButton.setText("查看病历");
        viewExistingCasesButton.setEnabled(false);
        viewExistingCasesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewExistingCasesButtonActionPerformed(evt);
            }
        });

        modifyCaseButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        modifyCaseButton.setText("修改病历");
        modifyCaseButton.setEnabled(false);
        modifyCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyCaseButtonActionPerformed(evt);
            }
        });

        deleteCaseButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        deleteCaseButton.setText("删除病历");
        deleteCaseButton.setEnabled(false);
        deleteCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCaseButtonActionPerformed(evt);
            }
        });

        apgarScoreButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        apgarScoreButton.setText("apgar打分");
        apgarScoreButton.setEnabled(false);
        apgarScoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apgarScoreButtonActionPerformed(evt);
            }
        });

        printButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        printButton.setText("预览并打印");
        printButton.setEnabled(false);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        previousPageButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        previousPageButton.setText("上一页");
        previousPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPageButtonActionPerformed(evt);
            }
        });

        nextPageButton.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N
        nextPageButton.setText("下一页");
        nextPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(previousPageButton)
                        .addGap(66, 66, 66)
                        .addComponent(nextPageButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(viewExistingCasesButton)
                        .addGap(109, 109, 109)
                        .addComponent(modifyCaseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(deleteCaseButton)
                        .addGap(81, 81, 81)
                        .addComponent(printButton)
                        .addGap(69, 69, 69)
                        .addComponent(apgarScoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titleLabel)
                        .addComponent(jLabel1))
                    .addComponent(createCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewExistingCasesButton)
                    .addComponent(modifyCaseButton)
                    .addComponent(deleteCaseButton)
                    .addComponent(apgarScoreButton)
                    .addComponent(printButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousPageButton)
                    .addComponent(nextPageButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageButtonActionPerformed
        currentPage++;
        updateModelFromExternal();
    }//GEN-LAST:event_nextPageButtonActionPerformed

    public void updateModelFromExternal() {
        modelList = CasesHelper.getCasesSortByField(numbersPerPage, currentPage, "createDate DESC");
        ((CaseTableModel)caseListTable.getModel()).setList(modelList);
        caseListTable.updateUI();
    }
        
    // TODO 这个函数也提供给外部调用，因为如果对一个case做了修改，那么下面的button需要在更改保存后更新。
    public void updateButtonForCurrentCase() {
        currentSelectIndex = caseListTable.getSelectedRow();
        // 没有选中任何case时，不应该去更新按钮状态，不然数组越界，因为currentSelectIndex为-1
        if (currentSelectIndex > -1) {
            Cases currentCase = (Cases) modelList.get(currentSelectIndex);
            if (currentCase.getGravida().getBabys() != null && currentCase.getGravida().getBabys().size() > 0) {
//            int babyNum = currentCase.getGravida().getBabys().size();
                printButton.setEnabled(true);
                apgarScoreButton.setEnabled(false);
            } else {
                printButton.setEnabled(false);
                apgarScoreButton.setEnabled(true);
            }
        } else {
            printButton.setEnabled(false);
            apgarScoreButton.setEnabled(false);
        }
        viewExistingCasesButton.setEnabled(true);
        modifyCaseButton.setEnabled(true);
        deleteCaseButton.setEnabled(true);
    }
    
    private void createCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCaseButtonActionPerformed
        CentralController.displayCreateCaseWindow(this);        
    }//GEN-LAST:event_createCaseButtonActionPerformed

    private void previousPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPageButtonActionPerformed
        currentPage--;
        updateModelFromExternal();
    }//GEN-LAST:event_previousPageButtonActionPerformed

    private void caseSelectFunction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caseSelectFunction
        updateButtonForCurrentCase();
    }//GEN-LAST:event_caseSelectFunction

    private void viewExistingCasesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewExistingCasesButtonActionPerformed
        Cases currentCase = (Cases) modelList.get(currentSelectIndex);
        CentralController.viewExistingCase(currentCase);
    }//GEN-LAST:event_viewExistingCasesButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        Cases currentCase = (Cases) modelList.get(currentSelectIndex);
        CentralController.viewExistingCase(currentCase);
    }//GEN-LAST:event_printButtonActionPerformed

    private void apgarScoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apgarScoreButtonActionPerformed
        Cases currentCase = (Cases) modelList.get(currentSelectIndex);
        CentralController.showBabyInputFromCaseList(currentCase, this);
    }//GEN-LAST:event_apgarScoreButtonActionPerformed

    private void modifyCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyCaseButtonActionPerformed
        Cases currentCase = (Cases) modelList.get(currentSelectIndex);
        CentralController.modifyExistingCase(currentCase, this);
    }//GEN-LAST:event_modifyCaseButtonActionPerformed

    private void deleteCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCaseButtonActionPerformed
        Cases currentCase = (Cases) modelList.get(currentSelectIndex);
        CentralController.deleteExistCase(currentCase, this);
    }//GEN-LAST:event_deleteCaseButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CaseHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaseHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaseHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaseHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaseHistory().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apgarScoreButton;
    private javax.swing.JTable caseListTable;
    private javax.swing.JButton createCaseButton;
    private javax.swing.JButton deleteCaseButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyCaseButton;
    private javax.swing.JButton nextPageButton;
    private javax.swing.JButton previousPageButton;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton viewExistingCasesButton;
    // End of variables declaration//GEN-END:variables


    private void fillCaseListAtFirstTime() {
        modelList  = CasesHelper.getLatestCases(numbersPerPage);
//        Cases topCase = (Cases)list.get(0);
//        System.out.println(topCase.getGravida().getName());
        CaseTableModel ctm = new CaseTableModel(modelList);
        caseListTable.setModel(ctm);
        currentPage = 1;
        titleLabel.setText(CustomerHelper.getFirstCustomer().getHospitalAndDepString());
        CentralController.EnableLargerTableFontSize(caseListTable); 
        modifyCaseButton.setVisible(false);
        deleteCaseButton.setVisible(false);
    }


    
    class CaseTableModel implements TableModel {
        List <Cases> caseList = null;
        String[] columnStrings = new String [] {"建档日期", "产母姓名", "产母年龄", "住院号", "评分人员", "接生人员"};
        
        public CaseTableModel(List <Cases> l) {
            caseList = l;
        }
        
        public void setList(List <Cases> newList) {
            caseList = newList;
        }
        
        @Override
        public int getRowCount() {
            return caseList.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
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
//            throw new UnsupportedOperationException("Not supported yet.");
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Cases currentCase = caseList.get(rowIndex);
            String value = null;
            switch (columnIndex) {
                case 0:
                    value = currentCase.getCreateDate(); break;
                case 1:
                    value = currentCase.getGravida().getName(); break;
                case 2:
                    value = new Integer(currentCase.getGravida().getAge()).toString(); break;
                case 3:
                    value = new Integer(currentCase.getGravida().getMedicNo()).toString(); break;
                case 4:
                    value = currentCase.getDoctor().getDoctorName(); break;
                case 5:
                    value = currentCase.getDoctor().getJieshengrenName(); break;
                default: ;
            }
            return value;
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
}
