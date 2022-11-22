package com.apartments.service.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private Long id;
    private String createdDate;
    private Long apartmentId;
    private String name;
    private String subject;
    private String phoneNumber;
    private String message;
    private String type;
    private String error;
    private boolean success;
}
