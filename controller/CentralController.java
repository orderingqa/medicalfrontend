/*
 * �������Ϊ����UI�Ŀ�����������������ͼ�����ݣ�Ȼ�����һ�����߼����㣬��䲢������Ӧ���µ���ͼ��
 */
package ls.jtsk.ui.controller;

import java.util.Collection;
import javax.swing.JFrame;
import ls.jtsk.helper.ApgarHelper;
import ls.jtsk.helper.BabyHelper;
import ls.jtsk.helper.CasesHelper;
import ls.jtsk.model.Cases;
import ls.jtsk.ui.APGARTab;
import ls.jtsk.ui.CreateNewBaby;

/**
 *
 * @author liushuai
 */
public class CentralController {
    private static CasesHelper ch = new CasesHelper();
    
    public static void saveCaseAndExit(String medicalNo, String doctorName, String gravidaName, String age) {
        ch.addCase(Integer.parseInt(medicalNo), doctorName, gravidaName, Integer.parseInt(age));
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
        // TODO ��ô������ʹ��������������������ֵ����ͨ����������ɣ��������޸Ĵ��룿
        //long newCaseId = 0;
        CreateNewBaby cnb = new CreateNewBaby(newCaseId);
        cnb.setTitle("�����ţ�" + medicalNo + "������"+gravidaName);
        cnb.setVisible(true);
    }
    
    public static void saveBabyAndShowApgarScoreWindow(long momId, int babyGender, String babyBirthTime, String babyWindowTitle) {
        long babyId = BabyHelper.addBaby(momId, babyGender, babyBirthTime);
        //long babyId = 0;
        APGARTab apgarWindow = new APGARTab(momId, babyId);
        apgarWindow.setTitle(babyWindowTitle+" �Ա� " + babyGender + " ����ʱ��: " + babyBirthTime);
        apgarWindow.setVisible(true);
    }
    
    public static void saveApgarAndDisposeWindow(long momId, long babyId, Collection collections, JFrame apgarFrame){
        System.out.println(collections.size());
        ApgarHelper.addApgar(momId, babyId, collections);
        apgarFrame.dispose();
    }
}