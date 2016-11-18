package br.com.kdbusao.service;

import br.com.kdbusao.controller.Dao;
import br.com.kdbusao.model.Empresa;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmpresa {

    Dao dao = new Dao();
    
public List<Empresa> getEmpresas(String cidade) {        
        return dao.getEmpresas(cidade);
    }

}
