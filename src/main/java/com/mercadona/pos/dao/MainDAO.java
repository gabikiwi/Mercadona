package com.mercadona.pos.dao;

public class MainDAO {

	public static void main(String[] args) {

		try {
			MySQLAccess dao = new MySQLAccess();
			dao.readDataBase();

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
