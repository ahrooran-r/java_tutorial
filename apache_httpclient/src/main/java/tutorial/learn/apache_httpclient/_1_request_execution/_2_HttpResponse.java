package tutorial.learn.apache_httpclient._1_request_execution;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.core5.http.impl.EnglishReasonPhraseCatalog;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicHttpResponse;

import java.util.Locale;

@Slf4j
public class _2_HttpResponse {
    public static void main(String[] args) {

        // HTTP response is a message sent by the server back to the client
        // after having received and interpreted a request message.

        // Here I'm creating my own dummy response
        HttpResponse response = new BasicHttpResponse(HttpStatus.SC_OK, EnglishReasonPhraseCatalog.INSTANCE, Locale.UK);
        response.setVersion(HttpVersion.HTTP_1_1);

        // different ways of adding a header
        Header basicHeader = new BasicHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        response.addHeader(basicHeader);
        response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");

        // different info in response
        response.getVersion(); // HTTP/1.1
        response.getCode(); // 200
        response.getReasonPhrase(); // OK

        // can get all headers of same name
        Header[] headers = response.getHeaders("Set-Cookie");

        // we can use iterator to parse headers
        response.headerIterator("Set-Cookie").forEachRemaining(header -> {
            header.getName();
            header.getValue();
        });
    }
}
