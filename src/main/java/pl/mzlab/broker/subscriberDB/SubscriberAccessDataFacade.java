package pl.mzlab.broker.subscriberDB;

import org.springframework.stereotype.Service;
import pl.mzlab.broker.sharedmodel.Event;
import pl.mzlab.broker.sharedmodel.EventType;
import pl.mzlab.broker.sharedmodel.Result;
import pl.mzlab.broker.sharedmodel.Subscriber;

import java.util.List;

@Service
public class SubscriberAccessDataFacade {

    private SubscriberRepository subscriberRepository;

    public SubscriberAccessDataFacade(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public List<Subscriber> findSubscribersInterestedInSubject(String subject) {
        return subscriberRepository.findSubscribersInterestedInSubject(subject);
    }

    public Result registerSubscriberForFutureMessages(Subscriber subscriber) {
        Subscriber registeredSubscriber = subscriberRepository.registerSubscriber(subscriber);
        if (registeredSubscriber != null) {
            return Result.success(new Event(EventType.SUBSCRIBER_REGISTERED, registeredSubscriber.toString()));
        }
        return Result.failure(new Event(EventType.ERROR, subscriber.toString()));
    }
}
