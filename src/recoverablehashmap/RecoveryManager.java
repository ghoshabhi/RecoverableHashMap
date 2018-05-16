package recoverablehashmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.ReadWriteUtil.writeToFile;

public class RecoveryManager<K,V> {
    private Map<K, V> map;
    private String mapFileName;
    private String logFileName;

    public RecoveryManager(Map<K, V> map, String mapFileName, String logFileName) {
        this.map = new RecoverableHashMap<>(map, logFileName);
        this.mapFileName = mapFileName;
        this.logFileName = logFileName;
    }

    // save the state of the map to map.txt file
    public void flush() {
        map.forEach(((k, v) -> {
            String mapEntry = k + " " + v;
            writeToFile(mapEntry, mapFileName);
        }));
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(logFileName));
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // look at the log file and replay the operations on the map object
    public void recover() {
        try (Stream<String> stream = Files.lines(Paths.get(logFileName))) {
            List<String> lines = stream.collect(Collectors.toList());
            lines.forEach(line -> {
                String[] operationLine = line.split("\\s+");
                String operation = operationLine[0];
                if(operation.equalsIgnoreCase("put")) {
                    K key = (K)operationLine[1];
                    V value = (V)operationLine[2];
                    map.put(key, value);
                } else if (operation.equalsIgnoreCase("remove")) {
                    K key = (K)operationLine[1];
                    map.remove(key);
                } else if (operation.equalsIgnoreCase("replace")) {
                    K key = (K)operationLine[1];
                    V oldValue = (V)operationLine[4];
                    V newValue = (V)operationLine[6];
                    map.replace(key, oldValue, newValue);
                } else if(operation.equalsIgnoreCase("clear")) {
                    map.clear();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map getMap() {
        return map;
    }
}
