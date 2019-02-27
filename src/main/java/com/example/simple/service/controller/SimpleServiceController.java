package com.example.simple.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleServiceController {

	private static HttpStatus infoHttpStatus = HttpStatus.OK;

	@Value("${version}")
	private String version;

	@Value("${color}")
	private String color;

	@GetMapping("/ServiceInfo")
	public ResponseEntity<String> getServiceInfo() {
		String response = String.format("Service: color = %s, version = %s, status = %s", color, version, infoHttpStatus);

		return new ResponseEntity<>(response, infoHttpStatus);
	}

	@PostMapping("/ServiceInfo")
	public String changeServiceInfoResponseStatusCode(@RequestParam Integer statusCode) {
		HttpStatus newStatus = HttpStatus.resolve(statusCode);
		String response = String.format(
				"Service: color = %s, version = %s, changed info response status from %s to %s",
				color,
				version,
				infoHttpStatus,
				newStatus
		);
		infoHttpStatus = newStatus;

		return response;
	}



}
