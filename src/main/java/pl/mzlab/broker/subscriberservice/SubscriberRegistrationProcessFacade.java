package pl.mzlab.broker.subscriberservice;


import org.springframework.stereotype.Service;
import pl.mzlab.broker.sharedmodel.Result;
import pl.mzlab.broker.sharedmodel.Subscriber;

@Service
public class SubscriberRegistrationProcessFacade {

    private SubscriberRegistrationProcessService subscriberRegistrationProcessService;

    public SubscriberRegistrationProcessFacade(SubscriberRegistrationProcessService subscriberRegistrationProcessService) {
        this.subscriberRegistrationProcessService = subscriberRegistrationProcessService;
    }

    public Result processSubscriberRegistration(Subscriber subscriber) {
        return subscriberRegistrationProcessService.processSubscriberRegistration(subscriber);
    }

}
