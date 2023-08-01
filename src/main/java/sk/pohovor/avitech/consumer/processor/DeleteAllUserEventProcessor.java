package sk.pohovor.avitech.consumer.processor;

import sk.pohovor.avitech.domain.event.DeleteAllUserEvent;
import sk.pohovor.avitech.repository.UserRepository;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public class DeleteAllUserEventProcessor implements EventProcessor<DeleteAllUserEvent> {
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void processEvent(DeleteAllUserEvent event) {
        userRepository.deleteAll();
    }
}
