import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class API {

  private static final HttpClient client = HttpClient.newHttpClient();
  private static final String API_URL = "http://localhost:8000/loginAPIjs";
  private static final String API_URL_ANSWER = "http://localhost:8000/loginAPIjsAnswer";
  public static void postLoginNotFound() {
    String json = String.format(
        "NotFound"
    );
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL_ANSWER))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();
    try {
      HttpResponse<String> response =
          client.send(
              request,
              HttpResponse.BodyHandlers.ofString()
          );

      System.out.println("4. RESPONSE RECEIVED");
      System.out.println("Status: " + response.statusCode());
      System.out.println("Body: " + response.body());

    } catch (Exception e) {
      System.out.println("5. REQUEST FAILED");
      e.printStackTrace();
    }
  }
  public static void postLoginFound() {
    String json = String.format(
        "Found"
    );
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL_ANSWER))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();
    try {
      HttpResponse<String> response =
          client.send(
              request,
              HttpResponse.BodyHandlers.ofString()
          );

      System.out.println("4. RESPONSE RECEIVED");
      System.out.println("Status: " + response.statusCode());
      System.out.println("Body: " + response.body());

    } catch (Exception e) {
      System.out.println("5. REQUEST FAILED");
      e.printStackTrace();
    }
  }
  public static void postLogin(String id, String name, String password) {

    System.out.println("1. postLogin called");

    String json = String.format(
        "{\"id\":\"%s\",\"name\":\"%s\",\"password\":\"%s\",\"approved\":%s}",
        id, name, password, true
    );

    System.out.println("2. JSON: " + json);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

    System.out.println("3. Request built");

    try {
      HttpResponse<String> response =
          client.send(
              request,
              HttpResponse.BodyHandlers.ofString()
          );

      System.out.println("4. RESPONSE RECEIVED");
      System.out.println("Status: " + response.statusCode());
      System.out.println("Body: " + response.body());

    } catch (Exception e) {
      System.out.println("5. REQUEST FAILED");
      e.printStackTrace();
    }

    System.out.println("6. sendAsync called");
  }
  public static String getLogin() {
    System.out.println("1. getLogin called");
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL))
        .GET()
        .build();
    System.out.println("2. Request built");
    try {
      HttpResponse<String> response =
          client.send(
              request,
              HttpResponse.BodyHandlers.ofString()
          );
      System.out.println("Status: " + response.statusCode());
      return response.body();

    }catch (Exception e) {
      System.out.println("5. REQUEST FAILED");
      e.printStackTrace();
      return null;
    }
  }
}
