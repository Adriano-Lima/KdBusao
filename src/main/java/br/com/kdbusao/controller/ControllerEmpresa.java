package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import br.com.kdbusao.model.Empresa;
import br.com.kdbusao.service.ServiceEmpresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerEmpresa {

    @Autowired
    ServiceEmpresa service;

    @RequestMapping(path = "empresas/{cidade}/{coordenadas}", method = RequestMethod.GET)
    public List<Empresa> getEmpresas(@PathVariable("cidade") String cidade, @PathVariable("coordenadas") String coordenadas) {
        return service.getEmpresas(cidade);
    }

}
