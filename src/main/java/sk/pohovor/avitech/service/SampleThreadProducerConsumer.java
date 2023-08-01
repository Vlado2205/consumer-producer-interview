package sk.pohovor.avitech.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Data;
import sk.pohovor.avitech.consumer.SampleConsumer;
import sk.pohovor.avitech.domain.event.Event;
import sk.pohovor.avitech.producer.SampleProducer;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
@Data
public class SampleThreadProducerConsumer implements ProducerConsumer {
    private final int queueSize = 1000;
    private final BlockingQueue<Event> blockingQueue = new ArrayBlockingQueue<>(queueSize);

    public void start() {
        ExecutorService producerExecutor = Executors.newSingleThreadExecutor();
        SampleProducer sampleProducer = new SampleProducer(blockingQueue);
        producerExecutor.execute(sampleProducer);

        ExecutorService consumerExecutor = Executors.newSingleThreadExecutor();
        SampleConsumer sampleConsumer = new SampleConsumer(blockingQueue);
        consumerExecutor.execute(sampleConsumer);

        producerExecutor.shutdown();
        consumerExecutor.shutdown();
    }

}
