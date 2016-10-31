package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import br.com.kdbusao.model.PontoParada;
import br.com.kdbusao.service.BusServicePontosdeParada;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerPontosdeParada {

    @Autowired
    private BusServicePontosdeParada busServicePontosdeParada;

    @RequestMapping(path = "/pontosParada/{linha}", method = RequestMethod.GET)
    public List<PontoParada> getAll(@PathVariable("linha") String linha) {
        return busServicePontosdeParada.getPontos(linha);
    }
}
