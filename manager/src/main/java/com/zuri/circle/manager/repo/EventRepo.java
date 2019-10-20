package com.zuri.circle.manager.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zuri.circle.manager.models.Donor;
import com.zuri.circle.manager.models.Events;

public interface EventRepo extends MongoRepository<Events, String>{

	public Events findByEventId(String id);
}
