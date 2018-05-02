package com.yong.mark.service.impl;

import com.yong.mark.repository.MarkRepository;
import com.yong.mark.service.MarkService;
import com.yong.mark.model.Mark;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author LiangYong
 * @date 2017/12/24
 */
@AllArgsConstructor
@Service
@Slf4j
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final SequenceServiceImpl sequenceService;

    @Override
    @Cacheable("testsdsd")
    public Mark findOneMark(String id) {
        log.debug("before findOneMark");
        Optional<Mark> opt = markRepository.findById(id);
//        Mark mark = new Mark();
//        mark.setId("1");
        log.debug("after findOneMark");
        checkArgument(opt.isPresent(), "mark not found!");
        return opt.get();
//        return mark;
    }

    @CachePut("testsdsd")
    public Mark findOneMarkLatest(String id){
        Optional<Mark> opt = markRepository.findById(id);
        checkArgument(opt.isPresent(), "mark not found!");
        return opt.get();
    }

    @Override
    public List<Mark> findAllMark() {
        return markRepository.findAll();
    }

    @Override
    public Mark saveMark(Mark mark) {
        return markRepository.insert(mark);
    }

    @Override
    public Mark updateMark(Mark mark) {
        checkArgument(mark.getId() != null, "update failure! Id can't be null!");
        Optional<Mark> old = markRepository.findById(mark.getId());
        checkArgument(old.isPresent(), "update failure! mark not found!");
        Mark result = Mark.updateMark(old.get(), mark);
        return markRepository.save(result);
    }
}
