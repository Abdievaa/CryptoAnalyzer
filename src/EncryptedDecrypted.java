import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptedDecrypted {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher cipher = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) throws IOException {
        System.out.printf("Enter file path to %s it" + System.lineSeparator(), flag ? "encrypt" : "decrypt");
        String path = scanner.nextLine();
        System.out.println("Enter key");
        int key = Integer.parseInt(scanner.nextLine());
        Path result = PathHelper.buildFullName(path, flag ? "_encrypted" : "_decrypted");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(result);
        ) {
            while (reader.ready()) {
                String line = reader.readLine();
                String encryptedDecrypted = flag ? cipher.encrypt(line, key) : cipher.decrypt(line, key);
                writer.write(encryptedDecrypted + System.lineSeparator());
            }
        }
        System.out.println("File content is encrypted");

    }


}

