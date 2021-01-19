package HW2;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket client;
    private ObjectInputStream objectInputStream;
//    private BufferedReader input;
    private PrintWriter printWriter;

    public ClientHandler (Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.objectInputStream = new ObjectInputStream(client.getInputStream());
//        this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.printWriter = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                ArrayList<Integer> arrayList = (ArrayList<Integer>) objectInputStream.readObject();
//                String n = input.readLine();
                printWriter.println("The max value of the array is: " + Server.findMax(arrayList) +
                        " and The min value of the array is: " + Server.findMin(arrayList));
//                printWriter.println(n);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter.close();
        }
    }
}
