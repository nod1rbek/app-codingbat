package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.*;
import uz.pdp.app_codingbat.payload.JavaDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.repository.ArrayRepository;
import uz.pdp.app_codingbat.repository.JavaRepository;
import uz.pdp.app_codingbat.repository.LogicRepository;
import uz.pdp.app_codingbat.repository.WarmupRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JavaService {

    @Autowired
    JavaRepository javaRepository;
    @Autowired
    WarmupRepository warmupRepository;
    @Autowired
    LogicRepository logicRepository;
    @Autowired
    ArrayRepository arrayRepository;


    Set<Array> arraySet = new HashSet<>();
    Set<Warmup> warmupSet = new HashSet<>();
    Set<Logic> logicSet = new HashSet<>();
    public ResponseApi add(JavaDto javaDto) {
        Optional<Warmup> optionalWarmup = warmupRepository.findById(javaDto.getWarmupId());
        Optional<Logic> optionalLogic = logicRepository.findById(javaDto.getLogicId());
        Optional<Array> optionalArray = arrayRepository.findById(javaDto.getArrayId());
        if (optionalArray.isPresent() && optionalWarmup.isPresent() && optionalLogic.isPresent()) {
            Array array = optionalArray.get();
            arraySet.add(array);

            Warmup warmup = optionalWarmup.get();
            warmupSet.add(warmup);

            Logic logic = optionalLogic.get();
            logicSet.add(logic);

            Java java = new Java();
            java.setArray(arraySet);
            java.setLogic(logicSet);
            java.setWarmup(warmupSet);

            javaRepository.save(java);
            return new ResponseApi("Added", true);
        }
        return new ResponseApi("ERROR!", false);
    }

    public List<Java> getAll() {
        return javaRepository.findAll();
    }

    public Java getOne(Integer id) {
        Optional<Java> optionalJava = javaRepository.findById(id);
        return optionalJava.orElseGet(Java::new);
    }

    public ResponseApi edit(Integer id, JavaDto javaDto) {
        Optional<Java> optionalJava = javaRepository.findById(id);
        Optional<Warmup> optionalWarmup = warmupRepository.findById(javaDto.getWarmupId());
        Optional<Logic> optionalLogic = logicRepository.findById(javaDto.getLogicId());
        Optional<Array> optionalArray = arrayRepository.findById(javaDto.getArrayId());
        if (optionalJava.isPresent()) {
            if (optionalArray.isPresent() && optionalWarmup.isPresent() && optionalLogic.isPresent()) {
                Array array = optionalArray.get();
                arraySet.add(array);

                Warmup warmup = optionalWarmup.get();
                warmupSet.add(warmup);

                Logic logic = optionalLogic.get();
                logicSet.add(logic);

                Java java = optionalJava.get();

                javaRepository.save(java);
                return new ResponseApi("Edited", true);
            }
        }
        return new ResponseApi("ERROR!", false);
    }

    public ResponseApi delete(Integer id) {
        try {
            javaRepository.deleteById(id);
            return new ResponseApi("Deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR!", false);
        }
    }
}
