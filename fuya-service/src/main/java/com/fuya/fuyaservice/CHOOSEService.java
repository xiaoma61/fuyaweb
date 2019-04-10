package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.CHOOSE;
import com.fuya.fuyadao.model.AdminProblemAnswer;

import java.util.List;

public interface CHOOSEService {
    void save(CHOOSE choose);
    AdminProblemAnswer findByCHOOSEID(int id);
    void update(String achoose, String bchoose, String cchoose, String dchoose, String answer, int id);
    List<Object>findObjectByCHOOSEID(int id);
    List<Object>findAnswer();
}
