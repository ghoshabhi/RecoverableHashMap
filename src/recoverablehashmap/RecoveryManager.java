package recoverablehashmap;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;

public class RecoveryManager { // extends AbstractMap implements Serializable
    private HashMap<Integer, String> map;
    private String mapFileName;
    private String logFileName;

    public RecoveryManager(HashMap<Integer,String> map, String mapFileName, String logFileName) {
        this.map = map;
        this.mapFileName = mapFileName;
        this.logFileName = logFileName;
    }

    public void flush() {
        throw new UnsupportedOperationException("flush() not supported yet!");
    }

    public void recover() {
        throw new UnsupportedOperationException("recover() not supported yet!");
    }

    public HashMap getMap() {
        return map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }

    public String getMapFileName() {
        return mapFileName;
    }

    public void setMapFileName(String mapFileName) {
        this.mapFileName = mapFileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
}
