package pl.mzlab.broker.subscriberAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mzlab.broker.sharedmodel.Result;
import pl.mzlab.broker.sharedmodel.Subscriber;
import pl.mzlab.broker.subscriberservice.SubscriberRegistrationProcessFacade;

@RestController
public class SubscriberRegistrationProcessAPI {

    private SubscriberRegistrationProcessFacade subscriberRegistrationProcessFacade;

    SubscriberRegistrationProcessAPI(SubscriberRegistrationProcessFacade subscriberRegistrationProcessFacade) {
        this.subscriberRegistrationProcessFacade = subscriberRegistrationProcessFacade;
    }

    @PostMapping("/subscribers")
    ResponseEntity<Result> registerSubscriber(Subscriber subscriber){
        Result result = subscriberRegistrationProcessFacade.processSubscriberRegistration(subscriber);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

