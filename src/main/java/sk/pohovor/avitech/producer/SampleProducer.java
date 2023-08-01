package sk.pohovor.avitech.producer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import lombok.SneakyThrows;
import sk.pohovor.avitech.domain.event.AddUserEvent;
import sk.pohovor.avitech.domain.event.DeleteAllUserEvent;
import sk.pohovor.avitech.domain.event.Event;
import sk.pohovor.avitech.domain.event.FinishConsumingEvent;
import sk.pohovor.avitech.domain.event.PrintAllUserEvent;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public class SampleProducer implements Runnable {

    private BlockingQueue<Event> blockingQueue;

    public SampleProducer(BlockingQueue<Event> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @SneakyThrows(value = InterruptedException.class)
    @Override
    public void run() {
        Event addUser1 = new AddUserEvent(1l, "a1", "Robert");
        Event addUser2 = new AddUserEvent(2l, "a2", "Martin");
        Event printAll = new PrintAllUserEvent();
        Event deleteAll = new DeleteAllUserEvent();
        Event printAll2 = new PrintAllUserEvent();

        List<Event> events = Arrays.asList(addUser1, addUser2, printAll, deleteAll, printAll2);
        for (Event event : events) {
            System.out.println(Thread.currentThread().getName() + "[Producing] event " + event);
            blockingQueue.put(event);
        }
        blockingQueue.put(new FinishConsumingEvent());
    }
}