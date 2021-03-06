package com.example.demo.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeminjamanModel {
	public Integer id;
	public String idPeminjam;
	public String idKonfirmasi;
	public String idPeminjaman;
	public String tujuanPinjam;
	public String deskripsi;
	public String tempatPeminjaman;
	public String tanggalPinjam;
	public String tanggalPengembalian;
	public Integer totalHargaJaminan;
	public String tanggalPerubahan;
	public String statusKonfirmasi;
	public Integer isDelete;
	public UserModel userPeminjam;
	public List<BarangDipinjamModel> listBarangDipinjam;
	public List<KonfirmasiPeminjamanModel> listKonfirmasi;
}
