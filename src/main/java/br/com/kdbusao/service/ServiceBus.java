package br.com.kdbusao.service;

import br.com.kdbusao.controller.Dao;
import br.com.kdbusao.model.Atualizacao;
import br.com.kdbusao.model.Bus;
import br.com.kdbusao.model.Resposta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ServiceBus {

    public List<Bus> getLoc(String linha) {
        Dao dao = new Dao();
        return dao.localizacaoAtualBus(linha);
    }

    public Resposta setLocalizacao(Atualizacao atualizacao) {
        Dao dao = new Dao();
        String vec[] = atualizacao.getLocalizacao().split(",");
        Bus bus = new Bus(atualizacao.getIdOnibus(), vec[0], vec[1]);
        Date d = null;
        boolean teste = false;
        try {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            d = ft.parse(atualizacao.getData());
            teste = dao.testarData(bus, d);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Resposta resposta = new Resposta();
        if (teste) {
            resposta.setMsg("OK");
            dao.atualizarLocalizacao(bus);
        } else {
            resposta.setMsg("NO");
        }
        return resposta;
    }

    public List<String> Linhas(String cidade) {
        Dao dao = new Dao();
        return dao.getLinhasCidade(cidade);
    }

}
