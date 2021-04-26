package pl.mzlab.broker.subscriberservice;

import org.springframework.stereotype.Service;
import pl.mzlab.broker.messageDB.MessageAccessDataFacade;
import pl.mzlab.broker.sharedmodel.*;
import pl.mzlab.broker.messagepublisher.PublishingService;
import pl.mzlab.broker.subscriberDB.SubscriberAccessDataFacade;

import java.util.LinkedList;
import java.util.List;

@Service
public class SubscriberRegistrationProcessService {

    private PublishingService publishingService;
    private MessageAccessDataFacade messageAccessDataFacade;
    private SubscriberAccessDataFacade subscriberAccessDataFacade;

    SubscriberRegistrationProcessService(PublishingService publishingService,
                                         MessageAccessDataFacade messageAccessDataFacade,
                                         SubscriberAccessDataFacade subscriberAccessDataFacade) {
        this.publishingService = publishingService;
        this.messageAccessDataFacade = messageAccessDataFacade;
        this.subscriberAccessDataFacade = subscriberAccessDataFacade;
    }

    Result processSubscriberRegistration(Subscriber subscriber) {
        List<Event> processSubscriberRegistrationEvents = new LinkedList<>();
        Result registrationResult = subscriberAccessDataFacade.registerSubscriberForFutureMessages(subscriber);
        processSubscriberRegistrationEvents.addAll(registrationResult.events());
        if (registrationResult.isSuccess()) {
            Result publishedMessagesResults = publishMessagesForSubscriber(subscriber);
            processSubscriberRegistrationEvents.addAll(publishedMessagesResults.events());
            return Result.success(processSubscriberRegistrationEvents);
        }
        return Result.failure(new Event(EventType.ERROR, subscriber.toString()));
    }

    Result publishMessagesForSubscriber(Subscriber subscriber) {
        List<Message> messagesBySubject = messageAccessDataFacade.findMessagesBySubject(subscriber.subject());
        boolean messagesWithGivenSubjectExists = messagesBySubject != null || !messagesBySubject.isEmpty();
        if (messagesWithGivenSubjectExists) {
            Result publishedMessagesResults = publishingService.publishMessagesToOneSubscriber(messagesBySubject, subscriber);
            return publishedMessagesResults;
        }
        return Result.failure(new Event(EventType.ERROR, subscriber.toString()));
    }


}
