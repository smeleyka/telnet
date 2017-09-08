

public class SnmpWalk {
    private static final String NET = "10.10.0.";
    private static final String COMMUNITY = "public";
    private static final String OID = ".1.3.6.1.2.1.17.7.1.4.3.1.1";
    private static final int RETRIES = 1;
    private static final int TIMEOUT = 1500;
    private static final int VERSION = 1;
    static String[] switches = null;

    public static void main(String[] args) {
        switches = getSwitchesArr();
        SnmpThread[] snmpthread = new SnmpThread[switches.length];

        for (int i = 0; i < switches.length; i++) {
            String ip = switches[i];
            snmpthread[i] = new SnmpThread(ip,COMMUNITY, OID,RETRIES,TIMEOUT);
            new Thread(snmpthread[i]).start();
        }


    }


    public static String[] getSwitchesArr() {
        String[] arr = new String[254];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = NET + (i + 1);
        }

        return arr;

    }

}


