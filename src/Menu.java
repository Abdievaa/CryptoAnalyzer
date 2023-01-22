import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Select an action by entering its number\n" +
                    "1. Encrypt text in a file with a key\n" +
                    "2. Decrypt text in a file with a key \n" +
                    "3. Pick up the key to the ciphertext in the file\n" +
                    "4. Decrypt text in file using static enumeration\n" +
                    "5. To exit the program");

            String answer = console.readLine();

            switch (answer) {
                case ("1") -> new EncryptedDecrypted().encryptedDecrypted(true);
                case ("2") -> new EncryptedDecrypted().encryptedDecrypted(false);
                case ("3") -> new BruteForce().bruteForce();
                case ("4") -> new Parsing().parse();
                case ("5") -> {return;}
            }


        }


    }
}