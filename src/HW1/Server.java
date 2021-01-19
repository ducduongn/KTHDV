package HW1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Server {
    private static int PORT_NUMBER = 8080;

    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

        System.out.println("WAITING FOR CLIENT CONNECTION...");
        Socket client = serverSocket.accept();
        System.out.println("SERVER has connected to CLIENT");

//        BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

        ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
        PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);

        try {
            while (true) {
                ArrayList <Integer> arrayList = (ArrayList<Integer>) objectInputStream.readObject();

                printWriter.println("The max value of the array is: " + findMax(arrayList) +
                        " and The min value of the array is: " + findMin(arrayList));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            client.close();
            serverSocket.close();
        }

    }

    public static Integer findMax(ArrayList<Integer> arrayList) {
        int maxValue = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++) {
            if (maxValue < arrayList.get(i))
                maxValue = arrayList.get(i);
        }
        return maxValue;
    }

    public static Integer findMin(ArrayList<Integer> arrayList) {
        int minValue = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++) {
            if (minValue > arrayList.get(i))
                minValue = arrayList.get(i);
        }
        return minValue;
    }

}
