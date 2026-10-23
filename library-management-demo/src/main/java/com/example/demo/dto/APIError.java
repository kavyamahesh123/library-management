package com.example.demo.dto;

public record APIError(int status, String error, String message, String path) {

}
