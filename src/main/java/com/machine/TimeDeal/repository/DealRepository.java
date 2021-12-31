package com.machine.TimeDeal.repository;

import com.machine.TimeDeal.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
    Deal findByDealIdAndIsActive(Long id, boolean isActive);
}
