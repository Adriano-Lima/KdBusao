package br.com.kdbusao.controller;

import br.com.kdbusao.model.Informacao;
import br.com.kdbusao.service.ServiceInformacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerInformacao {

    @Autowired
    private ServiceInformacao service;

    @RequestMapping(path = "informacoes/{cidade}", method = RequestMethod.GET)
    public List<Informacao> getInformacoes(@PathVariable("cidade") String cidade) {
        return service.getInformacoes(cidade);
    }
}
