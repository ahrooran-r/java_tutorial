package tutorial.learn.collections.agrona;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.ControlledMessageHandler;
import org.agrona.concurrent.MessageHandler;
import org.agrona.concurrent.UnsafeBuffer;
import org.agrona.concurrent.ringbuffer.OneToOneRingBuffer;
import org.agrona.concurrent.ringbuffer.RingBufferDescriptor;

import java.nio.ByteBuffer;
import java.util.HashSet;

public class _7_ConcurrentCollections {
    public static void main(String[] args) {

        /*
         * Storage for this ring buffer is defined upfront, and cannot be resized.
         * In the sample below, the underlying buffer is allocated off heap, and is set to accept
         * 4096 bytes of content. The addition of the RingBufferDescriptor.TRAILER_LENGTH is required
         * so that the data supporting the ring buffer is held within the same underlying buffer.
         * Note that any data written to the ring buffer via write has an additional 8 byte header
         * written with it, so be sure to keep this overhead in mind when sizing the underlying buffer.
         */
        final int bufferLength = 4096 + RingBufferDescriptor.TRAILER_LENGTH;
        final UnsafeBuffer internalBuffer = new UnsafeBuffer(ByteBuffer.allocateDirect(bufferLength));
        final OneToOneRingBuffer ringBuffer = new OneToOneRingBuffer(internalBuffer);

        // SENDING DATA IS DIRECT AND EASY

        //prepare some data
        final String testString = "0123456789";
        final UnsafeBuffer toSend = new UnsafeBuffer(ByteBuffer.allocateDirect(testString.length()));
        toSend.putStringWithoutLengthAscii(0, testString);

        //write the data
        boolean sentOk = ringBuffer.write(1, toSend, 0, testString.length());

        // ringBuffer provides the following two methods to show the current production and consumption.
        //the current consumer position in the ring buffer
        ringBuffer.consumerPosition();
        //the current producer position in the ring buffer
        ringBuffer.producerPosition();
    }

    // READING DATA IS SOMEWHAT LIKE THIS. THERE ARE 2 WAYS
    static class MessageCapture implements MessageHandler {
        private final HashSet<String> receivedStrings = new HashSet<>();
        private int count = 0;

        /**
         * msgType field is the identifier of the message and will be stored in the message header.
         * If this field is not used, it must be set to a value greater than 0.
         */
        @Override
        public void onMessage(int msgTypeId, MutableDirectBuffer buffer, int index, int length) {
            receivedStrings.add(buffer.getStringWithoutLengthAscii(index, length));
            count++;
        }
    }

    static class ControlledMessageCapture implements ControlledMessageHandler {
        @Override
        public ControlledMessageHandler.Action onMessage(int msgTypeId, MutableDirectBuffer buffer, int index, int length) {
            System.out.println("do something");
            return Action.COMMIT; //or ABORT, BREAK OR CONTINUE as required.

            /*
             * ABORT:       This aborts the read operation for this message. It will be delivered again on next read
             * BREAK:       This stops further processing after the current message for this read.
             * COMMIT:      Continues processing, but commits at the current message.
             * CONTINUE:    Continues processing, committing at the end of the current batch (this is equivalent to the standard handler).
             */
        }


        // OTHER TYPES:
        // https://aeroncookbook.com/agrona/concurrent/#broadcast
        // https://www.sobyte.net/post/2021-12/agrona/
    }
}
