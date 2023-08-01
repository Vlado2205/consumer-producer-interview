package sk.pohovor.avitech.domain.event;

import static sk.pohovor.avitech.domain.event.EventType.DELETE_ALL;

import lombok.Data;
import lombok.ToString;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
@Data
@ToString(callSuper = true)
public class DeleteAllUserEvent extends Event {
    public DeleteAllUserEvent() {
        super(DELETE_ALL);
    }
}
