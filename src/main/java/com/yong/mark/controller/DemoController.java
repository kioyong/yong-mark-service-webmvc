package com.yong.mark.controller;

import com.yong.mark.repository.MarkRepository;
import com.yong.mark.service.DemoService;
import com.yong.mark.vo.HotPlayerPatron;
import com.yong.mark.vo.HotPlayerSummary;
import com.yong.mark.vo.PatronValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiangYong
 * @date 2017/12/24
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;
    @Autowired
    private MarkRepository markRepository;

    @Value("${yong.string}")
    public String testString;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return "hello from mark service webmvc!5222 552!" + name + authorization;
    }

    @GetMapping("/hello")
    public String hello() {
//        try {
//            Thread.sleep(100l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return " return hello from mark";
    }

    @GetMapping("/error1")
    public String error() {
        throw new RuntimeException("oh !~!@#!");
    }

    @GetMapping("/getconfig")
    public String getConfig() {
        log.debug("testString = {}", testString);
        return "testString = " + testString;
    }

    @GetMapping("/agg")
    public HotPlayerSummary testAggregate() {
        List<HotPlayerPatron> patronList = markRepository.findAggregateByCustom();
        HotPlayerSummary summary = new HotPlayerSummary();
        summary.setPatronList(patronList);
        long buyIn = patronList.stream().mapToLong(p -> p.getBuyIn()).sum();
        long dd = patronList.stream().mapToLong(p -> p.getDd()).sum();
        long hours = patronList.stream().mapToLong(p -> p.getHours()).sum();
        summary.setHours(hours);
        summary.setBuyIn(buyIn);
        summary.setDd(dd);
        return summary;
    }

    @GetMapping("/getLastValue")
    public List<PatronValue> testReduce() {
        return markRepository.findAggregateBuyReduce();
    }

    @GetMapping("/name/{name}")
    public String getName(@PathVariable(name = "name") String name) {
        return demoService.getName(name);
    }

    @GetMapping("/cache/{test}")
    public String getCache(@PathVariable String test) {
        return demoService.getCacheString(test);
    }


}
