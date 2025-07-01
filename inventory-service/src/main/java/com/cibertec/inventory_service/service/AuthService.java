package com.cibertec.inventory_service.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean esAdmin(String username, String password) {
        String url = "http://localhost:8088/api/admin";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password); // <-- autogenera el header Authorization
        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.Forbidden e) {
            return false; // No es admin
        } catch (HttpClientErrorException.Unauthorized e) {
            return false; // Credenciales inválidas
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}