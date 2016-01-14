import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/*
    author: dj2bee
    github: https://github.com/dj2bee
    date created: 2016-01-14
 */

public class SimpleHTTPServer {
    /*
        TODO: Build a library of this project!
     */

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null);
        server.start();
    }
}
