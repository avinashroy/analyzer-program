package com.codegrind.analyzer.repository;

import com.codegrind.analyzer.model.TravelInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TravelInfoRepo extends CrudRepository<TravelInfo, String> {

    @Query("SELECT t.travelId FROM TravelInfo t where t.travelStatus = :travelStatus")
    public List<String> getTravelIdForActiveTravels(@Param("travelStatus") String travelStatus);

    @Query(value = "SELECT t FROM TravelInfo t where t.travelStatus = :travelStatus")
    public Optional<TravelInfo> getActiveTravels(@Param("travelStatus") String travelStatus);
}
