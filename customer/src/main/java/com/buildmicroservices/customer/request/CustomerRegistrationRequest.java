package com.buildmicroservices.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRegistrationRequest {
        private String firstName;
        private String lastName;
        private String email;
}
