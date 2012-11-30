/*
 * 本类会作为所有UI的控制器，接收所有视图的数据，然后基于一定的逻辑计算，填充并返回相应的新的视图。
 */
package ls.jtsk.ui.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import ls.jtsk.helper.ApgarHelper;
import ls.jtsk.helper.BabyHelper;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.model.Apgar;
import ls.jtsk.model.Baby;
import ls.jtsk.model.Cases;
import ls.jtsk.model.Doctor;
import ls.jtsk.model.Gender;
import ls.jtsk.model.Gravida;
import ls.jtsk.ui.APGARTab;
import ls.jtsk.ui.CaseHistory;
import ls.jtsk.ui.CreateNewBaby;
import ls.jtsk.ui.CreateNewCase;
import ls.jtsk.ui.ViewExistingCase;
import ls.jtsk.ui.assistant.ApgarPrintableTable;
import ls.jtsk.ui.model.ApgarTableModel;

/**
 *
 * @author liushuai
 */
public class CentralController {
    private static CasesHelper ch = new CasesHelper();
    
    // 这个初始化在创建一个case时产生，然后在所有更新操作中调用。
    private static CaseHistory caseHistory = null;
    
    
    public static void saveCaseAndExit(String medicalNo, String doctorName, String gravidaName, String age) {
        ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age));
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    // -------------------------------------------------------------------------------------------------------------------------------
    /**
     * 
     * 本方法是视图createNewCase的控制器。它会接收视图上传过来的用户输入，然后选择一个新的视图返回。
     * @param medicalNo
     * @param doctorName
     * @param gravidaName
     * @param age 
     */
    public static void saveCaseAndShowBabyInputWindow(String medicalNo, String doctorName, String gravidaName, String age) {
        long newCaseId = ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age)); // 模型更新完毕，接下来拼装新的视图。
        // TODO 怎么样才能使得我想运行上下两个赋值可以通过配置来完成，而不是修改代码？这样我可以随时测试有没有db的代码。
