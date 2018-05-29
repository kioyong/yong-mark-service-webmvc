package com.yong.mark.controller;

import com.yong.mark.model.ActivityRequest;
import com.yong.mark.repository.MarkRepository;
import com.yong.mark.service.DemoService;
import com.yong.mark.vo.HotPlayerPatron;
import com.yong.mark.vo.HotPlayerSummary;
import com.yong.mark.vo.PatronValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangYong
 * @date 2017/12/24
 */
@RestController
@RequestMapping("/demo")
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


    @GetMapping("/demo/test")
    public String test(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        log.debug("Authorization = {}", authorization);
        return " return hello from mark service";
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

    @PostMapping("/activity")
    public List<String> getActivityId(@RequestBody ActivityRequest request) {
        List<String> ids = request.getTableIds();
        List<String> result = new ArrayList<>();
        ZonedDateTime fromDate= request.getFromDate();
        ZonedDateTime toDate = request.getToDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        while (fromDate.isBefore(toDate)){
            fromDate = fromDate.plusDays(1);
            String dateString = fromDate.format(formatter);
            for (String id: ids) {
                result.add(dateString.concat(id));
            }

        }
        return result;
    }

    @PostMapping("/group")
    public String getGroup(){
        log.debug("start get group");
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {

            throw new RuntimeException("get group failed");
        }
        log.debug("end get group");
        return "this is group return ";
    }
}
