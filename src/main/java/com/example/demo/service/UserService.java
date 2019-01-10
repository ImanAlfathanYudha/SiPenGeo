package com.example.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.model.KonfirmasiPeminjamanModel;
import com.example.demo.model.PeminjamanModel;
import com.example.demo.model.UserModel;

public interface UserService {
	List<PeminjamanModel> getAllPeminjaman();
	
	UserModel selectUserByUsername(String username);
	
	UserModel selectUserById(String id);
	
	PeminjamanModel getPeminjamanbyID(String id);
	
	List<KonfirmasiPeminjamanModel> getAllKonfirmasi(String id);
	
	void tambahPeminjaman(PeminjamanModel peminjaman);
	
}
