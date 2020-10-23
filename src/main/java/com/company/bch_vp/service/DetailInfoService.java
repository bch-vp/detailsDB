package com.company.bch_vp.service;

import com.company.bch_vp.entity.Detail;
import com.company.bch_vp.entity.DetailInfo;
import com.company.bch_vp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailInfoService {
    void addDetail(Integer countOfDetail, Long idDetail, Long idProject);
    List<DetailInfo> findAll();
    Detail findDetailById(Long id);
    Project findProjectById(Long id);
    DetailInfo findDetailInfoById(Long id);
}