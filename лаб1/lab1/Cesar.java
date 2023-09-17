public class Cesar {
    public static void main(String[] args) {
        // Подготовливаем сообщение
        String testMessage = "checking the cesar code on real example";

        // Кодируем сообщение с k = 3
        String cesarEncodedTestMessage = encode(testMessage, 3);

        // Выводим зашифрованное сообщение для проверки
        System.out.println("Encoded message: " + cesarEncodedTestMessage);

        // Расшифровываем сообщение
        String decodedTestMessage = decode(cesarEncodedTestMessage, 3);

        // Проверяем, что расшифрованное сообщение соответствует ожиданиям
        System.out.println("Decoded message: " + decodedTestMessage);
    }

    // ========================================================================================
    // Implementation
    // ========================================================================================

    // Шифрование сообщения шифром Цезаря с произвольным сдвигом offset
    private static String encode(String message, int offset) {
        // Проверяем, что сдвиг не выходит за пределы алфавита
        // Если сдвиг не попадает в рамки алфавита, возвращаем сообщение об ошибке
        if (offset < 1 || offset > 26) {
            return "Could not encode your message. Please check offset.";
        }

        StringBuilder result = new StringBuilder();

        // В цикле шифруем сообщение с помощью сдвига по таблице ASCII
        for (char character : message.toCharArray()) {
            // Пробелы не шифруются
            if (character != ' ') {
                // ASCII код буквы - ASCII код а
                int originalAlphabetPosition = character - 'a';
                // Находим смещение в зависимости от offset
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                // Достаем символ ASCII, прибавляя смещение
                char newCharacter = (char) ('a' + newAlphabetPosition);
                // Записываем в результат
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    // Расшифрование сообщения, зашифрованного шифром Цезаря, с заранее известным offset
    private static String decode(String encodedMessage, int offset) {
        // Сдвигаем так, чтобы алфавит оказался в начальном положении
        return encode(encodedMessage, 26 - (offset % 26));
    }
}
