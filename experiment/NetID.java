/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// TODO �����Ϊ�˻�û�����ĵ�ַ�ĳ��򣬵���ϧ�������¾ͻ�������е�������
package ls.jtsk.ui.experiment;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import java.util.*;
import static java.lang.System.out;
/**
 *
 * @author LS
 */
public class NetID {
       public static void main(String args[]) throws SocketException {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets))
                displayInterfaceInformation(netint);
        }
        static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
            out.printf("Display name: %s\n", netint.getDisplayName());
            out.printf("Name: %s\n", netint.getName());
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                out.printf("InetAddress: %s\n", inetAddress);
                
            }
            out.printf("\n");
         }
    }