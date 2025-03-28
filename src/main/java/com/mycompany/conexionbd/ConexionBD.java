/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conexionbd;

import conexion.Conexion;
import java.sql.Connection;

/**
 *
 * @author maibe
 */
public class ConexionBD {

    public static void main(String[] args) {
        Connection conexion = Conexion.getInstance().getConnection();
        System.out.println("variable" + conexion);
    }
}
