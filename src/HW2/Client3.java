package HW2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Client3 {
    private static String SERVER_IP = "127.0.0.1";
    private static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {

        Socket server = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

//        PrintWriter printWriter = new PrintWriter(server.getOutputStream(), true);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());


        try {
            while (true) {
                System.out.print("Type number as size of array: ");
                int n = Integer.parseInt(keyboard.readLine());

                if (n == 0) break;

                ArrayList <Integer> arrayList = generateArray(n);
                System.out.println("Your random Interger array are: " + arrayList);
                objectOutputStream.writeObject(arrayList);

//                printWriter.println(arrayList);

                String serverResponse = input.readLine();
                System.out.println("Server response: " + serverResponse);
            }
        } finally {
            server.close();
            objectOutputStream.flush();
            System.exit(0);
        }

    }

    public static ArrayList<Integer> generateArray(int n){
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++){
            arrayList.add(random.nextInt(1000));
        }

        return arrayList;
    }
}

