package com.homehub.core.house.service;

import com.homehub.core.house.entity.House;
import com.homehub.core.house.entity.HouseMember;
import com.homehub.core.house.entity.HouseRole;
import com.homehub.core.house.repo.HouseMemberRepository;
import com.homehub.core.house.repo.HouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseService {

    private final HouseRepository houseRepository;
//    private final HouseMemberRepository houseMemberRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
//        this.houseMemberRepository = houseMemberRepository;
    }

    @Transactional
    public House createSingleHouse(Long organisationId, Long creatorUserId, String houseName) {
        House house = new House();
        house.setOrganisationId(organisationId);
        house.setName(houseName);
        house.setCreatedByUserId(creatorUserId);
        return houseRepository.save(house);
    }

    @Transactional
    public int bulkCreatePlaceholderHouses(Long organisationId, Long creatorUserId, int count) {
        for (int i = 1; i <= count; i++) {
            House h = new House();
            h.setOrganisationId(organisationId);
            h.setName("House " + i);
            h.setCreatedByUserId(creatorUserId);
            houseRepository.save(h);
        }
        return count;
    }
}
