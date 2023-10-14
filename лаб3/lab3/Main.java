public class Main {
    public static void main(String[] args) {
        Gamma gamma = new Gamma();
        String message = "testing gamma";

        String encryptedMessage = gamma.encrypt(message, 33);
        System.out.println(encryptedMessage);

        String decryptedMessage = gamma.decrypt(encryptedMessage, 33);
        System.out.println(decryptedMessage);
    }
}
