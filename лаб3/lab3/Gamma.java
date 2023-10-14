import java.util.ArrayList;

public class Gamma {
    private final ArrayList<Character> alphabet = new ArrayList<>();
    private final int alphabetSize;

    public Gamma() {
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabet.add(symbol);
        }
        alphabetSize = alphabet.size();
    }

    public String encrypt(String text, int key) {
        StringBuilder cryptogram = new StringBuilder();

        key = key % alphabetSize;
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbol == ' ') {
                cryptogram.append(" ");
            } else {
                int index = alphabet.indexOf(symbol);
                index = xor(index, random(key, i)) % alphabetSize;
                cryptogram.append(alphabet.get(index));
            }
        }
        return cryptogram.toString();
    }

    public String decrypt(String text, int key) {
        return encrypt(text, key);
    }

    private int random(int number, int count) {
        int[] numbers = new int[]{5, 67, 21, 76, 13, 86, 32, 87, 3, 98, 21,
            9, 11, 54, 94, 1, 4, 7, 55, 44, 32, 95, 33, 22, 64, 87, 30, 39,
            65};
        return numbers[(number * count) % numbers.length] % alphabet.size();
    }

    private int xor(int a, int b) {
        return a ^ b;
    }
}
