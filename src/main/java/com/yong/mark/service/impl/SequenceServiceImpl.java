package com.yong.mark.service.impl;

import com.yong.mark.repository.SequencesRepository;
import com.yong.mark.model.Sequences;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SequenceServiceImpl {

    @Autowired
    private SequencesRepository sequencesRepository;

    public String getNextSequence(String className){
        Optional<Sequences> opt = sequencesRepository.findById(className);
        if(opt.isPresent()){
            Sequences sequences = opt.get();
            sequences.setNumber(sequences.getNumber()+1);
            sequencesRepository.save(sequences);
            return sequences.getNumber()+"";
        }else{
            Sequences sequences = sequencesRepository.insert(new Sequences(className, 1));
            return sequences.getNumber()+"";
        }
    }
}
