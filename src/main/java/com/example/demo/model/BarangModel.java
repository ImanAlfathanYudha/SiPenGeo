package com.example.demo.model;

import java.time.Year;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarangModel {
	public Integer id;
	public String namaBarang;
	public String tipe;
	public String tahun;
	public Integer hargaJamin;
	public Integer kuantitas;
	public Integer isDelete;
}
