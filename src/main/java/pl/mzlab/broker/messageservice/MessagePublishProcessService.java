package pl.mzlab.broker.messageservice;

import org.springframework.stereotype.Service;
import pl.mzlab.broker.messageDB.MessageAccessDataFacade;
import pl.mzlab.broker.messagepublisher.PublishingService;
import pl.mzlab.broker.sharedmodel.*;
import pl.mzlab.broker.subscriberDB.SubscriberAccessDataFacade;

import java.util.LinkedList;
import java.util.List;

@Service
class MessagePublishProcessService {

    private SubscriberAccessDataFacade subscriberAccessDataFacade;
    private PublishingService publishingService;
    private MessageAccessDataFacade messageAccessDataFacade;

    MessagePublishProcessService(SubscriberAccessDataFacade subscriberAccessDataFacade,
                                 PublishingService publishingService,
                                 MessageAccessDataFacade messageAccessDataFacade) {
        this.subscriberAccessDataFacade = subscriberAccessDataFacade;
        this.publishingService = publishingService;
        this.messageAccessDataFacade = messageAccessDataFacade;
    }

    Result processNewMessagePublishing(Message message) {
        List<Event> processNewMessagePublishingEvents = new LinkedList<>();
        Result addedMessageResult = messageAccessDataFacade.addMessageForFutureClients(message);
        processNewMessagePublishingEvents.addAll(addedMessageResult.events());
        if (addedMessageResult.isSuccess()) {
            List<Subscriber> subscribersInterestedInSubject = subscriberAccessDataFacade.findSubscribersInterestedInSubject(message.subject());
            Result publishedMessageResult = publishingService.publishMessageToManySubscribers(message, subscribersInterestedInSubject);
            processNewMessagePublishingEvents.addAll(publishedMessageResult.events());
            return publishedMessageResult;
        }
        processNewMessagePublishingEvents.add(new Event(EventType.ERROR, message.toString()));
        return Result.failure(processNewMessagePublishingEvents);
    }


}
