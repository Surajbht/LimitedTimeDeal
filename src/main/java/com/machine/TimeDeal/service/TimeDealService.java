package com.machine.TimeDeal.service;

import com.machine.TimeDeal.dtos.DealDto;

public interface TimeDealService {
     DealDto createDeal(DealDto dealDto);

     DealDto updateDeal(DealDto dealDto);

     boolean endDeal(Long dealId);

     boolean claimDeal(Long dealId, String email);
}
