import java.util.HashMap;

public class Atbash {
    public static void main(String[] args) {
        // Подготовливаем сообщение
        String testMessage = "checking the atbash code on real example";

        // Кодируем сообщение
        String atbashEncodedTestMessage = atbash(testMessage);

        // Выводим зашифрованное сообщение для проверки
        System.out.println("Encoded message: " + atbashEncodedTestMessage);

        // Расшифровываем сообщение
        String decodedTestMessage = atbash(atbashEncodedTestMessage);

        // Проверяем, что расшифрованное сообщение соответствует ожиданиям
        System.out.println("Decoded message: " + decodedTestMessage);
    }

    // ========================================================================================
    // Implementation
    // ========================================================================================

    // Таблица соответствия латинского алфавита с пробелом с шифром Атбаш
    private static final HashMap<Character, Character> ATBASH_TABLE = new HashMap<>(){{
        put('a', ' '); put('b', 'z'); put('c', 'y'); put('d', 'x'); put('e', 'w'); put('f', 'v');
        put('g', 'u'); put('h', 't'); put('i', 's'); put('j', 'r'); put('k', 'q'); put('l', 'p');
        put('m', 'o'); put('n', 'n'); put('o', 'm'); put('p', 'l'); put('q', 'k'); put('r', 'j');
        put('s', 'i'); put('t', 'h'); put('u', 'g'); put('v', 'f'); put('w', 'e'); put('x', 'd');
        put('y', 'c'); put('z', 'b'); put(' ', 'a');
    }};

    // Шифрование и расшифрование сообщения шифром Атбаш
    public static String atbash(String message)
    {
        StringBuilder result = new StringBuilder();

        // В цикле находим соответствующий символ в таблице и записываем в результат
        for(char letter : message.toCharArray()) {
            result.append(Character.toLowerCase(ATBASH_TABLE.get(letter)));
        }

        return result.toString();
    }
}
