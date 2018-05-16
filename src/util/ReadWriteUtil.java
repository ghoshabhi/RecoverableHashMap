package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadWriteUtil {
    public static boolean writeToFile(String text, String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            List<String> list = stream.collect(Collectors.toList());
            list.add(text);
            Files.write(Paths.get(filename), list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeToFile(List<String> lines, String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            Files.write(Paths.get(filename), lines);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
