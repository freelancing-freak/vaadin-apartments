package com.apartments.service.apartments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public static Apartment from(ApartmentResponse response) {
        Apartment apartment = new Apartment();
        apartment.setId(response.id());
        apartment.setCreatedDate(response.createdDate());
        apartment.setName(response.name());
        apartment.setDescription(response.description());
        apartment.setLocation(response.location());
        apartment.setLocationSrc(response.locationSrc());
        apartment.setMeasurement(response.measurement());
        apartment.setNumberOfRooms(response.numberOfRooms());
        apartment.setPrice(response.price());
        apartment.setMainImage(response.mainImage());
        setImages(apartment, response);
        return apartment;
    }

    private static void setImages(Apartment apartment, ApartmentResponse response) {
        List<String> images = response.images();
        if (images == null || images.isEmpty()) {
            return;
        }
        if (isIndexExists(0, images)) apartment.setImage1(images.get(0));
        if (isIndexExists(1, images)) apartment.setImage2(images.get(1));
        if (isIndexExists(2, images)) apartment.setImage3(images.get(2));
        if (isIndexExists(3, images)) apartment.setImage4(images.get(3));
        if (isIndexExists(4, images)) apartment.setImage5(images.get(4));
        if (isIndexExists(5, images)) apartment.setImage6(images.get(5));
        if (isIndexExists(6, images)) apartment.setImage7(images.get(6));
        if (isIndexExists(7, images)) apartment.setImage8(images.get(7));
        if (isIndexExists(8, images)) apartment.setImage9(images.get(8));
    }

    private static boolean isIndexExists(int index, List<String> images) {
        return images.size() > index;
    }
}
