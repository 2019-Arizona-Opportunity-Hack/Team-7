package com.zuri.circle.manager.controllers;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zuri.circle.manager.Request.DonationReport;
import com.zuri.circle.manager.models.Donations;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.repo.DonationRepo;
import com.zuri.circle.manager.repo.DonorRepo;
import com.zuri.circle.manager.services.GeneratePdfReportService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PdfGenerator {
	
	@Autowired
	GeneratePdfReportService generatePdfReportService;
	
	@Autowired
	DonorRepo donorRepo;
	@Autowired
	DonationRepo donationrepo;
	
	@RequestMapping(value = "/{id}/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	 public ResponseEntity<InputStreamResource> donationReport(@PathVariable String id) {
		Optional<Donor> donor = donorRepo.findById(id);
		List<DonationReport> donationReport = new ArrayList<>();
		if(donor.isPresent()) {
			Donor d = donor.get();
			for(String key : d.getDonationStat().keySet()) {
				Optional<Donations> don = donationrepo.findByDonationId(key);
				if(don.isPresent())
					donationReport.add(new DonationReport(d.getDonationStat().get(key), d.getUser().getFirstName().concat(" " ).concat(d.getUser().getLastName()),don.get().getDonationDate() ));
				
			}
			
		}
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
