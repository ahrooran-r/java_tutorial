package tutorial.learn.apache_httpclient._1_request_execution;

import org.apache.commons.codec.CharEncoding;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.File;
import java.io.IOException;

public class _3_HttpEntity {

    /*
     * HTTP messages can carry a content entity associated with the request or response.
     *
     * Requests that use entities are referred to as entity enclosing requests.
     * The HTTP specification defines two entity enclosing request methods: POST and PUT.
     *
     * Responses are usually expected to enclose a content entity. But there are responses without entity as well.
     */

    /*
     * When the entity has been received with an incoming message, the methods HttpEntity#getContentType()
     * and HttpEntity#getContentLength() methods can be used for reading the common metadata
     * such as Content-Type and Content-Length headers (if they are available).
     * Since the Content-Type header can contain a character encoding for text mime-types like
     * text/plain or text/html, the HttpEntity#getContentEncoding() method is used to read this information.
     * If the headers aren't available, a length of -1 will be returned, and NULL for the content type.
     * If the Content-Type header is available, a Header object will be returned.
     */
    public static void main(String[] args) throws IOException, ParseException {

        HttpEntity myEntity = new StringEntity(
                "Hello World",
                ContentType.TEXT_PLAIN,
                CharEncoding.UTF_8,
                false
        );

        myEntity.getContentType(); // text/plain; charset=ISO-8859-1
        myEntity.getContentLength(); // 11


        /*
         * The recommended way to consume the content of an entity is by using its
         * HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods
         *
         * the use of EntityUtils is strongly discouraged unless the response entities
         * originate from a trusted HTTP server and are known to be of limited length.
         */
        EntityUtils.toString(myEntity); // Hello World

        // correct way of doing if length is unknown
        if (null != myEntity) {
            long len = myEntity.getContentLength();
            // if no entity is there length will be -1 -> so we need to account for this case
            if (len != -1 && len < 2048) {
                System.out.println(EntityUtils.toString(myEntity));
            } else {
                // Stream content out
            }
        }

        /*
         * In some situations it may be necessary to be able to READ ENTITY CONTENT MORE THAN ONCE.
         * In this case entity content must be buffered in some way
         */
        if (null != myEntity) {
            // The buffered entity is always repeatable.
            myEntity = new BufferedHttpEntity(myEntity);
        }

        // Creating entity for POST request
        File file = new File("somefile.txt");
        FileEntity entity = new FileEntity(file, ContentType.TEXT_PLAIN, CharEncoding.UTF_8);
        HttpPost httppost = new HttpPost("http://localhost/action.do");
        httppost.setEntity(entity);

        /*
        Other entities
            1. StringEntity
            2. ByteArrayEntity
            3. InputStreamEntity
            4. FileEntity
        */
    }
}
