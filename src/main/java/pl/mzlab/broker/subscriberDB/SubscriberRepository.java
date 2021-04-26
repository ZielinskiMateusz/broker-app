package pl.mzlab.broker.subscriberDB;

import pl.mzlab.broker.sharedmodel.Subscriber;

import java.util.List;

interface SubscriberRepository {

    Subscriber registerSubscriber(Subscriber subscriber);

    List<Subscriber> findSubscribersInterestedInSubject(String subject);


}
