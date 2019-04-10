package com.fuya.fuyaservice.impl;

import com.fuya.fuyadao.dao.CHOOSERepository;
import com.fuya.fuyadao.entity.CHOOSE;
import com.fuya.fuyadao.model.AdminProblemAnswer;
import com.fuya.fuyaservice.CHOOSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CHOOSEServiceImpl implements CHOOSEService {
    @Autowired
    CHOOSERepository chooseRepository;
    @Override
    public void save(CHOOSE choose) {
        chooseRepository.save(choose);

    }

    @Override
    public AdminProblemAnswer findByCHOOSEID(int id) {
        return chooseRepository.findByCHOOSEID(id);
    }

    @Override
    public void update(String achoose, String bchoose, String cchoose, String dchoose, String answer, int id) {
        chooseRepository.update(achoose, bchoose, cchoose, dchoose, answer, id);
    }

    @Override
    public List<Object> findObjectByCHOOSEID(int id) {
        return  chooseRepository.findObjectByCHOOSEID(id);
    }

    @Override
    public List<Object> findAnswer() {
        return chooseRepository.findAnswer();
    }

}
