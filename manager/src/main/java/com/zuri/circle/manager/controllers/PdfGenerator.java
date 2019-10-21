package com.zuri.circle.manager.controllers;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.Request.DonationReport;
import com.zuri.circle.manager.services.GeneratePdfReportService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PdfGenerator {
	
	@Autowired
	GeneratePdfReportService generatePdfReportService;
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	 public ResponseEntity<InputStreamResource> donationReport(@RequestBody DonationReport donationReport) {
		
		ByteArrayInputStream bis = generatePdfReportService.generatedonationReport(donationReport);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=donationreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}

}
