package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusDao {

    public void atualizarLocalizacao(Bus bus) {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "update onibus set latitude = ? longitude = ? where idonibus = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, bus.getLat());
            statement.setDouble(2, bus.getLongi());
            statement.setInt(3, bus.getId());

            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("e.getMessage()");
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

}
