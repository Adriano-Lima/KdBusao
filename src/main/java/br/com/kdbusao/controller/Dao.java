package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import br.com.kdbusao.model.Empresa;
import br.com.kdbusao.model.Informacao;
import br.com.kdbusao.model.PontoParada;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.DatatypeConverter;
import org.apache.tomcat.util.codec.binary.Base64;

public class Dao {

    public boolean testarData(Bus bus, Date data) {
        boolean flag = false;
        String dataServer = null;
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select dataHora from onibus where idonibus = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, bus.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dataServer = resultSet.getString("dataHora");
            }
            statement.close();
            connection.close();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateserver = ft.parse(dataServer);
            System.out.println("Data que veio na requisicao" + data + "data do servidor:" + dateserver);////// para fins de log 
            flag = data.after(dateserver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return flag;
        }
    }

    public void atualizarLocalizacao(Bus bus, String sentido) {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "update onibus set latitude = ?, longitude = ?, dataHora = ?, sentido = ? where idonibus = ?";

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String DateToStr = ft.format(new Date());

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, bus.getLat());
            statement.setDouble(2, bus.getLongi());
            statement.setString(3, DateToStr);
            statement.setString(4, sentido);
            statement.setInt(5, bus.getId());            

            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Bus> localizacaoAtualBus(String linha) {
        List<Bus> onibus = new ArrayList<Bus>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select idonibus,latitude,longitude,sentido from onibus inner join linha on(onibus.idlinha = linha.idlinha) where linha.nome like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, linha);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                double latitude = Double.parseDouble(resultSet.getString("latitude"));
                double longitude = Double.parseDouble(resultSet.getString("longitude"));
                Bus b = new Bus(resultSet.getInt("idonibus"), linha, latitude, longitude, resultSet.getString("sentido"));
                onibus.add(b);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("e.getMessage()");
        } finally {
            return onibus;
        }
    }

    public List<String> getLinhasCidade(String cidade) {
        List<String> linhas = new ArrayList<String>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select nome from linha where cidade like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cidade);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String linha = new String(resultSet.getString("nome"));
                linhas.add(linha);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("e.getMessage()");
        } finally {
            return linhas;
        }
    }

    public List<PontoParada> getPontosLinha(String linha) {
        List<PontoParada> pontos = new ArrayList<PontoParada>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select latitude,longitude,descricao from pontoParada inner join linha on(pontoParada.idlinha = linha.idlinha) where linha.nome like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, linha);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String latitude = resultSet.getString("latitude");
                String longitude = resultSet.getString("longitude");
                String descricao = resultSet.getString("descricao");
                String coordenadas = latitude.concat(",").concat(longitude);
                pontos.add(new PontoParada(coordenadas, "", descricao));
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("e.getMessage()");
        } finally {
            return pontos;
        }
    }

    public List<Empresa> getEmpresas(String cidade) {
        List<Empresa> lista = new ArrayList<Empresa>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select * from empresa where cidade like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cidade);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empresa e = new Empresa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("tipo"), resultSet.getString("descricao"),
                        resultSet.getString("cidade"), resultSet.getString("endereco"), resultSet.getDouble("latitude"), resultSet.getDouble("longitude"));     

                byte[] vec = resultSet.getBytes("icon");
                String icon = Base64.encodeBase64String(vec);
                e.setIcon(icon);
                
                vec = resultSet.getBytes("imagem");
                String imagem = Base64.encodeBase64String(vec);
                e.setImagem(imagem);
                
                lista.add(e);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("e.getMessage()");
        } finally {
            return lista;
        }
    }

    public List<Informacao> getInformacoes(String cidade) {
         List<Informacao> lista = new ArrayList<Informacao>();
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "select * from informacoes where cidade like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cidade);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Informacao i  = new Informacao(resultSet.getInt("id"), resultSet.getString("cidade"), resultSet.getString("empresa"), resultSet.getString("link"));                
                lista.add(i);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("e.getMessage()");
        } finally {
            return lista;
        }
    }

}
