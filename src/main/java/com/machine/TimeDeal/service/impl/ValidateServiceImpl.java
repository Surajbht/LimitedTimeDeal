package com.machine.TimeDeal.service.impl;

import com.machine.TimeDeal.commons.UserType;
import com.machine.TimeDeal.models.User;
import com.machine.TimeDeal.repository.UserRepository;
import com.machine.TimeDeal.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validateSeller(String emailId) {
        User user = userRepository.findByEmail(emailId);
        if(Objects.isNull(user)) {
            throw new IllegalArgumentException("User not found");
        }
        if(!UserType.SELLER.equals(user.getUserType())) {
            throw new IllegalArgumentException("User is not authorized");
        }
    }

    @Override
    public void validateBuyer(String emailId) {
        User user = userRepository.findByEmail(emailId);
        if(Objects.isNull(user)) {
            throw new IllegalArgumentException("User not found");
        }
        if(!UserType.BUYER.equals(user.getUserType())) {
            throw new IllegalArgumentException("User is not authorized");
        }
    }
}
