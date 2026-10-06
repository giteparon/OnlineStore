import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.nio.file.*;

public class Server {

  private static volatile String latestEventData = "{}";

  public static void main(String[] args) throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/loginAPIjs", exchange -> {
      if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
        String body = new String(exchange.getRequestBody().readAllBytes());

        System.out.println("[API] Received: " + body);

        latestEventData = body;

        String response = "{\"status\":\"ok\"}";

        exchange.getResponseHeaders().set(
            "Content-Type",
            "application/json"
        );

        exchange.sendResponseHeaders(200, response.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
          os.write(response.getBytes());
        }

      }
      else if(exchange.getRequestMethod().equalsIgnoreCase("GET")){
        System.out.println("[API] Sending: " + latestEventData);

        String response = latestEventData;

        exchange.getResponseHeaders().set(
            "Content-Type",
            "application/json"
        );

        exchange.sendResponseHeaders(200, response.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
          os.write(response.getBytes());
        }

      }
      else {
        exchange.sendResponseHeaders(405, -1);
      }
    });


    server.createContext("/", exchange -> {
      String path = exchange.getRequestURI().getPath();
      if (path.equals("/")) {
        path = "/index.html";
      }
      Path filePath = Paths.get("public" + path);
      if (Files.exists(filePath)) {
        byte[] bytes = Files.readAllBytes(filePath);
        String contentType = guessContentType(path);
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
          os.write(bytes);
        }

      } else {
        exchange.sendResponseHeaders(404, -1);
      }
    });
//    server.createContext("/loginAPI", exchange -> {
//      if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
//
//        String body = new String(exchange.getRequestBody().readAllBytes());
//
//        System.out.println("LOGIN REQUEST RECEIVED!");
//        System.out.println("Data: " + body);
//
//        String response = "{\"status\":\"ok\"}";
//
//        exchange.getResponseHeaders().set(
//            "Content-Type",
//            "application/json"
//        );
//
//        exchange.sendResponseHeaders(200, response.getBytes().length);
//
//        try (OutputStream os = exchange.getResponseBody()) {
//          os.write(response.getBytes());
//        }
//
//      } else {
//        exchange.sendResponseHeaders(405, -1);
//        exchange.close();
//      }
//    });

    server.setExecutor(null);
    server.start();
    System.out.println("Server running at http://localhost:8000");
  }

  private static String guessContentType(String path) {
    if (path.endsWith(".html"))
      return "text/html";
    if (path.endsWith(".css"))
      return "text/css";
    if (path.endsWith(".js"))
      return "application/javascript";

    return "application/octet-stream";
  }
}
