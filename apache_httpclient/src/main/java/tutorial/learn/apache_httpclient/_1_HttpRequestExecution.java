package tutorial.learn.apache_httpclient;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class _1_HttpRequestExecution {
    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
    }
}
