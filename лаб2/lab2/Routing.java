import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Routing {
    public static void main(String[] args) {
        // Вводим начальные данные
        int n = 6;
        int m = 5;
        String message = "нельзя недооценивать противника";
        String password = "пароль";

        // Кодируем сообщение и выводим его для проверки
        String encryptedMessage = encrypt(n, m, password, message);
        System.out.println(encryptedMessage);

        // Расшифровываем сообщение и выводим для проверки
        String decryptedMessage = decrypt(encryptedMessage, n, m, password);
        System.out.println(decryptedMessage);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    // Метод для шифрования
    private static String encrypt(int n, int m, String password, String message) {
        // Убираем пробелы
        String preparedMessage = message.replaceAll(" ", "");
        // Добавляем символы, так чтобы последняя строка была длинной m
        int charactersToAdd = preparedMessage.length() % (n * m);
        preparedMessage = preparedMessage + "а".repeat(charactersToAdd);

        // Создаем таблицу, ключи которой упорядоченны по алфавитному порядку
        // В значения будем записывать строки столбцов таблицы для ключа
        Map<Character, String> encryptionTable = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            encryptionTable.put(password.charAt(i), getCharactersByPosition(preparedMessage, i, n, m));
        }

        // Возвращаем "склеенные" строки зашифрованной таблицы
        return String.join("", encryptionTable.values());
    }

    // Метод для получения столбца таблицы
    private static String getCharactersByPosition(String string, int position, int n, int m) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            result.append(string.charAt(i * n + position));
        }
        return result.toString();
    }

    // Метод расшифровки сообщения
    private static String decrypt(String encryptedMessage, int n, int m, String password) {
        // Подготовливаем таблицу для расшифровки
        char[] route = password.toCharArray();
        Arrays.sort(route);
        Map<Character, String> decryptionMap = new HashMap<>();
        for (int i = 0; i < password.length(); i++) {
            decryptionMap.put(route[i], encryptedMessage.substring(i * m, i * m + m));
        }

        // Проходим по таблице и воостанавливаем сообщение по полученной таблице расшифровки
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.append(decryptionMap.get(password.charAt(j)).charAt(i));
            }
        }

        return result.toString();
    }
}
