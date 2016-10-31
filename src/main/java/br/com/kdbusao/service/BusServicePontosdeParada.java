/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kdbusao.service;

import br.com.kdbusao.controller.BusDao;
import br.com.kdbusao.model.PontoParada;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class BusServicePontosdeParada {

    public List<PontoParada> getPontos(String linha) {
        BusDao dao = new BusDao();
        return dao.getPontosLinha(linha);
    }
}
