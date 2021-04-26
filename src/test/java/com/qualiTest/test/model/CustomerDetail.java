package com.qualiTest.test.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CustomerDetail {
    private String title;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String mobileNo;
    private String addressFirstLine;
    private String email;
    private String city;
    private String state;
    private String postcode;
}
