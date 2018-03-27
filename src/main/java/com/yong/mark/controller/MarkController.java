package com.yong.mark.controller;

import com.yong.mark.service.MarkService;
import com.yong.mark.model.Mark;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author LiangYong
 * @date 2017/12/24
 */

@RestController
@RequestMapping("/mark")
@Slf4j
public class MarkController {

    @Autowired
    private MarkService markService;

    @GetMapping("/{id}")
    private Mark getMark(@PathVariable("id") String id) {
        return markService.findOneMark(id);
    }

    @GetMapping("/cache/{id}")
    private Mark findOneMarkLatest(@PathVariable("id") String id) {
        return markService.findOneMarkLatest(id);
    }

    @GetMapping
    private List<Mark> getAllMark() {
        return markService.findAllMark();

    }

    @PostMapping
    private Mark saveMark(@RequestBody Mark mark) {
        log.info("start save Mark.");
        return markService.saveMark(mark);
    }

    @PutMapping
    private Mark updateMark(@RequestBody Mark mark) {
        log.debug("start update mark");
        return markService.updateMark(mark);
    }

}
