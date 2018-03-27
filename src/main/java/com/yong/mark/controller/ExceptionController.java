package com.yong.mark.controller;

import com.google.common.base.Throwables;
import com.yong.mark.model.ExceptionLog;
import com.yong.mark.model.Response;
import com.yong.mark.repository.ExceptionLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiangYong
 * @date 2017/12/24
 */
@RestControllerAdvice
@Slf4j
@ConfigurationProperties
public class ExceptionController {

    @Value("${yong.debug.enable}")
    private static boolean dataFlag;

    @Autowired
    private ExceptionLogRepository repository;

    @ExceptionHandler(Exception.class)
    public Response handleAllException(Exception ex) {
        log.debug("handle Exception!  ");
        List<Map<String, String>> list = Arrays.stream(Throwables.getRootCause(ex).getStackTrace())
            .filter(t ->
                t.getClassName().startsWith("com.yong") &&
                    t.getLineNumber() > 0
            )
            .map(t -> {
                Map<String, String> map = new HashMap<>(3);
                map.put("methodName", t.getMethodName());
                map.put("fileName", t.getFileName());
                map.put("lineNumber", t.getLineNumber() + "");
                return map;
            }).collect(Collectors.toList());
        log.error("handle exception {}", ex);
        ExceptionLog oldLog = repository.findByFirstDetail(list.get(0));
        if (oldLog != null) {
            oldLog.setCount(oldLog.getCount() + 1);
            oldLog.setLastModifiedDate(new Date());
            repository.save(oldLog);
        } else {
            ExceptionLog exceptionLog = new ExceptionLog(null, Throwables.getRootCause(ex).getMessage(), list, list.get(0), 1);
            repository.save(exceptionLog);
        }
        if (dataFlag) {
            return Response.fail(Throwables.getRootCause(ex).getMessage(), list);
        } else {
            return Response.fail(Throwables.getRootCause(ex).getMessage(), null);
        }
    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public void handleArgumentException(IllegalArgumentException ex) {
//        log.debug("also handle ArgumentException!");
//    }
}
