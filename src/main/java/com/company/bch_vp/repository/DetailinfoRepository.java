package com.company.bch_vp.repository;

import com.company.bch_vp.entity.DetailInfo;
import com.company.bch_vp.entity.IdDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailinfoRepository extends JpaRepository<DetailInfo,Long> {
    DetailInfo findById(IdDetailInfo idDetailInfo);
}
