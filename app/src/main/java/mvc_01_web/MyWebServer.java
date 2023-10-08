package mvc_01_web;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8000);
        System.out.println("Listening for connection on port 8000 ....");
        //load html file
        String myWebSite = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("app/src/main/html/index.html")));

        while (true) {
            try (Socket socket = server.accept())
            {
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Access-Control-Allow-Origin: *\r\n" + // Allow requests from any origin
                        "Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS\r\n" + // Specify allowed HTTP methods
                        "Access-Control-Allow-Headers: Origin, Content-Type, Accept, Authorization\r\n" + // Specify allowed headers
                        "\r\n" +
                        myWebSite;
                socket.getOutputStream() .write(httpResponse.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}

