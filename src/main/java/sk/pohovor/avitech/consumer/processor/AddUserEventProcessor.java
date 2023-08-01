package sk.pohovor.avitech.consumer.processor;

import org.mapstruct.factory.Mappers;
import sk.pohovor.avitech.domain.entity.User;
import sk.pohovor.avitech.domain.event.AddUserEvent;
import sk.pohovor.avitech.mapper.UserEventMapper;
import sk.pohovor.avitech.repository.UserRepository;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public class AddUserEventProcessor implements EventProcessor<AddUserEvent> {
    private final UserRepository userRepository = UserRepository.getInstance();
    private final UserEventMapper mapper = Mappers.getMapper(UserEventMapper.class);


    @Override
    public void processEvent(AddUserEvent event) {

        User user = mapper.mapUserEventToUser(event);

        userRepository.saveUser(user);

    }
}
