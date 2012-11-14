/*
 * �������Ϊ����UI�Ŀ�����������������ͼ�����ݣ�Ȼ�����һ�����߼����㣬��䲢������Ӧ���µ���ͼ��
 */
package ls.jtsk.ui.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import ls.jtsk.helper.ApgarHelper;
import ls.jtsk.helper.BabyHelper;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.model.Apgar;
import ls.jtsk.model.Cases;
import ls.jtsk.ui.APGARTab;
import ls.jtsk.ui.CaseHistory;
import ls.jtsk.ui.CreateNewBaby;
import ls.jtsk.ui.CreateNewCase;
import ls.jtsk.ui.ViewExistingCase;

/**
 *
 * @author liushuai
 */
public class CentralController {
    private static CasesHelper ch = new CasesHelper();
    
    // �����ʼ���ڴ���һ��caseʱ������Ȼ�������и��²����е��á�
    private static CaseHistory caseHistory = null;
    
    
    public static void saveCaseAndExit(String medicalNo, String doctorName, String gravidaName, String age) {
        ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age));
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    /**
     * 
     * ����������ͼcreateNewCase�Ŀ����������������ͼ�ϴ��������û����룬Ȼ��ѡ��һ���µ���ͼ���ء�
     * @param medicalNo
     * @param doctorName
     * @param gravidaName
     * @param age 
     */
    public static void saveCaseAndShowBabyInputWindow(String medicalNo, String doctorName, String gravidaName, String age) {
        long newCaseId = ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age)); // ģ�͸�����ϣ�������ƴװ�µ���ͼ��
        // TODO ��ô������ʹ��������������������ֵ����ͨ����������ɣ��������޸Ĵ��룿�����ҿ�����ʱ������û��db�Ĵ��롣
//        long newCaseId = 0;
        CreateNewBaby cnb = new CreateNewBaby(newCaseId);
        cnb.setTitle("�����ţ�" + medicalNo + "������"+gravidaName);
        cnb.setVisible(true);
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    public static void saveBabyAndShowApgarScoreWindow(long momId, int babyGender, String babyBirthTime, String babyWindowTitle) {
        long babyId = BabyHelper.addBaby(momId, babyGender, babyBirthTime); // update backend model
//        long babyId = 0;
        APGARTab apgarWindow = new APGARTab(momId, babyId);
        apgarWindow.setTitle(babyWindowTitle+" �Ա� " + babyGender + " ����ʱ��: " + babyBirthTime);
        apgarWindow.setVisible(true);
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    public static void saveApgarAndDisposeWindow(long momId, long babyId, Collection collections, JFrame apgarFrame){
        ApgarHelper.addApgar(momId, babyId, collections);
        apgarFrame.dispose();
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    public static void saveApgarAndPrint(long momId, long babyId, Collection collections, JFrame apgarFrame) {
        ApgarHelper.addApgar(momId, babyId, collections);
        StringBuffer printableString = new StringBuffer();
        printableString.append(apgarFrame.getTitle()+"\r\n");
        printableString.append("����ʱ��   ����   ����    ������    �Դ̼���Ӧ������   ��ɫ\r\n");
        printableString.append("----------------------------------------------------------------------------\r\n\r\n");
        Iterator it = collections.iterator();
        while (it.hasNext()) { // TODO ������Ҫsort������1,5,10���ӵĴ�ֽ����������
            // TODO ���ﻹ��Ҫ�ϸ�Ŀ��ƴ���ӡ�����ݵ�format��������༸���ո��������ܵõ�һ������format��txt�ļ���
            Apgar apgar = (Apgar) it.next();
            printableString.append(apgar.toPrintableString()+"\r\n\r\n\r\n\r\n");
        }
        String fileName = new Long(System.currentTimeMillis()).toString()+".txt";
        try{
            writeToFile(fileName, printableString.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        callExternalCommand("notepad.exe "+fileName);
   
        // ���±���ӡ������ɺ��һ�����Ҫԭ���Ĵ�ӡ�о��������low priority��
        apgarFrame.dispose();
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    // TODO ��Ҫ����ֵ��ȷ���Ƿ�д�ļ��ɹ�����ΪIO����̶Ȼ�ʧ��
    private static void writeToFile(String fileName, String fileContent) throws IOException{
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
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
    
    public static void main(String[] args) {
        try {
            writeToFile("a.txt", "bcde");
            callExternalCommand("notepad.exe " + "a.txt");
        } catch (IOException ex) {
            Logger.getLogger(CentralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void displayCreateCaseWindow(CaseHistory ch) {
        CreateNewCase cnc = new CreateNewCase();
        cnc.setChForUpdate(ch);
        cnc.setTitle("�����µĲ���");
        cnc.setVisible(true);
        caseHistory = ch;
    }
    
    public static void viewExistingCase(Cases existCase) {
        new ViewExistingCase(existCase).setVisible(true);
    }
    
}