import org.junit.*;
import recoverablehashmap.RecoveryManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestIntegerToStringMap {
    Map<Integer, String> map;
    HashMap<Integer, String> realMap;
    RecoveryManager mgr;
    String mapFileName = "/Users/ghoshabhi/Downloads/RecoverableHashMap/src/map.txt";
    String logFileName = "/Users/ghoshabhi/Downloads/RecoverableHashMap/src/log.txt";

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws  Exception {

    }

    @Before
    public void setUp() throws IOException {
        realMap = new HashMap<>();
        mgr = new RecoveryManager(realMap, mapFileName, logFileName);
        map = mgr.getMap();
    }

    @After
    public void tearDown() {
    }

    /**
     * Simple case where Map is not saved, just operations in log
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void recoverFromEmpty() throws IOException, ClassNotFoundException {
        map.put(1, "Sue");
        realMap = null;
        mgr.recover();
        map = mgr.getMap();
        assertEquals("Sue", map.get(1));
    }

    /**
     * Slightly more complex scenario where Map has been saved
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void recoverFromNonEmpty() throws IOException, ClassNotFoundException {
        map.put(1, "Sue");
        mgr.flush();
        map.put(2, "Bob");
        map = null;
        mgr.recover();
        map = mgr.getMap();
        assertEquals("Sue", map.get(1));
        assertEquals("Bob", map.get(2));
    }
}
