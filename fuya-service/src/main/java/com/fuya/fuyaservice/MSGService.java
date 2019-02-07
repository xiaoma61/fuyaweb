package com.fuya.fuyaservice;

import com.fuya.fuyadao.entity.MSG;

public interface MSGService {
    void save(MSG msg);
    MSG findByID(int id);
}
