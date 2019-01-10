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

import com.example.demo.model.UserModel;
import com.example.demo.model.BarangDipinjamModel;
import com.example.demo.model.BarangModel;
import com.example.demo.model.KonfirmasiPeminjamanModel;
import com.example.demo.model.PeminjamanModel;

@Mapper
public interface UserMapper {
	@Select("SELECT *" + "FROM peminjaman " + "order by tanggal_perubahan")
	@Results(value = { @Result(property = "idPeminjam", column = "id_peminjam"),
			@Result(property = "tujuanPinjam", column = "tujuan_pinjam"),
			@Result(property = "tempatPeminjaman", column = "tempat_peminjaman"),
			@Result(property = "tanggalPinjam", column = "tanggal_pinjam"),
			@Result(property = "tanggalPengembalian", column = "tanggal_pengembalian"),
			@Result(property = "totalHargaJaminan", column = "total_harga_jaminan"),
			@Result(property = "tanggalPerubahan", column = "tanggal_perubahan"),
			@Result(property = "userPeminjam", column = "id_peminjam", javaType = UserModel.class, many = @Many(select = "selectUserById")),
			@Result(property = "listKonfirmasi", column = "id_peminjaman", javaType = List.class, many = @Many(select = "KonfirmasiPeminjamanMapper.getAllKonfirmasi")) })
	List<PeminjamanModel> getAllPeminjaman();

	@Select("select * from user where username = #{username}")
	@Results(value = {
			@Result(property = "listPeminjaman", column = "id_peminjam", javaType = List.class, many = @Many(select = "getAllPeminjaman")) })
	UserModel selectUserByUsername(@Param("username") String username);

	@Select("select * from user where id = #{id}")
	UserModel selectUserById(@Param("id") String id);

	// @Select("SELECT * FROM barang_dipinjam")
	// @Results(value = { @Result(property = "namaBarang", column =
	// "nama_barang"),
	// @Result(property = "hargaJamin", column = "harga_jamin"),
	// @Result(property = "isDelete", column = "is_Delete"),
	// @Result (property = "barangAsal", column = "id_barang",javaType =
	// BarangModel.class,many = @Many(select="getBarangById"))
	// })
	// List<BarangDipinjamModel> getAllBarangDipinjam();

	// @Select("SELECT * FROM kofirmasi_peminjaman where id_peminjaman=#{id}")
	// @Results(value = {
	// @Result(property = "idPetugas", column = "id_petugas"),
	// @Result(property = "idDosen", column = "id_dosen"),
	// @Result(property = "idPeminjaman", column = "id_peminjaman"),
	// @Result(property = "statusKonfirmasi", column = "status_konfirmasi"),
	// @Result(property = "keterangan", column = "keterangan"),
	// })
	// List<KonfirmasiPeminjamanModel> getAllKonfirmasi(@Param("id") String id);

	@Select("SELECT id, id_peminjam, tujuan_pinjam, tempat_peminjaman,tanggal_pinjam, tanggal_pengembalian, total_harga_jaminan, tanggal_perubahan "
			+ "FROM peminjaman " + "where id=#{id} order by tanggal_perubahan")
	@Results(value = { @Result(property = "idPeminjam", column = "id_peminjam"),
			@Result(property = "tujuanPinjam", column = "tujuan_pinjam"),
			@Result(property = "tempatPeminjaman", column = "tempat_peminjaman"),
			@Result(property = "tanggalPinjam", column = "tanggal_pinjam"),
			@Result(property = "tanggalPengembalian", column = "tanggal_pengembalian"),
			@Result(property = "totalHargaJaminan", column = "total_harga_jaminan"),
			@Result(property = "tanggalPerubahan", column = "tanggal_perubahan"),
			@Result(property = "listBarangDipinjam", column = "id_peminjaman", javaType = List.class, many = @Many(select = "BarangMapper.getAllBarangDipinjam")),
			@Result(property = "listKonfirmasi", column = "id_peminjaman", javaType = List.class, many = @Many(select = "KonfirmasiPeminjamanMapper.getAllKonfirmasi")) })
	PeminjamanModel getPeminjamanbyID(@Param("id") String id);

	@Update("UPDATE user " + "SET nama=#{nama}, instansi=#{instansi}, telepon=#{telepon}, alamat=#{alamat} "
			+ "WHERE id = #{id}")
	void updateProfilPeminjam(UserModel user);

	@Insert("INSERT INTO peminjaman (`id_peminjam`, `tujuan_pinjam`, `deskripsi`, `tempat_peminjaman`, `tanggal_pinjam`, `tanggal_pengembalian`, `tanggal_pengembalian_barang`,  `tanggal_perubahan` ) "
			+ "VALUES ('1', #{tujuanPinjam}, #{deskripsi}, #{tempatPeminjaman}, #{tanggalPinjam}, #{tanggalPengembalian}, #{tanggalPengembalian}, #{tanggalPinjam})")
	void addPeminjaman(PeminjamanModel peminjamanModel);
}
