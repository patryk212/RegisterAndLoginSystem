import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Login extends Registration {

    public static final String users = "users.properties";
    public static HashMap<String, String> accounts = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadUsers();

        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (!accounts.containsKey(name)) {
                System.out.println(" Account with that name does not exist !");
                continue;
            }
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (accounts.get(name).equals(password)) {
                System.out.println("Login successful !");
                System.out.println("Hello " + name + "!");
                System.out.println("1.Delete account");
                System.out.println("2.Logout");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    accounts.remove(name);
                    System.out.println("Account " + name + " has been deleted !");
                    saveUsers();
                    break;
                } else if (choice.equals("2")) {
                    break;
                }
            } else {
                System.out.println("Invalid password !");
            }
        }
    }


    public static void loadUsers() {
        try {
            FileInputStream inputStream = new FileInputStream(users);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            accounts = (HashMap<String, String>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("There are no accounts in the system");
        } else {
            for (String login : accounts.keySet()) {
                System.out.println("Login: " + login + " Password: " + accounts.get(login));
            }
        }
    }

    public static void saveUsers() {
        try {
            FileOutputStream outputStream = new FileOutputStream(users);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(accounts);
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
