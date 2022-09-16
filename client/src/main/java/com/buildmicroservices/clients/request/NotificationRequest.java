package com.buildmicroservices.clients.request;

public record NotificationRequest (
     Integer toCustomerId,
     String toCustomerEmail,
     String message
) {

}

