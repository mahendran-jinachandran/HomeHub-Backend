package com.homehub.core.house.repo;

import com.homehub.core.house.entity.HouseMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseMemberRepository extends JpaRepository<HouseMember, Long> {
}
