package tutorial.learn.collections.agrona;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.ControlledMessageHandler;
import org.agrona.concurrent.MessageHandler;
import org.agrona.concurrent.UnsafeBuffer;
import org.agrona.concurrent.ringbuffer.OneToOneRingBuffer;
import org.agrona.concurrent.ringbuffer.RingBufferDescriptor;

import java.nio.ByteBuffer;

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
        final UnsafeBuffer toSend = new UnsafeBuffer(ByteBuffer.allocateDirect(10));
        toSend.putStringWithoutLengthAscii(0, "012345679");

        //write the data
        boolean sentOk = ringBuffer.write(1, toSend, 0, 10);

    }

    // READING DATA IS SOMEWHAT LIKE THIS. THERE ARE 2 WAYS
    static class MessageCapture implements MessageHandler {
        @Override
        public void onMessage(int msgTypeId, MutableDirectBuffer buffer, int index, int length) {
            System.out.println("do something");
        }
    }

    class ControlledMessageCapture implements ControlledMessageHandler {
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
    }
}
