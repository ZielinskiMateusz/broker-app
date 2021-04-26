package pl.mzlab.broker.messageDB;

import org.springframework.stereotype.Repository;
import pl.mzlab.broker.sharedmodel.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
class InMemoryMessageRepository implements MessageRepository{

    private Map<String, List<Message>> messagesBySubjectDB = new HashMap<>();

    public Message add(Message message){
        List<Message> messagesBySubject = messagesBySubjectDB.get(message.subject());
        boolean messagesExists = messagesBySubject != null;
        if(messagesExists){
            messagesBySubject.add(message);
        } else {
            messagesBySubjectDB.put(message.subject(), Arrays.asList(message));
        }
        return message;
    }

    public List<Message> findBySubject(String subject){
        List<Message> messagesBySubject = messagesBySubjectDB.get(subject);
        return messagesBySubject;
    }

}
