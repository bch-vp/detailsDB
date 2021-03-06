package com.company.bch_vp.service.impl;

import com.company.bch_vp.entity.Detail;
import com.company.bch_vp.entity.DetailInfo;
import com.company.bch_vp.entity.IdDetailInfo;
import com.company.bch_vp.entity.Project;
import com.company.bch_vp.repository.DetailRepository;
import com.company.bch_vp.repository.DetailinfoRepository;
import com.company.bch_vp.repository.ProjectRepository;
import com.company.bch_vp.service.DetailInfoService;
import com.company.bch_vp.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import java.util.List;
import java.util.Optional;

@Service
public class DetailInfoServiceImpl implements DetailInfoService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private DetailinfoRepository detailinfoRepository;

    @Override
    public void addQuantityOfDetailsInProject(Integer quantity, Long idDetail, Long idProject) {
        DetailInfo detailInfo=detailinfoRepository.findById(new IdDetailInfo(idDetail,idProject));
        detailInfo.getDetail().subtractAvailableDetails(quantity);
        detailInfo.addQuantityofDetailsUsed(quantity);
        detailinfoRepository.flush();
    }

    @Override
    public void deleteProjectInDetail(Long idDetail, Long idProject){
        DetailInfo detailInfo=detailinfoRepository.findById(new IdDetailInfo(idDetail,idProject));
        detailInfo.getDetail().addAvailableDetails(detailInfo.getQuantityDetailsUsed());
        detailinfoRepository.delete(detailInfo);
    }

    @Override
    public void addDetail(Integer quantityDetailsUsed, Long idDetail, Long idProject) {
        Project project=projectRepository.findById(idProject).get();
        Detail detail=detailRepository.findById(idDetail).get();
        if(quantityDetailsUsed<=detail.getQuantityOfAvailable()){
            detail.subtractAvailableDetails(quantityDetailsUsed);
            DetailInfo detailInfo=new DetailInfo(quantityDetailsUsed,detail,project);
           detailinfoRepository.save(detailInfo);
        }
    }

    @Override
    public List<DetailInfo> findAll() {
        return detailinfoRepository.findAll();
    }

    @Override
    public DetailInfo findById(Long idDetail, Long idProject) {
        return detailinfoRepository.findById(new IdDetailInfo(idDetail, idProject));
    }

    @Override
    public void subtractQuantityOfDetailsInProject(Integer quantity, Long idDetail, Long idProject) {
        DetailInfo detailInfo=detailinfoRepository.findById(new IdDetailInfo(idDetail,idProject));
        detailInfo.getDetail().addAvailableDetails(quantity);
        detailInfo.subtractQuantityofDetailsUsed(quantity);
        detailinfoRepository.flush();
    }

    @Override
    public void sendChangesImmediately() {
        detailinfoRepository.flush();
    }
}
