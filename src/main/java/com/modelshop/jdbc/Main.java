
package com.modelshop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        String postgredb = "jdbc:postgresql://localhost:5432/";
        Scanner in = new Scanner(System.in);
        System.out.println("Введите адрес базы данных: (3DModelOnlineShop)");
        String url = postgredb + "3DModelOnlineShop"; //in.nextLine();
        System.out.println("Введите логин базы данных: (postgres)");
        String login = "postgres" ; //in.nextLine();
        System.out.println("Введите пароль базы данных: (admin)");
        String password = "admin" ; //in.nextLine();
        createDatabase(url, login, password);
    }
    
    private static void createDatabase(String url, String login, String password) { 
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Создание базы данных");
          Connection con = DriverManager.getConnection(url, login, password);
          Statement stmt = con.createStatement();
                String SQL ="CREATE TABLE IF NOT EXISTS threed_user (\n"
                        + "  \"id_user\"      BIGINT          NOT NULL,\n"
                        + "  \"nik\"    CHARACTER VARYING(50) NOT NULL UNIQUE,\n"
                        + "  \"folowCount\" INTEGER           NOT NULL,\n"
                        + "  \"modelsCount\"       INTEGER    NOT NULL,\n"
                        + "  \"raiting\" 	INTEGER          NOT NULL,\n"
                        + "  \"account\"     BIGINT          NOT NULL,\n"
                        + "  PRIMARY KEY (\"id_user\")\n"
                        + ");"
                        +"CREATE TABLE IF NOT EXISTS folowing (\n"
                        + "  \"id_user\"      BIGINT NOT NULL,\n"
                        + "  \"id_folowing_user\"      BIGINT NOT NULL,\n"
                        + "  PRIMARY KEY (\"id_user\",\"id_folowing_user\"),\n"
                        + "  FOREIGN KEY (\"id_folowing_user\") REFERENCES \"threed_user\" (\"id_user\"),\n"
                        + "  FOREIGN KEY (\"id_user\") REFERENCES \"threed_user\" (\"id_user\")\n"
                        + ");"
                        +"CREATE TABLE IF NOT EXISTS model_format (\n"
                        + "  \"id_format\"      BIGINT NOT NULL,\n"
                        + "  \"formatName\"      CHARACTER VARYING(50),\n"
                        + "  PRIMARY KEY (\"id_format\")\n"
                        + ");"
                        +"CREATE TABLE IF NOT EXISTS renders (\n"
                        + "  \"id_render\"      BIGINT NOT NULL,\n"
                        + "  \"size\"      INTEGER NOT NULL,\n"
                        + "  \"po_render\" CHARACTER VARYING(50),\n"
                        + "  PRIMARY KEY (\"id_render\")\n"
                        + ");"
                        +"CREATE TABLE IF NOT EXISTS models (\n"
                        + "  \"id_author\"   BIGINT NOT NULL,\n"
                        + "  \"id_model\"    BIGINT NOT NULL,\n"
                        + "  \"modelName\"   CHARACTER VARYING(50) NOT NULL,\n"
                        + "  \"polyCount\"   INTEGER NOT NULL,\n"
                        + "  \"polyVertex\"  INTEGER NOT NULL,\n"
                        + "  \"id_render\"   BIGINT NOT NULL,\n"
                        + "  \"id_format\"   BIGINT NOT NULL,\n"
                        + "  FOREIGN KEY (\"id_author\") REFERENCES \"threed_user\" (\"id_user\"),\n"
                        + "  FOREIGN KEY (\"id_render\") REFERENCES \"renders\" (\"id_render\"),\n"
                        + "  FOREIGN KEY (\"id_format\") REFERENCES \"model_format\" (\"id_format\"),\n"
                        + "  PRIMARY KEY (\"id_author\",\"id_model\")\n"
                        + "  \n"
                        + ");"
                        +"CREATE TABLE IF NOT EXISTS sale (\n"
                        + "  \"id_author\"   BIGINT NOT NULL,\n"
                        + "  \"id_buyer\"    BIGINT NOT NULL,\n"
                        + "  \"id_model\"    BIGINT NOT NULL,\n"
                        + "  \"count\"       INTEGER,\n"
                        + "  PRIMARY KEY (\"id_author\",\"id_buyer\",\"id_model\"),\n"
                        + "  FOREIGN KEY (\"id_author\",\"id_model\") REFERENCES \"models\" (\"id_author\",\"id_model\"),\n"
                        + "  FOREIGN KEY (\"id_buyer\") REFERENCES \"threed_user\" (\"id_user\")\n"
                        + ");"
                        +"CREATE TABLE IF NOT EXISTS sale_story (\n"
                        + "  \"id_author\"   BIGINT NOT NULL,\n"
                        + "  \"id_buyer\"    BIGINT NOT NULL,\n"
                        + "  \"id_model\"    BIGINT NOT NULL,\n"
                        + "  \"date\"        DATE NOT NULL,\n"
                        + "  PRIMARY KEY (\"id_author\",\"id_buyer\",\"id_model\"),\n"
                        + "  FOREIGN KEY (\"id_author\",\"id_model\",\"id_buyer\") REFERENCES \"sale\" (\"id_author\",\"id_model\",\"id_buyer\")\n"
                        + ");";
                stmt.executeUpdate(SQL);
                System.out.print("База данных создана успешно!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
