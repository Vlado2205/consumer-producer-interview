package sk.pohovor.avitech.mapper;

import org.mapstruct.Mapper;
import sk.pohovor.avitech.domain.entity.User;
import sk.pohovor.avitech.domain.event.AddUserEvent;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
@Mapper
public interface UserEventMapper {

    User mapUserEventToUser(AddUserEvent addUserEvent);
}
