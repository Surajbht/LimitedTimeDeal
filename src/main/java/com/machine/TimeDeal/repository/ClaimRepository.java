package com.machine.TimeDeal.repository;

import com.machine.TimeDeal.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Claim findByDealIdAndClaimedBy(Long dealId, Long userId);
}
