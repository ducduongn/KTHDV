package HW2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int PORT_NUMBER = 8080;

    private static ArrayList <ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

        while (true) {
            System.out.println("WAITING FOR CLIENT CONNECTION...");
            Socket client = serverSocket.accept();
            System.out.println("SERVER has connected to CLIENT");

            ClientHandler clientHandler = new ClientHandler(client);
            clients.add(clientHandler);

            pool.execute(clientHandler);
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

