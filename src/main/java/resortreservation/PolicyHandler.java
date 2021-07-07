package resortreservation;

import resortreservation.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired ResortRepository resortRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCanceled_ResortStatusChangePolicy(@Payload ReservationCanceled reservationCanceled){

        if(!reservationCanceled.validate()) return;

        System.out.println("\n\n##### listener ResortStatusChangePolicy : " + reservationCanceled.toJson() + "\n\n");

        // Sample Logic //
        Resort resort = new Resort();
        resortRepository.save(resort);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
