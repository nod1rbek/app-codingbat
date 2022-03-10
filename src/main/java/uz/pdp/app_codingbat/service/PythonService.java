package uz.pdp.app_codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_codingbat.entity.*;
import uz.pdp.app_codingbat.payload.PythonDto;
import uz.pdp.app_codingbat.payload.ResponseApi;
import uz.pdp.app_codingbat.repository.*;
import uz.pdp.app_codingbat.entity.List;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PythonService {

    @Autowired
    PythonRepository pythonRepository;
    @Autowired
    WarmupRepository warmupRepository;
    @Autowired
    LogicRepository logicRepository;
    @Autowired
    ListRepository listRepository;

    Set<uz.pdp.app_codingbat.entity.List> listSet = new HashSet<>();
    Set<Warmup> warmupSet = new HashSet<>();
    Set<Logic> logicSet = new HashSet<>();
    public ResponseApi add(PythonDto pythonDto) {
        Optional<Warmup> optionalWarmup = warmupRepository.findById(pythonDto.getWarmupId());
        Optional<Logic> optionalLogic = logicRepository.findById(pythonDto.getLogicId());
        Optional<List> optionalList = listRepository.findById(pythonDto.getListId());
        if (optionalList.isPresent() && optionalWarmup.isPresent() && optionalLogic.isPresent()) {
            List list = optionalList.get();
            listSet.add(list);

            Warmup warmup = optionalWarmup.get();
            warmupSet.add(warmup);

            Logic logic = optionalLogic.get();
            logicSet.add(logic);

            Python python = new Python();
            python.setList(listSet);
            python.setLogic(logicSet);
            python.setWarmup(warmupSet);

            pythonRepository.save(python);
            return new ResponseApi("Added", true);
        }
        return new ResponseApi("ERROR!", false);
    }

    public java.util.List<Python> getAll() {
        return pythonRepository.findAll();
    }

    public Python getOne(Integer id) {
        Optional<Python> optionalPython = pythonRepository.findById(id);
        return optionalPython.orElseGet(Python::new);
    }

    public ResponseApi edit(Integer id, PythonDto pythonDto) {
        Optional<Python> optionalPython = pythonRepository.findById(id);
        Optional<Warmup> optionalWarmup = warmupRepository.findById(pythonDto.getWarmupId());
        Optional<Logic> optionalLogic = logicRepository.findById(pythonDto.getLogicId());
        Optional<List> optionalList = listRepository.findById(pythonDto.getListId());
        if (optionalPython.isPresent()) {
            if (optionalList.isPresent() && optionalWarmup.isPresent() && optionalLogic.isPresent()) {
                List list = optionalList.get();
                listSet.add(list);

                Warmup warmup = optionalWarmup.get();
                warmupSet.add(warmup);

                Logic logic = optionalLogic.get();
                logicSet.add(logic);

                Python python = optionalPython.get();

                pythonRepository.save(python);
                return new ResponseApi("Edited", true);
            }
        }
        return new ResponseApi("ERROR!", false);
    }

    public ResponseApi delete(Integer id) {
        try {
            pythonRepository.deleteById(id);
            return new ResponseApi("Deleted", true);
        } catch (Exception e) {
            return new ResponseApi("ERROR!", false);
        }
    }
}
