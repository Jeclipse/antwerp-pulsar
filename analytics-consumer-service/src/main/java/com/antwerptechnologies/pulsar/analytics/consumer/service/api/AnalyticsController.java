package com.antwerptechnologies.pulsar.analytics.consumer.service.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antwerptechnologies.pulsar.analytics.consumer.service.dto.AnalyticsResponseModel;
import com.antwerptechnologies.pulsar.analytics.consumer.service.service.AnalyticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = "application/vnd.api.v1+json")
public class AnalyticsController {

	private final AnalyticsService analyticsService;

	@GetMapping("/get-analytics-by-topic/{topic}")
	@Operation(summary = "Get analytics by topic.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success.", content = {
			@Content(mediaType = "application/vnd.api.v1+json", schema = @Schema(implementation = AnalyticsResponseModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Not found."),
			@ApiResponse(responseCode = "500", description = "Unexpected error.") })
	public ResponseEntity<List<AnalyticsResponseModel>> getAnalyticsByTrendingTopic(@PathVariable @NotEmpty String topic) {
		List<AnalyticsResponseModel> response = analyticsService.findByTrendingTopic(topic);

		log.info("Analytics data returned for trending topic {}", topic);
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/get-all-analytics")
	@Operation(summary = "Get all analytics.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success.", content = {
			@Content(mediaType = "application/vnd.api.v1+json", schema = @Schema(implementation = AnalyticsResponseModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Not found."),
			@ApiResponse(responseCode = "500", description = "Unexpected error.") })
	public ResponseEntity<List<AnalyticsResponseModel>> getAllAnalytics() {
		List<AnalyticsResponseModel> response = analyticsService.getAllAnalytics();

		log.info("All Analytics data returned");
		return ResponseEntity.ok(response);

	}
}
