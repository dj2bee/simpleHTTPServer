import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RequestHandler implements HttpHandler {
    private static final int    INTERNAL_SERVER_ERROR = 500,
                                NOT_IMPLEMENTED = 501,
                                SERVICE_UNAVAILABLE = 503,
                                FORBIDDEN = 403,
                                NOT_FOUND = 404;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // TODO: Implement multithreading

        String method = httpExchange.getRequestMethod();

        switch (method) {
            case "GET":
                handleGet(httpExchange);
                break;
            case "POST":
                handlePost(httpExchange);
                break;
            case "PUT":

                break;
            case "DELETE":

                break;
            default:
                error(httpExchange, 501, "No valid HTTP-Request-Method");
        }
    }

    private void handlePost(HttpExchange httpExchange) throws IOException {
        String uri = httpExchange.getRequestURI().toString();

        switch (uri) {
            case "/":
                sendResponse(httpExchange, ContentProvider.index());
                break;
            case "/foo":
                sendResponse(httpExchange, ContentProvider.foo());
                break;
            default:
                error(httpExchange, NOT_FOUND, "Ressource not found.");
        }
    }

    private void handleGet(HttpExchange httpExchange) throws IOException {
        String uri = httpExchange.getRequestURI().toString();

        switch (uri) {
            case "/":
                sendResponse(httpExchange, ContentProvider.index());
                break;
            case "/foo":
                sendResponse(httpExchange, ContentProvider.foo());
                break;
            default:
                error(httpExchange, NOT_FOUND, "Ressource not found.");
        }
    }

    private void sendResponse(HttpExchange httpExchange, String msg) throws IOException {
        httpExchange.sendResponseHeaders(200, msg.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(msg.getBytes());
        outputStream.close();
    }

    private void error(HttpExchange httpExchange, int status, String msg) throws IOException {
        msg = "<html><body><h1>" + status + "</h1>" + msg + "</body></html>";
        httpExchange.sendResponseHeaders(status, msg.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(msg.getBytes());
        outputStream.close();
    }
}
