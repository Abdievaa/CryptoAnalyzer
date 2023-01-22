import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Parsing {
    private final Scanner scanner = new Scanner(System.in);

    private final Map<Character, Integer> mapPath = new HashMap<>();
    private final Map<Character, Integer> mapStatistic = new HashMap<>();
    private final Map<Character, Character> decrypted = new HashMap<>();

    public void parse() throws IOException {
        System.out.println("\n" + "Enter the full path to the file to decrypt it:");
        String path = scanner.nextLine();
        System.out.println("Enter the full path to the file to collect statistics:");
        String statistic = scanner.nextLine();
        Path encrypted = PathHelper.buildFullName(path, "_parsing");

        List<Map.Entry<Character, Integer>> listPath = mapToList(fillMapValues(mapPath, path));
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(fillMapValues(mapStatistic, statistic));

        if (listPath.size() <= listStatistic.size()) {
            for (int i = 0; i < listPath.size(); i++) {
                decrypted.put(listPath.get(i).getKey(), listStatistic.get(i).getKey());
            }
        } else {
            System.out.println("The size of the statistics file is insufficient for decryption, a file longer than the encrypted one is needed");
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             BufferedWriter writer = Files.newBufferedWriter(encrypted)) {
            while (reader.ready()) {
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                for (char encryptedChar : line.toCharArray()) {
                    Character decryptedChar = decrypted.get(encryptedChar);
                    builder.append(decryptedChar);
                }
                writer.write(builder + System.lineSeparator());
            }
        }
        System.out.println("The contents of the file were decrypted using statistical analysis.");

    }

    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line);
            }

            for (char aChar : builder.toString().toCharArray()) {
                if (!map.containsKey(aChar)) {
                    map.put(aChar, 1);
                } else {
                    map.put(aChar, map.get(aChar) + 1);
                }
            }
        }
        return map;
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<Character, Integer>> comparator = (o1, o2) -> o2.getValue() - o1.getValue();
        list.sort(comparator);
        return list;

    }
}
