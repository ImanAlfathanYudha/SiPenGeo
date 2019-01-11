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

import com.example.demo.model.PeminjamanModel;
import com.example.demo.model.UserModel;

@Mapper
public interface PeminjamanMapper {

	@Select("SELECT * FROM peminjaman " + "where is_delete=0")
	@Results(value = { @Result(property = "tujuanPinjam", column = "tujuan_pinjam"),
			@Result(property = "deskripsi", column = "deskripsi"),
			@Result(property = "tanggalPinjam", column = "tanggal_pinjam"),
			@Result(property = "tanggalPengembalian", column = "tanggal_pengembalian"),
			@Result(property = "isDelete", column = "is_delete") })
	List<PeminjamanModel> getAllPeminjamanTersedia();

	@Select("select * from peminjaman where id = #{id}")
	@Results(value = { @Result(property = "tujuanPinjam", column = "tujuan_pinjam"),
			@Result(property = "hargaJamin", column = "harga_jamin"),
			@Result(property = "isDelete", column = "is_Delete") })
	PeminjamanModel getPeminjamanById(@Param("id") String id);

	// @Select("SELECT * FROM peminjaman_dipinjam where id_peminjaman=#{id}")
	// @Results(value = { @Result(property = "tujuanPinjam", column =
	// "tujuan_pinjam"),
	// @Result(property = "idPeminjam", column = "id_peminjam"),
	// @Result(property = "hargaJamin", column = "harga_jamin"),
	// @Result(property = "isDelete", column = "is_Delete"),
	// @Result(property = "kuantitasDipinjam", column = "kuantitas_dipinjam"),
	// @Result(property = "peminjamanAsal", column = "id_peminjaman", javaType =
	// PeminjamanModel.class, many = @Many(select = "getPeminjamanById")) })
	// List<PeminjamanDipinjamModel> getAllPeminjamanDipinjam(@Param("id")
	// String id);

	// @Insert("INSERT INTO peminjaman (tujuan_pinjam, tipe, tahun, harga_jamin,
	// kuantitas, is_delete) "
	// + "VALUES (#{tujuanPinjam}, #{tipe}, #{tahun}, #{hargaJamin},
	// #{kuantitas}, 0)")
	// void addPeminjaman(PeminjamanModel peminjamanModel);
	// biar yuda yg bikin ini

	@Select("SELECT * FROM peminjaman " + "ORDER BY ID DESC LIMIT 1")
	PeminjamanModel peminjamanTerakhir();

	@Insert("INSERT INTO peminjaman (`id_peminjam`, `tujuan_pinjam`, `deskripsi`, `tempat_peminjaman`, `tanggal_pinjam`, `tanggal_pengembalian`, `tanggal_pengembalian_barang`,  `tanggal_perubahan`, `total_harga_jaminan`, `is_delete`) "
			+ "VALUES ('1', #{tujuanPinjam}, #{deskripsi}, #{tempatPeminjaman}, #{tanggalPinjam}, #{tanggalPengembalian}, #{tanggalPengembalian}, #{tanggalPinjam}, '0', '0')")
	void addPeminjaman(PeminjamanModel peminjamanModel);

	@Update("UPDATE peminjaman " + "SET tanggal_perubahan = #{tanggalPerubahan} " + "WHERE id = #{id} ")
	void updateTanggalPerubahan(PeminjamanModel peminjaman);

	@Update("UPDATE peminjaman SET tempat_peminjaman=#{tempatPeminjaman}, tujuan_pinjam=#{tujuanPinjam}, deskripsi=#{deskripsi}, tanggal_pinjam=#{tanggalPinjam}, tanggal_pengembalian=#{tanggalPengembalian} "
			+ "WHERE id = #{id}")
	void editPeminjaman(PeminjamanModel peminjaman);

	@Update("UPDATE peminjaman SET is_delete=1 " + "WHERE id = #{id}")
	void deletePeminjaman(PeminjamanModel peminjaman);
	// @Update("UPDATE peminjaman SET is_delete=1 " + "WHERE id = #{id}")
	// void deletePeminjaman(@Param("id") String id);
}
