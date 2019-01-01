package com.example.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KonfirmasiPeminjamanModel {
	public Integer id;
	public Integer idDosen;
	public Integer idPetugas;
	public Integer idPeminjaman;
	public String statusKonfirmasi;
	public String keterangan;
}
