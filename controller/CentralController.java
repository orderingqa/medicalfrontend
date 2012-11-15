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
        printableString.append(getPureApgarPrintableString(collections));
        String fileName = new Long(System.currentTimeMillis()).toString()+".txt";
        
        printToFileAndOpenNotePad(fileName, printableString.toString());
   
        // ���±���ӡ������ɺ��һ�����Ҫԭ���Ĵ�ӡ�о��������low priority��
        apgarFrame.dispose();
        if (caseHistory != null) caseHistory.updateModelFromExternal();
    }
    
    
    public static void printExistCase(Cases existCase) {
        String strForPrint = getPrintableStringFromCase(existCase);
        String fileName = new Long(System.currentTimeMillis()).toString()+".txt";
        printToFileAndOpenNotePad(fileName, strForPrint);
    }
    
    private static String getPrintableStringFromCase(Cases existCase) {
        StringBuffer printableString = new StringBuffer();
        Gravida gravida = existCase.getGravida();
        Doctor doctor = existCase.getDoctor();
        Baby firstBaby = (Baby)gravida.getBabys().toArray()[0];
        Collection apgarCollection = firstBaby.getApgars();
        String hopspitalString = "��������� " + doctor.getDoctorName() + "    �����ţ� " + gravida.getMedicNo() + "\r\n";
        String gravidaString = "���������� " + gravida.getName() + "    ���䣺 " + gravida.getAge() + "\r\n";
        String babyString = "Ӥ������ʱ�䣺 " + firstBaby.getBirthTime() + "    Ӥ���Ա� " + (firstBaby.getGender() == Gender.BOY ? "��" : "Ů" ) + "\r\n"; 
        printableString.append(hopspitalString);
        printableString.append(gravidaString);
        printableString.append(babyString);
        printableString.append(getPureApgarPrintableString(apgarCollection));
        return printableString.toString();
    }
    
    
    private static String getPureApgarPrintableString(Collection collections) {
        StringBuffer apgarPrString = new StringBuffer();
        apgarPrString.append("����ʱ��   ����   ����    ������    �Դ̼���Ӧ������   ��ɫ\r\n");
        apgarPrString.append("----------------------------------------------------------------------------\r\n\r\n");
        Iterator it = collections.iterator();
        while (it.hasNext()) { // TODO ������Ҫsort������1,5,10���ӵĴ�ֽ����������
            // TODO ���ﻹ��Ҫ�ϸ�Ŀ��ƴ���ӡ�����ݵ�format��������༸���ո��������ܵõ�һ������format��txt�ļ���
            Apgar apgar = (Apgar) it.next();
            apgarPrString.append(apgar.toPrintableString()+"\r\n\r\n\r\n\r\n");
        }
        
        // TODO ������Ҫ��û�д�ֵ�apgarʱ���������ж�����ȫ����ʾδ��֡�
        return apgarPrString.toString();
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

    private static void printToFileAndOpenNotePad(String fileName, String printableString) {
        try{
            writeToFile(fileName, printableString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        callExternalCommand("notepad.exe "+fileName);
    }
    
}