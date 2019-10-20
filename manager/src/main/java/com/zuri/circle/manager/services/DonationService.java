package com.zuri.circle.manager.services;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.zuri.circle.manager.exceptions.RegisterDonation_Exception;
import com.zuri.circle.manager.models.Donations;
import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.repo.DonationRepo;
import com.zuri.circle.manager.repo.DonorRepo;

@Service
public class DonationService {


	@Autowired
	DonorRepo donorRepo;
	@Autowired
	DonationRepo donationRepo;
	
	public void registerdonation(String id, String amount) throws RegisterDonation_Exception {
		// TODO Auto-generated method stub
		if(id != null) {
			try {
				String uuid = UUID.randomUUID().toString();
				Optional<Donor> donorOptional = donorRepo.findById(id);
				Donor donor = donorOptional.get();
				Donations donation = new Donations(uuid,donor,amount);
				donationRepo.insert(donation);
				Map<String, String> donorRecord = donor.getDonationStat();
				donorRecord.put(uuid, amount);
				int totalAmt = Integer.parseInt(donor.getTotalAmount()) + Integer.parseInt(amount);
				donor.setTotalAmount(Integer.toString(totalAmt));
				donorRepo.save(donor);
			}catch(MongoWriteException e) {
				throw new RegisterDonation_Exception("Error while writing the object to database." + e.getMessage());
			}
		}else {
			throw new RegisterDonation_Exception("The Donor object is null");
		}
	}

}
