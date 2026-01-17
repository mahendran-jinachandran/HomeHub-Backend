package com.homehub.core.house.service;

import com.homehub.core.house.entity.HouseMember;
import com.homehub.core.house.entity.HouseRole;
import com.homehub.core.house.repo.HouseMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseMemberService {

    private final HouseMemberRepository houseMemberRepository;

    public HouseMemberService(HouseMemberRepository houseMemberRepository) {
        this.houseMemberRepository = houseMemberRepository;
    }

    public HouseMember createHouseMembers(Long houseId,Long invitedByUserId, Long memberUserId, HouseRole role) {
        HouseMember hm = new HouseMember();
        hm.setHouseId(houseId);
        hm.setInvitedByUserId(invitedByUserId);
        hm.setUserId(memberUserId);
        hm.setRole(role);

        houseMemberRepository.save(hm);
        return  hm;
    }
}
