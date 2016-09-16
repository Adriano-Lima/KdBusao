package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusDao {

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
            //2016-09-15 17:14:19
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateserver = ft.parse(dataServer);
            System.out.println("Data que veio na requisicao"+data + "data do servidor:"+dateserver);
            flag = data.after(dateserver);
        } catch (Exception e) {
            System.out.println("e.getMessage()");
        } finally {
            return flag;
        }
    }

    public void atualizarLocalizacao(Bus bus) {
        try {
            Connection connection = ConexaoUtil.getInstance().getConnection();
            String sql = "update onibus set latitude = ?, longitude = ?, dataHora = CURRENT_TIMESTAMP where idonibus = ?";

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
