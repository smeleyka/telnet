import org.snmp4j.mp.SnmpConstants;

import java.util.ArrayList;
import java.util.List;

public class SnmpWalk {
    private static final String NET = "10.10.";
    private static final String COMMUNITY = "public";
    private static final String VLAN_OID = ".1.3.6.1.2.1.17.7.1.4.3.1.1";
    private static final String NAME_OID = ".1.3.6.1.2.1.1.5.0";
    private static final int RETRIES = 1;
    private static final int TIMEOUT = 1500;
    private static final int VERSION = SnmpConstants.version2c;
    private static ArrayList<Switch> switches;

    public static void main(String[] args) {
        switches = new ArrayList<>();
        getSwitches(getIpArr());
        //printSwitches();
        findVlan(105);
    }

//Получаем массив ip
    public static ArrayList getIpArr() {
        ArrayList<String> ipList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 254; j++) {
                ipList.add(NET+i+"."+j);
            }
        }
        return ipList;
    }

//Получаем массив Switches
    public static void getSwitches(List<String>ipList) {
        List<Thread> snmpThreads = new ArrayList<>();
        for (String ip:ipList) {
           snmpThreads.add(new Thread(new SnmpThread(ip, COMMUNITY, VLAN_OID, VERSION, RETRIES, TIMEOUT)));
        }
        try {
            for (Thread t : snmpThreads) {
                t.start();
            }
            for (Thread t : snmpThreads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addSwitch(Switch sw) {
        switches.add(sw);
    }

    public static void printSwitches() {
        for (Switch s : switches) {
            System.out.println(s);
        }
    }

    public static void findVlan(int vlan){
        for (Switch sw:switches){
            if (sw.isVlanExists(vlan)){
                System.out.println("Vlan "+vlan+" on "+sw);
            }
        }
    }


}
//На будущее
//ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
//executorService.shutdown();
//executorService.notifyAll();

