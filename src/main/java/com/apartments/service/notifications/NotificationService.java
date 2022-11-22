package com.apartments.service.notifications;

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
public class NotificationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Value("${url.apartments:#{'http://localhost:8080/api/admin-panel/notifications'}}")
    private String URL;

    public List<Notification> findAll(String filterText) {
        Object response;
        if (filterText == null || filterText.isBlank()) {
            response = restTemplate.getForObject(URL, Object.class);
        } else {
            response = restTemplate.getForObject(URL + "?filter_text=" + filterText, Object.class);
        }
        if (response == null) {
            throw new RuntimeException("Cannot fetch notifications");
        }
        return mapper.convertValue(response, new TypeReference<>() {
        });
    }

    public void delete(Long id) {
        restTemplate.delete(URL + '/' + id);
    }
}
