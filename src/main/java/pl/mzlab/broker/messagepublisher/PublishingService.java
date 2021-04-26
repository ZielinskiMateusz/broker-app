package pl.mzlab.broker.messagepublisher;

import org.springframework.stereotype.Service;
import pl.mzlab.broker.sharedmodel.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class PublishingService {

    private Publisher publisher;

    public PublishingService(Publisher publisher) {
        this.publisher = publisher;
    }

    public Result publishMessage(Message message, Subscriber subscriber) {
        if (message.isValid() && subscriber.isValid()) {
            publisher.publishMessage(message, subscriber);
            return Result.success(new Event(EventType.MESSAGE_PUBLISHED, message.toString()));
        }
        return Result.failure(new Event(EventType.ERROR,
                message.toString() + ";" + subscriber.toString()));
    }

    public Result publishMessagesToOneSubscriber(List<Message> messages, Subscriber subscriber) {
        if(subscriber.isValid()){
            List<Event> events = new LinkedList<>();
            messages.forEach(message -> {
                Result result = publishMessage(message, subscriber);
                events.addAll(result.events());
            });
            return Result.success(events);
        }
        return Result.failure(new Event(EventType.ERROR, subscriber.toString()));

    }

    public Result publishMessageToManySubscribers(Message message, List<Subscriber> subscribers) {
        if(message.isValid()){
            List<Event> events = new LinkedList<>();
            subscribers.forEach(subscriber -> {
                Result result = publishMessage(message, subscriber);
                events.addAll(result.events());
            });
            return Result.success(events);
        }
        return Result.failure(new Event(EventType.ERROR, message.toString()));
    }

}
