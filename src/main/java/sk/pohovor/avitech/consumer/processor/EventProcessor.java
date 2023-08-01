package sk.pohovor.avitech.consumer.processor;

import sk.pohovor.avitech.domain.event.Event;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public interface EventProcessor<T extends Event> {
    void processEvent(T event);
}
