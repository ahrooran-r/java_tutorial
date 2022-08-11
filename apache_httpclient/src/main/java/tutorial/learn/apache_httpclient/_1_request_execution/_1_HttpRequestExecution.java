package tutorial.learn.apache_httpclient._1_request_execution;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class _1_HttpRequestExecution {

    public static void main(String[] args) throws IOException, URISyntaxException {


        /*
         * HttpClient supports out of the box all HTTP methods defined in the
         * HTTP / 1.1 specification:
         * GET, HEAD, POST, PUT, DELETE, TRACE and OPTIONS.
         * There is a specific class for each method type.:
         * HttpGet, HttpHead, HttpPost, HttpPut, HttpDelete, HttpTrace, and HttpOptions.
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        try (httpClient) {


            /*
             * HttpClient supports out of the box all HTTP methods defined in the HTTP/1.1 specification:
             * GET, HEAD, POST, PUT, DELETE, TRACE and OPTIONS.
             * There is a specific class for each method type.: HttpGet, HttpHead, HttpPost, HttpPut,
             * HttpDelete, HttpTrace, and HttpOptions.
             */
            HttpGet url = new HttpGet("http://jsonplaceholder.typicode.com/todos/1");
            response = httpClient.execute(url);

            // Easily build URI with URIBuilder
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.google.com")
                    .setPath("/search")
                    .setParameter("q", "httpclient")
                    .setParameter("btnG", "Google Search")
                    .setParameter("aq", "f")
                    .setParameter("oq", "")
                    .build();

            // Easily build localhost uri
            URI localhostURI = URIBuilder.localhost().build();


        } finally {

            // In order to ensure proper release of system resources one must close
            // either the content stream associated with the entity
            response.getEntity().close();
            //  or the response itself
            response.close();

            /*
             *The difference between closing the content stream and closing the response is that
             * the former will attempt to keep the underlying connection alive by consuming the entity content
             * while the latter immediately shuts down and discards the connection.
             */
        }
    }
}
