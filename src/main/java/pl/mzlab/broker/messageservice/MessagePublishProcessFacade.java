package pl.mzlab.broker.messageservice;

import org.springframework.stereotype.Service;
import pl.mzlab.broker.sharedmodel.Message;
import pl.mzlab.broker.sharedmodel.Result;

@Service
public class MessagePublishProcessFacade {

    MessagePublishProcessService messagePublishProcessService;

    public MessagePublishProcessFacade(MessagePublishProcessService messagePublishProcessService) {
        this.messagePublishProcessService = messagePublishProcessService;
    }

    public Result processNewMessagePublishing(Message message){
        return messagePublishProcessService.processNewMessagePublishing(message);
    }

}
