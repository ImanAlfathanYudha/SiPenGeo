package com.example.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	public Integer id;
	public String npm;
	public String nama;
	public String username;
	public String role;
	public String instansi;
	public String telepon;
	public String alamat;
	public List<PeminjamanModel> listPeminjaman;
}
