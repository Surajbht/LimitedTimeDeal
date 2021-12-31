package com.machine.TimeDeal.service.impl;

import com.machine.TimeDeal.dtos.DealDto;
import com.machine.TimeDeal.models.Claim;
import com.machine.TimeDeal.models.Deal;
import com.machine.TimeDeal.models.User;
import com.machine.TimeDeal.repository.ClaimRepository;
import com.machine.TimeDeal.repository.DealRepository;
import com.machine.TimeDeal.repository.UserRepository;
import com.machine.TimeDeal.service.TimeDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TimeDealServiceImpl implements TimeDealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public DealDto createDeal(DealDto dealDto) {
        validateDealRequest(dealDto);
        Deal deal = toDeal(dealDto);
        dealRepository.save(deal);
        return toDealDto(deal);
    }

    private Deal toDeal(DealDto dealDto) {
        Deal deal = new Deal();
        deal.setEndTime(dealDto.getEndTime());
        deal.setItemCount(0);
        deal.setMaxLimit(dealDto.getMaxLimit());
        deal.setName(dealDto.getName());
        deal.setPrice(dealDto.getPrice());
        deal.setStartTime(new Date());
        deal.setActive(true);
        return deal;
    }

    private DealDto toDealDto(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setEndTime(deal.getEndTime());
        dealDto.setItemCount(deal.getItemCount());
        dealDto.setMaxLimit(deal.getMaxLimit());
        dealDto.setName(deal.getName());
        dealDto.setPrice(deal.getPrice());
        dealDto.setStartTime(deal.getStartTime());
        dealDto.setActive(deal.isActive());
        return dealDto;
    }

    @Override
    public DealDto updateDeal(DealDto dealDto) {
//        validateDealUpdateRequest();
        Deal deal = dealRepository.findByDealIdAndIsActive(dealDto.getId(), true);
        if(Objects.isNull(deal)) {
            throw new IllegalArgumentException("Invalid request");
        }
        if(Objects.nonNull(dealDto.getEndTime())) {
            deal.setEndTime(dealDto.getEndTime());
        }
        if(Objects.nonNull(dealDto.getMaxLimit())) {
            deal.setMaxLimit(dealDto.getMaxLimit());
        }
        dealRepository.save(deal);
        return toDealDto(deal);
    }

    @Override
    public boolean endDeal(Long dealId) {
        Deal deal = dealRepository.findByDealIdAndIsActive(dealId, true);
        if(Objects.isNull(deal)) {
            throw new IllegalArgumentException("Invalid request");
        }
        deal.setActive(false);
        dealRepository.save(deal);
        return true;
    }

    @Override
    public boolean claimDeal(Long dealId, String email) {
        Deal deal = dealRepository.findByDealIdAndIsActive(dealId, true);
        if(Objects.isNull(deal)) {
            throw new IllegalArgumentException("Invalid request");
        }
        User user = userRepository.findByEmail(email);
        Claim claim = claimRepository.findByDealIdAndClaimedBy(dealId, user.getId());
        if(Objects.nonNull(claim)) {
            throw new IllegalArgumentException("Invalid request");
        }
        if(deal.getMaxLimit().compareTo(deal.getItemCount())==0) {
            throw new IllegalArgumentException("No item left in the deal");
        }
        claim = toClaim(dealId, user.getId());
        claimRepository.save(claim);
        deal.setItemCount(deal.getItemCount()+1);
        dealRepository.save(deal);
        return true;
    }

    private Claim toClaim(Long dealId, Long userId) {
        Claim claim = new Claim();
        claim.setDealId(dealId);
        claim.setClaimedBy(userId);
        claim.setClaimedAt(new Date());
        return claim;
    }

    private void validateDealRequest(DealDto dealDto) {
        if(Objects.isNull(dealDto)) {
            throw new IllegalArgumentException("Invalid request");
        }
        if(Objects.isNull(dealDto.getEndTime()) || dealDto.getEndTime().before(new Date())) {
            throw new IllegalArgumentException("Invalid request");
        }

        if(Objects.isNull(dealDto.getMaxLimit()) || dealDto.getMaxLimit()<=0) {
            throw new IllegalArgumentException("Invalid request");
        }

        if(Objects.isNull(dealDto.getPrice()) || dealDto.getPrice()<=0) {
            throw new IllegalArgumentException("Invalid request");
        }

        if(Objects.nonNull(dealDto.getId())) {
            throw new IllegalArgumentException("Invalid request");
        }
    }
}
