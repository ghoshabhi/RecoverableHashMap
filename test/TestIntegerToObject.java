import main.Person;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import recoverablehashmap.RecoveryManager;

public class TestIntegerToObject {
    Map<Integer, Person> map;
    HashMap<Integer, Person> realMap;
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

    @Test
    public void objectTestCase1() {
        Person abhi = new Person("Abhishek", "Ghosh");
        Person adi = new Person("Aditya", "Ghosh");
        Person baba = new Person("Tushar", "Ghosh");
        Person maa = new Person("Kalpana", "Ghosh");
        Person didi = new Person("Rintu", "Ghosh");
        map.put(1, abhi);
        map.put(2, adi);
        map.put(3, baba);
        mgr.flush();
        map.put(4, maa);
        map = null;
        mgr.recover();
        map = mgr.getMap();
        map.put(5, didi);
        assertEquals(5, map.size());
        assertEquals(abhi.toString(), map.get(1).toString());
        assertEquals(adi.toString(), map.get(2).toString());
        assertEquals(didi.toString(), map.get(5).toString());
    }

    @Test
    public void objectTest2() {
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
        assertNull(realMap);
        mgr.recover();
        map = mgr.getMap();
        assertEquals(abhi, (Person)map.get(1));
        assertNotEquals(adi, (Person)map.get(2));
        assertEquals(maa, (Person)map.get(2));
        assertNotEquals(baba, (Person)map.get(3));
        assertEquals(didi, (Person)map.get(3));
        mgr.flush();
    }
}
