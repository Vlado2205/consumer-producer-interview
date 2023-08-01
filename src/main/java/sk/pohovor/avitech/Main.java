package sk.pohovor.avitech;

import sk.pohovor.avitech.service.ProducerConsumer;
import sk.pohovor.avitech.service.SampleThreadProducerConsumer;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public class Main {
    public static void main(String... args) {
        ProducerConsumer sampleThreadProducerConsumer = new SampleThreadProducerConsumer();
        sampleThreadProducerConsumer.start();
    }
}