//        long newCaseId = 0;
        CreateNewBaby cnb = new CreateNewBaby(newCaseId);
        cnb.setTitle("大夫姓名:"+doctorName+" 病历号:" + medicalNo + "--产妇姓名："+gravidaName+" 年龄年龄:"+age);
        cnb.setVisible(true);
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    public static void showBabyInputFromCaseList(Cases existCase, CaseHistory ch) {
        if (existCase != null) {
            caseHistory = ch;
            long caseId = existCase.getId();
            CreateNewBaby cnb = new CreateNewBaby(caseId);
            cnb.setTitle("大夫姓名：" + existCase.getDoctor().getDoctorName() +" 病历号:" + existCase.getGravida().getMedicNo() + "--产妇姓名："+existCase.getGravida().getName()+" 年龄年龄:"+existCase.getGravida().getAge());
            cnb.setVisible(true);
        } else {
            showCommonMessageBox();
        }
    }
    // -------------------------------------------------------------------------------------------------------------------------------    
    
    public static void saveBabyAndShowApgarScoreWindow(long momId, int babyGender, String babyBirthTime, String babyWindowTitle) {
        long babyId = BabyHelper.addBaby(momId, babyGender, babyBirthTime); // update backend model
//        long babyId = 0;
        APGARTab apgarWindow = new APGARTab(momId, babyId);
        apgarWindow.setTitle(babyWindowTitle+"--性别:" + (babyGender == Gender.BOY ? "男" : "女" ) + " 出生时间:" + babyBirthTime);
        apgarWindow.setVisible(true);
        if (caseHistory != null) {
            caseHistory.updateModelFromExternal();
            caseHistory.updateButtonForCurrentCase();
        }
    }
    


    // 打完分后关闭apgar窗口，不打印
    public static void saveApgarAndDisposeWindow(long momId, long babyId, Collection collections, JFrame apgarFrame){
        ApgarHelper.addApgar(momId, babyId, collections);
        apgarFrame.dispose();
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    // 打完分后立刻调用打印功能
    public static void saveApgarAndPrint(long momId, long babyId, Collection collections, JFrame apgarFrame) {
        ApgarHelper.addApgar(momId, babyId, collections);
        apgarFrame.dispose();
        if (caseHistory != null) caseHistory.updateModelFromExternal();
        Cases existCase = CasesHelper.getCasesById(momId);
        new ViewExistingCase(existCase).setVisible(true);
        
//        StringBuffer printableString = new StringBuffer();
//        printableString.append(apgarFrame.getTitle());
//        printableString.append("\r\n-------------------------------------------------------------------------------------------------");
//        printableString.append(getPureApgarPrintableString(collections));
//        String fileName ="temp/" + new Long(System.currentTimeMillis()).toString()+".txt";
//        
//        printToFileAndOpenNotePad(fileName, printableString.toString());
//   
//        // 记事本打印程序完成后，我还是需要原生的打印研究，这个是low priority。
          
    }
    
    // 从case list中调用打印功能
    // 基于对话框和java的标准打印接口后，这个函数被弃用。
    public static void printExistCase(Cases existCase) {
//      String strForPrint = getPrintableStringFromCase(existCase);        
        if (existCase.getGravida().getBabys() != null && existCase.getGravida().getBabys().size() > 0) {
            String caseTitle = getPrintableCasesTitle(existCase);
            Baby baby = (Baby) existCase.getGravida().getBabys().iterator().next();
            if (baby.getApgars() != null) {
                String strForPrint = caseTitle + ApgarPrintableTable.getPrintableApgarString(new ApgarTableModel(baby.getApgars().toArray()));
                String fileName ="temp/" + new Long(System.currentTimeMillis()).toString()+".txt";
                //TODO [待总] 良好的封装函数，会大大减少调整代码和添加新feature的工作量。
                printToFileAndOpenNotePad(fileName, strForPrint);
            }
        }
    }
    
    public static String getPrintableCasesTitle(Cases existCase) {
        StringBuffer printableString = new StringBuffer();
        Gravida gravida = existCase.getGravida();
        Doctor doctor = existCase.getDoctor();
        Baby firstBaby = (Baby)gravida.getBabys().toArray()[0];
        String gravidaString = "产妇姓名:" + gravida.getName() + "                                    产妇年龄:" + gravida.getAge() + "\n";
        String hopspitalString = "大夫姓名:" + doctor.getDoctorName() + "                                    病历号:" + gravida.getMedicNo() + "\n";
        String babyString = "婴儿出生时间:" + firstBaby.getBirthTime() + "          婴儿性别:" + (firstBaby.getGender() == Gender.BOY ? "男" : "女" ); 
        printableString.append(gravidaString);
        printableString.append(hopspitalString);
        printableString.append(babyString);
//        printableString.append("\r\n-------------------------------------------------------------------------------------------------");
//        printableString.append(getPureApgarPrintableString(apgarCollection));
        return printableString.toString();
    }
    
    
    private static String getPureApgarPrintableString(Collection collections) {
//        StringBuffer apgarPrString = new StringBuffer();
//        apgarPrString.append("评分时间   心率   呼吸    肌张力    对刺激反应、怪象   颜色\r\n");
//        apgarPrString.append("----------------------------------------------------------------------------\r\n\r\n");
//        Iterator it = collections.iterator();
//        while (it.hasNext()) { // TODO 这里需要sort，按照1,5,10分钟的打分结果进行排序
//            // TODO 这里还需要严格的控制待打印的数据的format，比如最多几个空格，这样就能得到一个良好format的txt文件。
//            Apgar apgar = (Apgar) it.next();
//            apgarPrString.append(apgar.toPrintableString()+"\r\n\r\n\r\n\r\n");
//        }
//        
//        // TODO 这里需要对没有打分的apgar时间间隔进行判定，并全部显示未打分。
//        return apgarPrString.toString();
        
        // TODO [待总] 调用最新的打印统一接口
        return ApgarPrintableTable.getPrintableApgarString(new ApgarTableModel(collections.toArray()));
    }
    
    // TODO 需要返回值以确定是否写文件成功，因为IO极大程度会失败
    private static void writeToFile(String fileName, String fileContent) throws IOException{
        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
        output.write(fileContent);
        output.close();
    }
    
    private static void callExternalCommand(String commandString) {
    Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(commandString);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void displayCreateCaseWindow(CaseHistory ch) {
        CreateNewCase cnc = new CreateNewCase();
        cnc.setChForUpdate(ch);
        cnc.setTitle("创建新的病历");
        cnc.setVisible(true);
        caseHistory = ch;
    }
    
    public static void viewExistingCase(Cases existCase) {
        new ViewExistingCase(existCase).setVisible(true);
    }

    private static void printToFileAndOpenNotePad(String fileName, String printableString) {
        try{
            writeToFile(fileName, printableString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        callExternalCommand("notepad.exe "+fileName);
    }
    
    public static void showCommonMessageBox() {
        JOptionPane.showMessageDialog(null, "请按照提示输入正确的格式");
    }
    
    
    public static boolean shouldContinueMessageBox(String promptTitle, String promptMessageBody) {
        Object[] options = {"确定","取消"}; 
        int response = JOptionPane.showOptionDialog(null, promptMessageBody, promptTitle,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
        if(response == 0) {  
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        try {
            writeToFile("a.txt", "bcde");
            callExternalCommand("notepad.exe " + "a.txt");
        } catch (IOException ex) {
            Logger.getLogger(CentralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}