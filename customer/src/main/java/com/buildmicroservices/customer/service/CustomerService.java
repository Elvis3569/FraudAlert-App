package com.buildmicroservices.customer.service;

import com.buildmicroservice.amqp.RabbitMQMessageProducer;
import com.buildmicroservices.clients.fraud.FraudClient;
import com.buildmicroservices.clients.request.NotificationRequest;
import com.buildmicroservices.clients.response.FraudCheckResponse;
import com.buildmicroservices.customer.request.CustomerRegistrationRequest;
import com.buildmicroservices.customer.model.Customer;
import com.buildmicroservices.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private  final RabbitMQMessageProducer rabbitMQMessageProducer;


    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        //todo: check if email valid
        
        //todo: check if email is not taken

        //todo: store customer in db....done
        customerRepository.saveAndFlush(customer);
        
        //todo: check if customer is a fraudster
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraduster");
        }

        //todo: send notification


        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, Welcome...", customer.getId())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
//                internal.exchange
                "internal.notification.routing-key"
//                internal.notification.routing-key
        );


    }
}
