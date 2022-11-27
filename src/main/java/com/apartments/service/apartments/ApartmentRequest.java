package com.apartments.service.apartments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentRequest {

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
    private List<String> images;

    public static ApartmentRequest from(Apartment apartment) {
        ApartmentRequest request = new ApartmentRequest();
        request.setId(apartment.getId());
        request.setCreatedDate(apartment.getCreatedDate());
        request.setName(apartment.getName());
        request.setDescription(apartment.getDescription());
        request.setLocation(apartment.getLocation());
        request.setLocationSrc(apartment.getLocationSrc());
        request.setMeasurement(apartment.getMeasurement());
        request.setNumberOfRooms(apartment.getNumberOfRooms());
        request.setPrice(apartment.getPrice());
        request.setMainImage(apartment.getMainImage());
        setImages(apartment, request);
        return request;
    }

    private static void setImages(Apartment apartment, ApartmentRequest request) {
        List<String> images = new LinkedList<>();
        if (isNotNullAndBlank(apartment.getImage1())) images.add(apartment.getImage1());
        if (isNotNullAndBlank(apartment.getImage2())) images.add(apartment.getImage2());
        if (isNotNullAndBlank(apartment.getImage3())) images.add(apartment.getImage3());
        if (isNotNullAndBlank(apartment.getImage4())) images.add(apartment.getImage4());
        if (isNotNullAndBlank(apartment.getImage5())) images.add(apartment.getImage5());
        if (isNotNullAndBlank(apartment.getImage6())) images.add(apartment.getImage6());
        if (isNotNullAndBlank(apartment.getImage7())) images.add(apartment.getImage7());
        if (isNotNullAndBlank(apartment.getImage8())) images.add(apartment.getImage8());
        if (isNotNullAndBlank(apartment.getImage9())) images.add(apartment.getImage9());
        request.setImages(images);
    }

    private static boolean isNotNullAndBlank(String image) {
        return image != null && !Strings.isBlank(image);
    }
}
