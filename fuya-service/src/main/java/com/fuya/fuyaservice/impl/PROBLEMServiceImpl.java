package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.PROBLEMRepository;
import com.fuya.fuyadao.entity.PROBLEM;
import com.fuya.fuyadao.model.PROBLEMmodel;
import com.fuya.fuyaservice.PROBLEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PROBLEMServiceImpl implements PROBLEMService {


    @Autowired
    PROBLEMRepository problemRepository;
    @Override
    public void save(PROBLEM problem) {
        problemRepository.save(problem);
    }

    @Override
    public List<PROBLEM> findByTITLELike(String title) {
        return problemRepository.findByTITLELike(title);
    }

    @Override
    public Page<PROBLEM> findALL(int start, int rows) {

//        PageHelper.startPage(start,rows);
//        List<PROBLEM> problemList=problemRepository.findALL();
//        PageInfo<PROBLEM>problemAnswerPageInfo=new PageInfo<>(problemList);
//        return problemAnswerPageInfo;
        Pageable pageable =new PageRequest(start,rows);
        return problemRepository.findAll(pageable);
    }

    @Override
    public void deleteAllByPROMBLEIDandCHOOSEID(int MCHOOSEID, int PROMBLEID) {
        problemRepository.deleteAllByPROMBLEIDandCHOOSEID(MCHOOSEID, PROMBLEID);
    }

    @Override
    public void update(String title, String choosetype, String subjectmatier, int id) {
        problemRepository.update(title, choosetype, subjectmatier, id);
    }

    @Override
    public List<Object[]> findByNum(int nums) {
        return problemRepository.findByNum(nums);
    }
}
