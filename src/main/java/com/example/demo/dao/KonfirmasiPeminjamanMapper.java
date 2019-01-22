package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.KonfirmasiPeminjamanModel;

@Mapper
public interface KonfirmasiPeminjamanMapper {
	
	@Select("SELECT * FROM konfirmasi_peminjaman where id_peminjaman=#{id}")
	@Results(value = {
			@Result(property = "idPetugas", column = "id_petugas"),
			@Result(property = "idDosen", column = "id_dosen"),
			@Result(property = "idPeminjaman", column = "id_peminjaman"),
			@Result(property = "statusKonfirmasi", column = "status_konfirmasi"),
			@Result(property = "keterangan", column = "keterangan"),
	}) 
	List<KonfirmasiPeminjamanModel> getAllKonfirmasi(@Param("id") String id);
	
	@Insert("INSERT INTO konfirmasi_peminjaman ( `id_petugas`, `id_dosen`, `id_peminjaman`, `status_konfirmasi`, `keterangan`) "
			+ "VALUES ('6', NULL, #{id}, 'Dilaporkan', NULL), "
			+ "( NULL, '5', #{id}, 'Dilaporkan', NULL)")
	void buatKonfirmasiEksternal(@Param("id") String id);
	
	@Insert("INSERT INTO konfirmasi_peminjaman ( `id_petugas`, `id_dosen`, `id_peminjaman`, `status_konfirmasi`, `keterangan`) "
			+ "VALUES ('6', NULL, #{id}, 'Dilaporkan', NULL), "
			+ "( NULL, '4', #{id}, 'Dilaporkan', NULL)")
	void buatKonfirmasiInternal(@Param("id") String id);
	
	@Insert("INSERT INTO konfirmasi_peminjaman ( `id_petugas`, `id_dosen`, `id_peminjaman`, `status_konfirmasi`, `keterangan`) "
			+ "VALUES ('6', NULL, #{id}, 'Dilaporkan', NULL)")
	void buatKonfirmasiPetugasLab(@Param("id") String id);
}
