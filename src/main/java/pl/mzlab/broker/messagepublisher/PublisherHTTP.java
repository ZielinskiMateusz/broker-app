package pl.mzlab.broker.messagepublisher;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.mzlab.broker.sharedmodel.Message;
import pl.mzlab.broker.sharedmodel.Subscriber;

@Service
class PublisherHTTP implements Publisher {

    public void publishMessage(Message message, Subscriber subscriber) {
        createWebClient(subscriber.address())
                .post().bodyValue(message)
                .retrieve()
                .bodyToMono(Void.class);
    }

    private WebClient createWebClient(String address) {
        return WebClient.builder()
                .baseUrl(address)
                .build();
    }

}
