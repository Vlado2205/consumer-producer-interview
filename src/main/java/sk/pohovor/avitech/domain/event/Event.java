package sk.pohovor.avitech.domain.event;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
@Data
@NoArgsConstructor
public class Event {

    private UUID eventUUID;
    private EventType eventType;

    public Event(EventType eventType) {
        if (eventUUID == null) {
            eventUUID = UUID.randomUUID();
        }
        this.eventType = eventType;
    }
}
