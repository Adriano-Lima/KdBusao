package br.com.kdbusao.controller;

import br.com.kdbusao.model.Atualizacao;
import br.com.kdbusao.model.Bus;
import br.com.kdbusao.model.Resposta;
import br.com.kdbusao.service.BusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bus")
public class BusController {

    @Autowired
    private BusService busService;

    //retornar todos os onibus de uma linha
    @RequestMapping(path = "/onibus/{linha}", method = RequestMethod.GET)
    public List<Bus> getAll(@PathVariable("linha") String linha) {
        return busService.getLoc(linha);
    }

    //atualizar a posicao de um onibus 
    @RequestMapping(path = "/onibus/posicao", method = RequestMethod.POST)
//    public Resposta setPosicao(@RequestParam("idOnibus") String idOnibus, @RequestParam("coordenadas") String coordenadas,
//            @RequestParam("data") String data ) {
    public Resposta setPosicao(@RequestBody Atualizacao atualizacao) {
        System.out.println("Mudança de localização >>:" + atualizacao.getLocalizacao() + " distancia >>:" + atualizacao.getDistancia()
        + " velocidade >>:"+atualizacao.getVelocidade() +" data >>:"+atualizacao.getData());
        return busService.setLocalizacao(atualizacao);   
    }

    //consulta de linhas disponiveis por cidade
    @RequestMapping(path = "/linhas/{cidade}", method = RequestMethod.GET)
    public List<String> getLinhas(@PathVariable("cidade") String cidade) {
        return busService.Linhas(cidade);
    }

}
