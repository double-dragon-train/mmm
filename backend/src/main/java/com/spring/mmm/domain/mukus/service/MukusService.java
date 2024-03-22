package com.spring.mmm.domain.mukus.service;

public interface MukusService {
    void selectRecentMukus(Long recommendFoodId);
    void getMukusMonth(Long groupId, Integer year, Integer month);
}
