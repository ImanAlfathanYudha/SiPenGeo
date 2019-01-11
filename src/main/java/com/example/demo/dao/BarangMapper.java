package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.BarangDipinjamModel;
import com.example.demo.model.BarangModel;
import com.example.demo.model.PeminjamanModel;

@Mapper
public interface BarangMapper {

	@Select("SELECT * FROM barang " + "where kuantitas!=0 and is_delete=0")
	@Results(value = { @Result(property = "namaBarang", column = "nama_barang"),
			@Result(property = "hargaJamin", column = "harga_jamin"),
			@Result(property = "isDelete", column = "is_delete") })
	List<BarangModel> getAllBarangTersedia();

	@Select("select * from barang where id = #{id}")
	@Results(value = { @Result(property = "namaBarang", column = "nama_barang"),
			@Result(property = "hargaJamin", column = "harga_jamin"),
			@Result(property = "isDelete", column = "is_Delete") })
	BarangModel getBarangById(@Param("id") String id);

	@Select("SELECT * FROM barang_dipinjam where id_peminjaman=#{id}")
	@Results(value = { @Result(property = "namaBarang", column = "nama_barang"),
			@Result(property = "idBarang", column = "id_barang"),
			@Result(property = "hargaJamin", column = "harga_jamin"),
			@Result(property = "isDelete", column = "is_Delete"),
			@Result(property = "kuantitasDipinjam", column = "kuantitas_dipinjam"),
			@Result(property = "barangAsal", column = "id_barang", javaType = BarangModel.class, many = @Many(select = "getBarangById")) })
	List<BarangDipinjamModel> getAllBarangDipinjam(@Param("id") String id);

	@Insert("INSERT INTO barang (nama_barang, tipe, tahun, harga_jamin, kuantitas, is_delete) "
			+ "VALUES (#{namaBarang}, #{tipe}, #{tahun}, #{hargaJamin}, #{kuantitas}, 0)")
	void addBarang(BarangModel barangModel);

	@Update("UPDATE barang SET nama_barang=#{namaBarang}, tipe=#{tipe}, tahun=#{tahun}, harga_jamin=#{hargaJamin}, kuantitas=#{kuantitas} "
			+ "WHERE id = #{id}")
	void editBarang(BarangModel barang);
	
	@Update("UPDATE barang "
			+ "SET is_delete = '1'"
			+ "WHERE id = #{id} ")
	void deleteBarangById(BarangModel barang);
}
