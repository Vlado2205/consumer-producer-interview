package sk.pohovor.avitech.producer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import sk.pohovor.avitech.domain.event.AddUserEvent;
import sk.pohovor.avitech.domain.event.Event;
import sk.pohovor.avitech.domain.event.EventType;

/**
 * @author Vladimír Leško <a href="mailto:vladimir.lesko@openwise.cz">vladimir.lesko@openwise.cz</a>
 */
public class SampleProducerTest {

    @SneakyThrows
    @Test
    void testSampleProducer() {
        ExecutorService es = Executors.newCachedThreadPool();

        BlockingQueue<Event> blockingQueue = new ArrayBlockingQueue<>(100);
        SampleProducer sampleProducer = new SampleProducer(blockingQueue);

        es.execute(sampleProducer);

        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
        if (finished) {
            assertNotNull(blockingQueue);

            AddUserEvent addUserTestedEvent = (AddUserEvent) blockingQueue.take();
            assertEquals(addUserTestedEvent.getEventType(), EventType.ADD_USER);
            assertEquals(addUserTestedEvent.getUserId(), 1);
            assertEquals(addUserTestedEvent.getUserGuid(), "a1");
            assertEquals(addUserTestedEvent.getUserName(), "Robert");

            addUserTestedEvent = (AddUserEvent) blockingQueue.take();
            assertEquals(addUserTestedEvent.getEventType(), EventType.ADD_USER);
            assertEquals(addUserTestedEvent.getUserId(), 2);
            assertEquals(addUserTestedEvent.getUserGuid(), "a2");
            assertEquals(addUserTestedEvent.getUserName(), "Martin");

            Event testedEvent = blockingQueue.take();
            assertEquals(testedEvent.getEventType(), EventType.PRINT_ALL);

            testedEvent = blockingQueue.take();
            assertEquals(testedEvent.getEventType(), EventType.DELETE_ALL);

            testedEvent = blockingQueue.take();
            assertEquals(testedEvent.getEventType(), EventType.PRINT_ALL);

            testedEvent = blockingQueue.take();
            assertEquals(testedEvent.getEventType(), EventType.FINISH_CONSUMING);

            assertTrue(blockingQueue.isEmpty());

        }
    }
}
