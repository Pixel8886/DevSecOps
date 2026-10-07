import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);


        server.createContext("/health", exchange -> {
            String response = "{\"status\":\"OK\",\"service\":\"tasks-api\"}";
            sendJson(exchange, response);
        });


        server.createContext("/tasks", exchange -> {
            String response = "[" +
                    "{\"id\":1,\"title\":\"Сделать КТ по Docker\",\"done\":false}," +
                    "{\"id\":2,\"title\":\"Запушить в GitHub\",\"done\":false}," +
                    "{\"id\":3,\"title\":\"Проверить docker ps\",\"done\":true}" +
                    "]";
            sendJson(exchange, response);
        });

        server.start();
        System.out.println("Tasks API started on http://localhost:8080");
        System.out.println("Endpoints: /health, /tasks");
    }

    private static void sendJson(com.sun.net.httpserver.HttpExchange exchange, String body) throws java.io.IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}