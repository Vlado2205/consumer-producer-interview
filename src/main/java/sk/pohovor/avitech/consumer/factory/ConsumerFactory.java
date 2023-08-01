package sk.pohovor.avitech.consumer.factory;

import java.util.HashMap;
import java.util.Map;

import sk.pohovor.avitech.consumer.processor.AddUserEventProcessor;
import sk.pohovor.avitech.consumer.processor.DeleteAllUserEventProcessor;
import sk.pohovor.avitech.consumer.processor.EventProcessor;
import sk.pohovor.avitech.consumer.processor.PrintAlluserEventProcessor;
import sk.pohovor.avitech.domain.event.Event;
import sk.pohovor.avitech.domain.event.EventType;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public class ConsumerFactory {
    private final Map<EventType, EventProcessor> eventProcessors = new HashMap<>();

    public ConsumerFactory() {
        eventProcessors.put(EventType.ADD_USER, new AddUserEventProcessor());
        eventProcessors.put(EventType.PRINT_ALL, new PrintAlluserEventProcessor());
        eventProcessors.put(EventType.DELETE_ALL, new DeleteAllUserEventProcessor());
    }

    public void consume(Event event) {
        if (event != null) {
            EventProcessor eventProcessor = eventProcessors.get(event.getEventType());
            if (eventProcessor != null) {
                eventProcessor.processEvent(event);
            } else {
                //Best option will be to throw NotImplemented exception from apache commons
                throw new UnsupportedOperationException("Unknown event processor for event type " + event.getEventType());
            }

        }
    }
}
