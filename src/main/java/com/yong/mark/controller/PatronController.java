package com.yong.mark.controller;

import com.yong.mark.model.Patron;
import com.yong.mark.service.impl.PatronServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatronController {

    @Autowired
    private PatronServiceImpl patronService;

    @GetMapping("/all")
    public List<Patron> findAll(){
        return patronService.findAll();
    }

    @GetMapping("/scannedId")
    public List<String> findPatronList(){
        return patronService.findWithoutScannedIdList();
    }
}
