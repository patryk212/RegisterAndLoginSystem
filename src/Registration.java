import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Registration {

    public static final String users = "users.properties";
    public static HashMap<String, String> accounts = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadUsers();

        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (accounts.containsKey(name)) {
                System.out.println(" account with that name already exists !");
                continue;
            }
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            accounts.put(name, password);
            System.out.println("Account created !");
            saveUsers();
            break;
        }


    }

        public static void saveUsers() {
            try {
                FileOutputStream fileOut = new FileOutputStream(users);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(accounts);
                out.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
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
    }





