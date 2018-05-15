package recoverablehashmap;

import java.util.Map;

public class RecoveryManager<K,V> {
    private Map<K, V> map;
    private String mapFileName;
    private String logFileName;

    public RecoveryManager(Map<K, V> map, String mapFileName, String logFileName) {
        this.map = new RecoverableHashMap<>(mapFileName, logFileName);
        this.mapFileName = mapFileName;
        this.logFileName = logFileName;
    }

    public void flush() {
        throw new UnsupportedOperationException("flush() not supported yet!");
    }

    public void recover() {
        throw new UnsupportedOperationException("recover() not supported yet!");
    }

    public Map getMap() {
        return map;
    }
}
