package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BarangMapper;
import com.example.demo.model.BarangModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BarangServiceDatabase implements BarangService {
	@Autowired
	private BarangMapper barangMapper;

	@Override
	public List<BarangModel> getAllBarangTersedia() {
		// TODO Auto-generated method stub
		log.info("List barang tersedia", barangMapper.getAllBarangTersedia());
		System.out.println(barangMapper.getAllBarangTersedia());
		return barangMapper.getAllBarangTersedia();
	}

	@Override
	public BarangModel getBarangById(String id) {
		BarangModel barang = barangMapper.getBarangById(id);
		log.info("barang ", barang);
		return barang;
	}

	@Override
	public void addBarang(BarangModel barang) {
		// TODO Auto-generated method stub
		log.info("tambah ruangan" + barang);
		barangMapper.addBarang(barang);
	}

	public void editBarang(BarangModel barang) {
		// TODO Auto-generated method stub
		log.info("edit barang " + barang);
		barangMapper.editBarang(barang);
	}

}
