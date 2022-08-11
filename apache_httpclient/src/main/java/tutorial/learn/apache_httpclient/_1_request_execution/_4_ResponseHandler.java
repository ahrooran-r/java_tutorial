package tutorial.learn.apache_httpclient._1_request_execution;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.AbstractHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class _4_ResponseHandler {
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet url = new HttpGet("http://jsonplaceholder.typicode.com/todos/1");

        // For STRING, there is already a response handler built in
        HttpClientResponseHandler<String> stringResponseHandler = new BasicHttpClientResponseHandler();

        // OR we can create our own response handler
        HttpClientResponseHandler<JSONObject> jsonResponseHandler = new AbstractHttpClientResponseHandler<>() {
            @SneakyThrows
            @Override
            public JSONObject handleEntity(HttpEntity entity) throws IOException {

                if (entity == null) throw new ClientProtocolException("Response contains no content");

                // takes in an entity and converts to an actual object
                return new JSONObject(EntityUtils.toString(entity));
            }
        };

        // now call the handler
        JSONObject content = httpClient.execute(url, jsonResponseHandler);
    }
}
