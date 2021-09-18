package com.arjun.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjun.entity.DemoEntity;
import com.arjun.pojo.DemoPojo;

@Repository
public interface DemoRepo extends JpaRepository<DemoEntity, Long>{

}
