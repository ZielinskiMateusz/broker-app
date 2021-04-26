package pl.mzlab.broker.subscriberDB;

import org.springframework.stereotype.Repository;
import pl.mzlab.broker.sharedmodel.Subscriber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
class InMemorySubscriberRepository implements SubscriberRepository {

    private Map<String,List<Subscriber>> subscribersBySubjectsDB = new HashMap<>();

    public Subscriber registerSubscriber(Subscriber subscriber){
        List<Subscriber> subscribers = subscribersBySubjectsDB.get(subscriber.subject());
        boolean subscribersForGivenSubjectExists = subscribers != null;
        if(subscribersForGivenSubjectExists){
            subscribers.add(subscriber);
        } else {
            subscribersBySubjectsDB.put(subscriber.subject(), Arrays.asList(subscriber));
        }
        return subscriber;
    }

    public List<Subscriber> findSubscribersInterestedInSubject(String subject){
        List<Subscriber> subscribers = subscribersBySubjectsDB.get(subject);
        return subscribers;
    }



}
