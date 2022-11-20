package com.apartments.service.apartments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {

    private Long id;
    private String createdDate;
    private String name;
    private String description;
    private String location;
    private String locationSrc;
    private int measurement;
    private int numberOfRooms;
    private String price;
    private String mainImage;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String image7;
    private String image8;
    private String image9;
}
