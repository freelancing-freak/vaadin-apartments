package com.apartments.service.apartments;

import java.util.List;

public record ApartmentResponse(Long id,
                                String createdDate,
                                String name,
                                String description,
                                String location,
                                String locationSrc,
                                int measurement,
                                int numberOfRooms,
                                String price,
                                String mainImage,
                                List<String> images) {
}
