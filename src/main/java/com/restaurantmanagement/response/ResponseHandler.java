package com.restaurantmanagement.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse( Object responseObj, HttpStatus status) {


        return new ResponseEntity<Object>(responseObj,status);
    }
}
