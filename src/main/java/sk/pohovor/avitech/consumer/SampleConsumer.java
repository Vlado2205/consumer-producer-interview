package sk.pohovor.avitech.consumer;

import java.util.concurrent.BlockingQueue;

import lombok.AllArgsConstructor;
import sk.pohovor.avitech.configuration.HibernateConfiguration;
import sk.pohovor.avitech.consumer.factory.ConsumerFactory;
import sk.pohovor.avitech.domain.event.Event;
import sk.pohovor.avitech.domain.event.EventType;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
@AllArgsConstructor
public class SampleConsumer implements Runnable {
    private BlockingQueue<Event> blockingQueue;
    private final ConsumerFactory consumerFactory = new ConsumerFactory();

    @Override
    public void run() {
        while (true) {
            try {
                Event event = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + " [Consuming] event :" + event);
                if (event.getEventType() == EventType.FINISH_CONSUMING) {
                    // last thread will close Hibernate session
                    System.out.println("No more events to consume. Closing connections and exiting application.");
                    HibernateConfiguration.getSessionFactory().close();
                    break;
                }
                consumerFactory.consume(event);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}