package br.com.kdbusao.service;

import br.com.kdbusao.controller.BusDao;
import br.com.kdbusao.model.Bus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class BusService {

    public List<Bus> getLoc(String linha){
        BusDao busDao = new BusDao();
        return busDao.localizacaoAtualBus(linha);
    }
    
    public String setLocalizacao(String id, String lon, String lat,String data){
        BusDao busDao = new BusDao();
        Bus bus = new Bus(id, lat, lon);
        String resposta = "";
        Date d = null;
        boolean teste = false;        
        try{
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        d = ft.parse(data);
        teste = busDao.testarData(bus, d);
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }        
        if(teste){
            resposta ="Ok";
            busDao.atualizarLocalizacao(bus);
        }else{
            resposta ="NO";
        }               
        return resposta;
    }

}
