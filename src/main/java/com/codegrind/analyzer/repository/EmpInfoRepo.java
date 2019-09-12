package com.codegrind.analyzer.repository;

import com.codegrind.analyzer.model.EmpInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpInfoRepo extends CrudRepository<EmpInfo, String> {
}
