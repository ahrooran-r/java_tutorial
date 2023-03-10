package tutorial.learn.collections.agrona;

import org.agrona.concurrent.IdGenerator;
import org.agrona.concurrent.SnowflakeIdGenerator;
import org.agrona.concurrent.SystemEpochClock;

public class _6_IdentityGenerator {
    public static void main(String[] args) {

        /*
         * To configure a basic Snowflake Identity Generator, you must provide a unique node Id.
         * This should be unique within the space of nodes generating identities in your system -
         * for example, if you have a system composed of 5 processes that are each generating values
         * with the Snowflake Identity Generator, then each of these should receive a unique node Id
         * — for example 1,2,3,4 and 5. The standard configuration of the Agrona Snowflake Identity Generator
         * supports at most 1024 nodes. This is a simplification of the original Twitter Snowflake algorithm,
         * which used 5 bits for a data center identity and 5 bits for a worker identity.
         */
        final long nodeId = 1L;

        /*
         * Note that Snowflake is able to generate unique identities for a period of 69 years. By default,
         * the Agrona library uses 1970 as the starting point, so identities will be extinguished in 2039.
         * There is an overloaded constructor that lets you specify your own offset, so you could set this to
         * 2021 and create unique identities until 2090.
         */
        final IdGenerator idGenerator = new SnowflakeIdGenerator(
                SnowflakeIdGenerator.NODE_ID_BITS_DEFAULT,
                SnowflakeIdGenerator.SEQUENCE_BITS_DEFAULT,
                nodeId,
                // offsets up to current time <- this actually we need to hardcode
                SystemEpochClock.INSTANCE.time(),
                SystemEpochClock.INSTANCE
        );

        final long nextId = idGenerator.nextId();

    }
}
