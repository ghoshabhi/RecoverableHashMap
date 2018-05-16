package main;

import recoverablehashmap.RecoveryManager;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String mapFileName = "/Users/ghoshabhi/Downloads/RecoverableHashMap/src/map.txt";
        String logFileName = "/Users/ghoshabhi/Downloads/RecoverableHashMap/src/log.txt";
        Map<Integer, Person> map;
        HashMap<Integer, Person> realMap;
        RecoveryManager mgr;
        realMap = new HashMap<>();
        mgr = new RecoveryManager(realMap, mapFileName, logFileName);
        map = mgr.getMap();
        Person abhi = new Person("Abhishek", "Ghosh");
        Person adi = new Person("Aditya", "Ghosh");
        Person baba = new Person("Tushar", "Ghosh");
        Person maa = new Person("Kalpana", "Ghosh");
        Person didi = new Person("Rintu", "Ghosh");
        map.put(1, abhi);
        map.put(2, adi);
        map.put(3, baba);
        map.remove(2);
        map.put(2, maa);
        map.replace(3, map.get(3), didi);
        realMap = null;
        mgr.recover();
        map = mgr.getMap();
//        mgr.flush();
    }
}
