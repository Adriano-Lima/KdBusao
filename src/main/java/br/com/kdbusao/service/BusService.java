package br.com.kdbusao.service;

import br.com.kdbusao.controller.BusDao;
import br.com.kdbusao.model.Bus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class BusService {

    public List<Bus> getLoc(String linha){
        BusDao busDao = new BusDao();
        return busDao.localizacaoAtualBus(linha);
    }

}
