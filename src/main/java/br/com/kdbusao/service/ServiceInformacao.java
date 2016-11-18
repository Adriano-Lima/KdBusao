package br.com.kdbusao.service;

import br.com.kdbusao.controller.Dao;
import br.com.kdbusao.model.Empresa;
import br.com.kdbusao.model.Informacao;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServiceInformacao {
    
    Dao dao = new Dao();
    
    public List<Informacao> getInformacoes(String cidade) {        
        return dao.getInformacoes(cidade);
    }
    
}
