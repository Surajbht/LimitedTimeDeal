package com.machine.TimeDeal.controllers;

import com.machine.TimeDeal.dtos.DealDto;
import com.machine.TimeDeal.service.TimeDealService;
import com.machine.TimeDeal.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
public class TimeDealController {

    @Autowired
    private TimeDealService timeDealService;

    @Autowired
    private ValidateService validateService;

    @PostMapping("deal/{emailId}")
    public DealDto createDeal(@RequestBody DealDto dealDto, @PathVariable String emailId) {
        validateService.validateSeller(emailId);
        return timeDealService.createDeal(dealDto);
    }

    @PutMapping("deal/{emailId}")
    public DealDto updateDeal(@RequestBody DealDto dealDto, @PathVariable String emailId) {
        validateService.validateSeller(emailId);
        return timeDealService.updateDeal(dealDto);
    }

    @PutMapping("end-deal/{emailId}/{dealId}")
    public boolean createDeal(@PathVariable Long dealId, @PathVariable String emailId) {
        validateService.validateSeller(emailId);
        return timeDealService.endDeal(dealId);
    }

    @PostMapping("claim/{emailId}/{dealId}")
    public boolean claimDeal(@PathVariable Long dealId, @PathVariable String emailId) {
        validateService.validateBuyer(emailId);
        return timeDealService.claimDeal(dealId, emailId);
    }
}
