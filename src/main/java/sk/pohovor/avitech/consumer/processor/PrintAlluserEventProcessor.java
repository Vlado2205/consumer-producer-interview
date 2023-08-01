package sk.pohovor.avitech.consumer.processor;

import sk.pohovor.avitech.domain.event.PrintAllUserEvent;
import sk.pohovor.avitech.repository.UserRepository;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public class PrintAlluserEventProcessor implements EventProcessor<PrintAllUserEvent> {
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void processEvent(PrintAllUserEvent event) {

        System.out.println("Printing all users: " + userRepository.getUsers());
    }
}
