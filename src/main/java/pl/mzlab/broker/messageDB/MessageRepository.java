package pl.mzlab.broker.messageDB;

import pl.mzlab.broker.sharedmodel.Message;

import java.util.List;

interface MessageRepository {

    Message add(Message message);

    List<Message> findBySubject(String subject);
}
