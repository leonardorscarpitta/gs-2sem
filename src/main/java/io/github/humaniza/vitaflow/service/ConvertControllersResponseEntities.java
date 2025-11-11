package io.github.humaniza.vitaflow.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ConvertControllersResponseEntities {

    public HashMap<String, Object> manageStatus(Object responseObject, HttpStatusCode httpStatusCode) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status_code", httpStatusCode);
        response.put("object", responseObject);

        return response;
    }

}
