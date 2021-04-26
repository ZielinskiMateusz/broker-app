package pl.mzlab.broker.messageAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mzlab.broker.messageservice.MessagePublishProcessFacade;
import pl.mzlab.broker.sharedmodel.Message;
import pl.mzlab.broker.sharedmodel.Result;

@RestController
class MessagePublishProcessAPI {

    private MessagePublishProcessFacade messagePublishProcessFacade;

    MessagePublishProcessAPI(MessagePublishProcessFacade messagePublishProcessFacade) {
        this.messagePublishProcessFacade = messagePublishProcessFacade;
    }

    @PostMapping("/messages")
    ResponseEntity<Result> addMessage(Message message){
        Result result = messagePublishProcessFacade.processNewMessagePublishing(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }
}
