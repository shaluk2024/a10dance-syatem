package com.example.a10dance.persistence;

import com.example.a10dance.domain.MonthYearData;
import org.springframework.data.repository.CrudRepository;

public interface MonthYearDataRepository extends CrudRepository<MonthYearData, Long> {
}
