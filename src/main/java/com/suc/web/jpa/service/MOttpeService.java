package com.suc.web.jpa.service;

import java.util.List;
import com.suc.web.entity.MOttype;

public interface MOttpeService {
    MOttype findById(long id) throws Exception;

    MOttype findByName(String name) throws Exception;

    List<MOttype> findAll() throws Exception;
}
