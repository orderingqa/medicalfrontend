/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.model;

// TODO ��ô��̬����һ�������ApgarTableModel�̳�һ���࣬���磺TableModel��
// �������ҿ�������Ҫ��ʱ�������̳С�js���������

// ���������þ��Ǽȿ���Ϊ�鿴������ʱ���ṩ��ʾ֧��
// Ҳ����Ϊ��ӡ��ʱ���ṩͳһ����Ⱦ��ͼ

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ls.jtsk.model.Apgar;



/**
 *
 * @author liushuai
 */
public class ApgarTableModel implements TableModel {

        String[] columnStrings = new String [] {"apgar������", "1���ӷ���", "5���ӷ���", "10���ӷ���"};
        String[] rowTitle = new String[] {"����(��/��)", "����", "������", "�Դ̼���Ӧ������", "Ƥ����ɫ", "�ܷ�"};
        Object[] apgars = null;
                
        public ApgarTableModel(Object[] apgars){
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
                case 1: // ��1�б�ʾ1���ӵ�apgar�������������ĸ�ָ��Ҫ���кţ�Ҳ��rowIndex��
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 1);
                    break;
                case 2: // ��2�б�ʾ5���ӵ�apgar����
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 5);
                    break;
                case 3: // ��3�б�ʾ10���ӵ�apgar����
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 10);
                    break;
                default: ;
            }
            return apgarResult;
        }

        
        // �������Ӧ�ø����еĴ�ӡʹ��
        private String getApgarValueFromIndex(int apgarTypeIndex, Object[] apgars, int apgarInterval) {
            String apgarString = null;
            Apgar ap = null;
            for (int i=0; i<apgars.length; i++) {
                if (((Apgar) apgars[i]).getApgarInterval() == apgarInterval) {
                    ap = (Apgar) apgars[i];
                }
            }
            // ���apgar����һ���Ӿ�û�У���ֱ�ӷ���δ���
            // TODO Ŀǰ�ķ����Ҿ����Ѿ��������ţ���Ϊ��ֻ̨�᷵����ȷ��ֵ��û���Ϊ-1�������Ϊ0,1,2��
            // UI����ʱ����data��չ�ֵ�ת��������
            if (ap == null) return "δ���";
            switch (apgarTypeIndex) {
                case 0:
                    apgarString = ap.getPulse() < 0 ? "δ���" : new Integer(ap.getPulse()).toString();
                    break;
                case 1:
                    apgarString = ap.getRespiration() < 0 ? "δ���" : new Integer(ap.getRespiration()).toString();
                    break;
                case 2:
                    apgarString = ap.getActivity() < 0 ? "δ���" : new Integer(ap.getActivity()).toString();
                    break;
                case 3:
                    apgarString = ap.getGrimace() < 0 ? "δ���" : new Integer(ap.getGrimace()).toString();
                    break;
                case 4:
                    apgarString = ap.getAppearance() < 0 ? "δ���" : new Integer(ap.getAppearance()).toString();
                    break;
                case 5:
                    apgarString = new Integer(ap.getSum()).toString();           
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
