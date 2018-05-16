package recoverablehashmap;

import java.util.*;

import static util.ReadWriteUtil.writeToFile;


public class RecoverableHashMap<K, V> implements Map<K, V> {
//    private String mapFileName;
    private String logFileName;
    private Map<K, V> map;

    public RecoverableHashMap(Map<K, V> map, String logFileName) {
        this.map = map;
        this.logFileName = logFileName;
//        this.mapFileName = mapFileName;
    }

    // return "value" or return null -> if file operation failed
    @Override
    public V put(K key, V value) {
        String operation = key + " " + value;
        boolean resultLog = writeToFile("PUT " + operation, logFileName);

        if (resultLog) {
            return map.put(key, value);
        }
        // remove the operation from the log file ??
        return null;
    }

    @Override
    public V remove(Object key) {
        boolean resultLog = writeToFile("REMOVE " + key, logFileName);

        if (resultLog) {
            return map.remove(key);
        }
        // remove the operation from the log file ??
        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (map.containsKey(key)) {
            String operation = "REPLACE KEY " + key + " OLD " + oldValue + " NEW " + newValue;
            boolean writeToLogFileResult = writeToFile(operation, logFileName);
            if (writeToLogFileResult) {
                return map.replace(key, oldValue, newValue);
            }
        }
        // remove the operation from the log file ??
        return false;
    }

    @Override
    public void clear() {
        String operation = "CLEAR";
        boolean writeToLogFileResult = writeToFile(operation, logFileName);
        if (writeToLogFileResult) {
            map.clear();
            return;
        }
        // remove the operation from the log file ??
        return;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
