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
	public String tujuanPinjam;
	public String tempatPeminjaman;
	public String tanggalPinjam;
	public String tanggalPengembalian;
	public Integer totalHargaJaminan;
	public Date tanggalPerubahan;
	public UserModel userPeminjam;
	public List<BarangDipinjamModel> listBarangDipinjam;
	public List<KonfirmasiPeminjamanModel> listKonfirmasi;
}
