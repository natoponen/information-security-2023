
public class Vigener {
    // Сдвиг. Для английского алфавита 97, для русского 1072
    private static int bias = 0;
    // Количество букв в алфавите. Для английского алфавита 26, для русского 33
    private static int letters = 0;

    public static void main(String[] args) {
        // Задаем свиг и количество букв для английского алфавита
        bias = 97;
        letters = 26;

        // Задаем сообщение и ключ
        String message = "test message for vigener encryption method";
        String key = "mathematics";

        // Кодируем сообщение и выводим его
        String encryptedMessage = encrypt(message, key);
        System.out.println(encryptedMessage);

        // Декодируем сообщение и выводим его
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println(decryptedMessage);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    // Метод для кодирования
    private static String encrypt(String text, String key) {
        StringBuilder encrypt = new StringBuilder();
        int keyLen = key.length();
        // Для каждого символа в сообщении
        for (int i = 0; i < text.length(); i++) {
            // Пропускаем пробелы
            if (text.charAt(i) == ' ') {
                encrypt.append(' ');
                continue;
            }
            // Находим символ, сдвинутый вправо на соответствующий номер символа ключа в алфавите, дописываем его в результат
            encrypt.append((char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * bias) % letters) + bias));
        }
        // Возвращаем результат
        return encrypt.toString();
    }

    // Метод для декодирования
    private static String decrypt(String cipher, String key) {
        StringBuilder decrypt = new StringBuilder();
        int keyLen = key.length();
        // Для каждого символа в шифре
        for (int i = 0; i < cipher.length(); i++) {
            // Пропускаем пробелы
            if (cipher.charAt(i) == ' ') {
                decrypt.append(' ');
                continue;
            }
            // Находим символ, сдвинутый влево на соответствующий номер символа ключа в алфавите, дописываем его в результат
            decrypt.append((char) (((cipher.charAt(i) - key.charAt(i % keyLen) + letters) % letters) + bias));
        }
        // Возвращаем результат
        return decrypt.toString();
    }
}
