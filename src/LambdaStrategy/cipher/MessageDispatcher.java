package LambdaStrategy.cipher;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MessageDispatcher {

    public void dispatch(List<Message> messages, EncryptionStrategy strategy, Predicate<Message> filter,
                         Consumer<String> sender){

        //klasyczna pętla zgodnie z zasadą zadania bez Stream API
        for (Message message : messages) {
            if (filter.test(message)) {
                String encrypted = strategy.encrypt(message.content());
                sender.accept("[" + message.id() + "] " + encrypted);
            }
        }
    }
}
