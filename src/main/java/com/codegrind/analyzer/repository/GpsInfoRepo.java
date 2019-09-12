package com.codegrind.analyzer.repository;

import com.codegrind.analyzer.model.GpsInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Iterator;
import java.util.List;

@Repository
public interface GpsInfoRepo extends CrudRepository<GpsInfo, String> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM gps_info WHERE travel_id = :travelId ORDER BY info_id DESC limit 10")
    public Iterable<GpsInfo> findLastTenRecords(@Param("travelId") String travelId);
}
