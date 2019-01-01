package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.BarangDipinjamModel;
import com.example.demo.model.BarangModel;

@Mapper
public interface BarangMapper {

	@Select("SELECT * FROM barang " + "where kuantitas!=0 and is_delete=0" )
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
			@Result (property = "barangAsal", column = "id_barang",javaType = BarangModel.class,many = @Many(select="getBarangById"))
	})
	List<BarangDipinjamModel> getAllBarangDipinjam(@Param("id") String id);
}
