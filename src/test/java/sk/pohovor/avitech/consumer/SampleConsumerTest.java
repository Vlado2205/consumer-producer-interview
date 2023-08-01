package sk.pohovor.avitech.consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sk.pohovor.avitech.domain.event.AddUserEvent;
import sk.pohovor.avitech.domain.event.DeleteAllUserEvent;
import sk.pohovor.avitech.domain.event.Event;
import sk.pohovor.avitech.domain.event.FinishConsumingEvent;
import sk.pohovor.avitech.domain.event.PrintAllUserEvent;

/**
 * @author Vladimír Leško <a href="mailto:vladimir.lesko@openwise.cz">vladimir.lesko@openwise.cz</a>
 */
public class SampleConsumerTest {

    private final static BlockingQueue<Event> blockingQueue = new ArrayBlockingQueue<>(100);

    @SneakyThrows
    @BeforeAll
    static void initData() {
        blockingQueue.put(new AddUserEvent(1l, "userGuid", "userName"));
        blockingQueue.put(new PrintAllUserEvent());
        blockingQueue.put(new DeleteAllUserEvent());
        blockingQueue.put(new FinishConsumingEvent());
    }

    @SneakyThrows
    @Test
    void testSampleConsumer() {
        ExecutorService es = Executors.newCachedThreadPool();

        SampleConsumer sampleConsumer = new SampleConsumer(blockingQueue);
        es.execute(sampleConsumer);

        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
        if(finished) {
            assertTrue(blockingQueue.isEmpty());
        }
    }
}
