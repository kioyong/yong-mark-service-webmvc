package com.yong.mark.service.impl;

import com.yong.mark.model.Patron;
import com.yong.mark.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronServiceImpl {

    @Autowired
    private PatronRepository patronRepository;


    public List<Patron> findAll() {
        return patronRepository.findAll();
    }

    public List<String> findWithoutScannedIdList() {
        return patronRepository.findPatronListWithoutScannedId();
    }
}
