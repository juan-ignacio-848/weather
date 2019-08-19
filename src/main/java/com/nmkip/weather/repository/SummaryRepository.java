package com.nmkip.weather.repository;

import com.nmkip.weather.domain.Summary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends CrudRepository<Summary, Long> {
}
