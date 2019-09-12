package com.codegrind.analyzer.repository;

import com.codegrind.analyzer.model.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertsRepo extends CrudRepository<Alerts, String> {

    @Query("SELECT a FROM Alerts a WHERE a.travelId = :travelId")
    public Optional<Alerts> findByTravelId(@Param("travelId") String travelId);

    @Query("SELECT a FROM Alerts a WHERE a.travelId = :travelId AND a.alertStatus = :alertStatus")
    public Optional<Alerts> findStatusByTravelId(@Param("travelId") String travelId,
                                                 @Param("alertStatus") String travelStatus);
}
