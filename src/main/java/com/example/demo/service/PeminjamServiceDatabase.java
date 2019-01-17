package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BarangMapper;
import com.example.demo.dao.KonfirmasiPeminjamanMapper;
import com.example.demo.dao.PeminjamanMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.BarangDipinjamModel;
import com.example.demo.model.KonfirmasiPeminjamanModel;
import com.example.demo.model.PeminjamanModel;
import com.example.demo.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PeminjamServiceDatabase implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BarangMapper barangMapper;

	@Autowired
	private PeminjamanMapper peminjamanMapper;

	@Autowired
	private KonfirmasiPeminjamanMapper konfirmasiMapper;

	@Override
	public List<PeminjamanModel> getAllPeminjaman() {
		log.info("getAllList", userMapper.getAllPeminjaman());
		return userMapper.getAllPeminjaman();
	}

	@Override
	public UserModel selectUserByUsername(String username) {
		log.info("get user by username", userMapper.selectUserByUsername(username));
		return userMapper.selectUserByUsername(username);
	}

	@Override
	public PeminjamanModel getPeminjamanbyID(String id) {
		// TODO Auto-generated method stub
		PeminjamanModel peminjam = userMapper.getPeminjamanbyID(id);
		peminjam.listBarangDipinjam = getAllBarangDipinjam(id);
		peminjam.listKonfirmasi = getAllKonfirmasi(id);
		// Checking
		log.info("get peminjaman by id, ler ", peminjam);
		System.out.println();
		System.out.println("peminjam " + peminjam);
		log.info("get listbarang by id, ler ", peminjam.listBarangDipinjam);
		// System.out.println("list barang " + peminjam.listBarangDipinjam +
		// peminjam.listBarangDipinjam.size());
		// log.info("get konfirmasi by id tol ", peminjam.listKonfirmasi);
		// System.out.println("get konfirmasi by id tol " +
		// peminjam.listKonfirmasi + peminjam.listKonfirmasi.size());
		// gak usah ngegas lu tod, sel kok ngegas
		return peminjam;
	}

	public KonfirmasiPeminjamanModel getKonfirmasiPeminjamanbyID(String id) {
		KonfirmasiPeminjamanModel konfirmasiPeminjaman = userMapper.getKonfirmasiPeminjamanbyID(id);
//		konfirmasiPeminjam.listBarangDipinjam = getAllBarangDipinjam(id);
//		konfirmasiPeminjam.listKonfirmasi = getAllKonfirmasi(id);
		konfirmasiPeminjaman.peminjaman = userMapper.getPeminjamanbyID(konfirmasiPeminjaman.idPeminjaman.toString());
		konfirmasiPeminjaman.peminjaman.listBarangDipinjam = barangMapper.getAllBarangDipinjam(konfirmasiPeminjaman.peminjaman.id.toString());
		konfirmasiPeminjaman.peminjaman.userPeminjam = userMapper.selectUserById(konfirmasiPeminjaman.peminjaman.idPeminjam.toString());
		log.info("konfirmasi peminjaman",konfirmasiPeminjaman);
		log.info("peminjaman di konfirmasi ",konfirmasiPeminjaman.peminjaman);
		log.info("sapa yg minjem ",konfirmasiPeminjaman.peminjaman.userPeminjam);
		System.out.println(konfirmasiPeminjaman);
		return konfirmasiPeminjaman;
	}

	public List<BarangDipinjamModel> getAllBarangDipinjam(String id) {
		List<BarangDipinjamModel> listBarangDipinjam = barangMapper.getAllBarangDipinjam(id);
		return listBarangDipinjam;
	}

	public List<KonfirmasiPeminjamanModel> getAllKonfirmasi(String id) {
		List<KonfirmasiPeminjamanModel> listKonfirmasi = konfirmasiMapper.getAllKonfirmasi(id);
		return listKonfirmasi;
	}

	public void updateProfilPeminjam(UserModel peminjam) {
		log.info("update Keluarga " + peminjam);
		userMapper.updateProfilPeminjam(peminjam);
	}

	@Override
	public UserModel selectUserById(String id) {
		UserModel user = userMapper.selectUserById(id);
		System.out.println("user by id " + user);
		return user;
	}

	public void editPeminjaman(PeminjamanModel peminjaman) {
		// TODO Auto-generated method stub
		log.info("edit peminjaman " + peminjaman);
		peminjamanMapper.editPeminjaman(peminjaman);
	}

	@Override
	public void tambahPeminjaman(PeminjamanModel peminjamanModel) {
		// TODO Auto-generated method stub
		log.info("tambah peminjaman" + peminjamanModel);
		peminjamanMapper.addPeminjaman(peminjamanModel);
		PeminjamanModel peminjamanTerakhir = peminjamanMapper.peminjamanTerakhir();
		peminjamanTerakhir.setTanggalPerubahan(java.time.LocalDate.now().toString());
		peminjamanMapper.updateTanggalPerubahan(peminjamanTerakhir);
	}

	// public void deletePeminjaman(PeminjamanModel peminjaman) {
	// // TODO Auto-generated method stub
	// log.info("delete peminjaman " + peminjaman);
	// peminjamanMapper.deletePeminjaman(peminjaman);
	// }
	public void deletePeminjaman(PeminjamanModel peminjaman) {
		// TODO Auto-generated method stub
		log.info("delete peminjaman nomor" + peminjaman);
		peminjamanMapper.deletePeminjaman(peminjaman);
		System.out.println(peminjaman.isDelete);
	}

}
