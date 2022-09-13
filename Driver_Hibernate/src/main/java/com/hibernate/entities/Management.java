package com.hibernate.entities;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Management {
	Driver driver;

    Map<BusLine, Integer> assignedBuses;

    float totalDistance;
    public Management(Driver driver, Map<BusLine, Integer> assignedBuses) {
		super();
		this.driver = driver;
		this.assignedBuses = assignedBuses;
	}
    public void setTotalDistance() {
        if (assignedBuses == null || assignedBuses.isEmpty()) {
            this.setTotalDistance(0);
        }
        AtomicReference<Float> totalDistance = new AtomicReference<>((float) 0);
        this.assignedBuses.forEach((busLine, round) -> totalDistance.updateAndGet(v -> v + busLine.getDistance() * round));
        this.totalDistance = totalDistance.get();

    }
	
    
}
