import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher cipher = new CaesarCipher();

    public void bruteForce() throws IOException {
        System.out.println("Enter the file path to decrypt it");
        String path = scanner.nextLine();
        Path result = PathHelper.buildFullName(path, "_bruteForcing");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(result)) {
            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>();
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
                list.add(string);
            }
            for (int i = 0; i < cipher.alphabetLength(); i++) {
                String decrypt = cipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    for (String string : list) {
                        String encrypt = cipher.decrypt(string, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Encrypt complite. Key = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) {
        boolean isValidate = false;
        String[] strings = text.split(" ");
        for (String word : strings) {
            if (word.length() > 28) {
                return false;
            }
        }
        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
            isValidate = true;
        }
        while (isValidate) {
            System.out.println(text.substring(0, Math.min(text.length(), 400)));
            System.out.println("Text is correct? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                System.out.println("Please press only y or n");
            }
        }
        return false;
    }
}
