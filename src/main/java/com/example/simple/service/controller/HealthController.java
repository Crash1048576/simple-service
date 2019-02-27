package com.example.simple.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

	private static HttpStatus healthHttpStatus = HttpStatus.OK;

	@Value("${version}")
	private String version;

	@Value("${color}")
	private String color;

	@GetMapping("/health")
	public ResponseEntity<String> health() {
		return new ResponseEntity<>(healthHttpStatus);
	}

	@PostMapping("/health")
	public String changeHealth(@RequestParam Integer statusCode) {
		HttpStatus newStatus = HttpStatus.resolve(statusCode);
		String response = String.format(
				"Service: color = %s, version = %s, changed health response status from %s to %s",
				color,
				version,
				healthHttpStatus,
				newStatus
		);
		healthHttpStatus = newStatus;

		return response;
	}

	@GetMapping("/death")
	public ResponseEntity<String> death() {
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}

}
