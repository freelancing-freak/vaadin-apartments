package com.apartments.service.apartments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Value("${url.apartments:#{'http://localhost:8080/api/admin-panel/apartments'}}")
    private String URL;

    public List<Apartment> findAll(String filterText) {
        Object response;
        if (filterText == null || filterText.isBlank()) {
            response = restTemplate.getForObject(URL, Object.class);
        } else {
            response = restTemplate.getForObject(URL + "?filter_text=" + filterText, Object.class);
        }
        if (response == null) {
            throw new RuntimeException("Cannot fetch apartments");
        }
        return mapper.convertValue(response, new TypeReference<>() {
        });
    }

    public void save(Apartment apartment) {
        restTemplate.postForEntity(URL, apartment, Object.class);
    }

    public void update(Apartment apartment) {
        restTemplate.put(URL, apartment);
    }

    public void delete(Long id) {
        restTemplate.delete(URL + '/' + id);
    }
}
