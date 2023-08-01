package sk.pohovor.avitech.domain.event;

import static sk.pohovor.avitech.domain.event.EventType.ADD_USER;

import lombok.Data;
import lombok.ToString;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
@Data
@ToString(callSuper=true)
public class AddUserEvent extends Event {
    private Long userId;
    private String userGuid;
    private String userName;

    public AddUserEvent(Long userId, String userGuid, String userName) {
        super(ADD_USER);
        this.userId = userId;
        this.userGuid = userGuid;
        this.userName = userName;
    }
}
