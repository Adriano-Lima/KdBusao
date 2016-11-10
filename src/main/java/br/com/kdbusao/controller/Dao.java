package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import br.com.kdbusao.model.PontoParada;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void atualizarLocalizacao(Bus bus) {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "update onibus set latitude = ?, longitude = ?, dataHora = ? where idonibus = ?";

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String DateToStr = ft.format(new Date());           

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, bus.getLat());
            statement.setDouble(2, bus.getLongi());
            statement.setString(3,DateToStr);
            statement.setInt(4, bus.getId());

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
            String sql = "select idonibus,latitude,longitude from onibus inner join linha on(onibus.idlinha = linha.idlinha) where linha.nome like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, linha);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                double latitude = Double.parseDouble(resultSet.getString("latitude"));
                double longitude = Double.parseDouble(resultSet.getString("longitude"));
                Bus b = new Bus(resultSet.getInt("idonibus"), linha, latitude, longitude);
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

}
