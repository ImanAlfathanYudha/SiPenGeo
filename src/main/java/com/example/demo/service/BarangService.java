package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BarangModel;

public interface BarangService {
	
	List<BarangModel> getAllBarangTersedia();
	
	BarangModel getBarangById(String id);
	
	void addBarang(BarangModel barang);
	
	void deleteBarang(BarangModel barang);
}
