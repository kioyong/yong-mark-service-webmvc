package com.yong.mark.controller;

import com.yong.mark.model.Patron;
import com.yong.mark.service.impl.PatronServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/patron")
public class PatronController {

    private PatronServiceImpl patronService;

    @GetMapping("/all")
    public List<Patron> findAll(){
        return patronService.findAll();
    }


}
