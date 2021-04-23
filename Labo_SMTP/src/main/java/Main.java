import config.ConfigurationManager;

public class Main {
    public static void main(String[] args) {
        String[] messages = ConfigurationManager.getMessages();

        for (String message :
                messages) {
            System.out.println(message);
        }
    }
}
