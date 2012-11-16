/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.model;

// TODO 怎么动态的让一个类比如ApgarTableModel继承一个类，比如：TableModel。
// 这样，我可以在需要的时候让它继承。js好像很容易

// 这个类的作用就是既可以为查看病历的时候提供显示支持
// 也可以为打印的时候提供统一的渲染视图

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ls.jtsk.model.Apgar;



/**
 *
 * @author liushuai
 */
public class ApgarTableModel implements TableModel {

        String[] columnStrings = new String [] {"apgar评分项", "1分钟分数", "5分钟分数", "10分钟分数"};
        String[] rowTitle = new String[] {"心率(次/分)", "呼吸", "肌张力", "对刺激反应、怪象", "皮肤颜色", "总分"};
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
                case 1: // 第1列表示1分钟的apgar分数，具体是哪个指标要看行号，也即rowIndex。
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 1);
                    break;
                case 2: // 第2列表示5分钟的apgar分数
                    apgarResult = getApgarValueFromIndex(rowIndex, (Object[]) apgars, 5);
                    break;
                case 3: // 第3列表示10分钟的apgar分数
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
            // 如果apgar在这一分钟就没有，则直接返回未打分
            // TODO 目前的方法我觉得已经算是优雅，因为后台只会返回正确的值，没打分为-1，打完分为0,1,2。
            // UI调用时进行data到展现的转换工作。
            if (ap == null) return "未打分";
            switch (apgarTypeIndex) {
                case 0:
                    apgarString = ap.getPulse() < 0 ? "未打分" : new Integer(ap.getPulse()).toString();
                    break;
                case 1:
                    apgarString = ap.getRespiration() < 0 ? "未打分" : new Integer(ap.getRespiration()).toString();
                    break;
                case 2:
                    apgarString = ap.getActivity() < 0 ? "未打分" : new Integer(ap.getActivity()).toString();
                    break;
                case 3:
                    apgarString = ap.getGrimace() < 0 ? "未打分" : new Integer(ap.getGrimace()).toString();
                    break;
                case 4:
                    apgarString = ap.getAppearance() < 0 ? "未打分" : new Integer(ap.getAppearance()).toString();
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
