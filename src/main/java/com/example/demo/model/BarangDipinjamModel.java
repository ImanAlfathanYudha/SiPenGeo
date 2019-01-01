package com.example.demo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarangDipinjamModel {
	public Integer id;
	public Integer idBarang;
	public Integer idPeminjaman;
	public Integer kuantitasDipinjam;
	public BarangModel barangAsal;
	public PeminjamanModel peminjamanAsal;
}
