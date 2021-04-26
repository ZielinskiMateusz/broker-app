package pl.mzlab.broker.messagepublisher;

import pl.mzlab.broker.sharedmodel.Message;
import pl.mzlab.broker.sharedmodel.Subscriber;

public interface Publisher {

    void publishMessage(Message message, Subscriber subscriber);

}
