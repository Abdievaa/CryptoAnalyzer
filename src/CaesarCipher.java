public class CaesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789";

    public int alphabetLength(){
        return ALPHABET.length();
    }


    public String encrypt(String message, int key) {//AIDA, 30
        StringBuilder builder = new StringBuilder();
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = ALPHABET.indexOf(chars[i]);
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char charAt = newIndex < 0 ? ALPHABET.charAt(newIndex + ALPHABET.length()) : ALPHABET.charAt(newIndex);
                builder.append(charAt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }
}
