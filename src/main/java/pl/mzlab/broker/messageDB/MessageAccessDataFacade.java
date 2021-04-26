package pl.mzlab.broker.messageDB;

import org.springframework.stereotype.Service;
import pl.mzlab.broker.sharedmodel.Event;
import pl.mzlab.broker.sharedmodel.EventType;
import pl.mzlab.broker.sharedmodel.Message;
import pl.mzlab.broker.sharedmodel.Result;

import java.util.List;

@Service
public class MessageAccessDataFacade {

    private MessageRepository messageRepository;

    public MessageAccessDataFacade(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findMessagesBySubject(String subject) {
        List<Message> messagesBySubject = messageRepository.findBySubject(subject);
        return messagesBySubject;
    }

    public Result addMessageForFutureClients(Message message) {
        Message addedMessage = messageRepository.add(message);
        if (addedMessage != null) {
            return Result.success(new Event(EventType.MESSAGE_ADDED, addedMessage.toString()));
        }
        return Result.failure(new Event(EventType.ERROR, message.toString()));
    }
}
