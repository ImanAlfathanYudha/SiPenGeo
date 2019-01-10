package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.BarangDipinjamModel;
import com.example.demo.model.BarangModel;
import com.example.demo.model.KonfirmasiPeminjamanModel;
import com.example.demo.model.PeminjamanModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.BarangServiceDatabase;
import com.example.demo.service.PeminjamServiceDatabase;

@Controller
public class UserController {

	@Autowired
	PeminjamServiceDatabase peminjamService;
	// @Autowired
	// PeminjamanServiceDatabase peminjamanService;
	@Autowired
	BarangServiceDatabase barangService;

	@RequestMapping("/sipen/EditProfil/{id}")
	public String editProfilPeminjam(Model model, @PathVariable(value = "id") String id) {
		UserModel user = peminjamService.selectUserById(id);
		System.out.println("user " + user);
		model.addAttribute("user", user);
		return "formProfilPeminjam";
	}

	@PostMapping("/sipen/EditProfil/submit")
	public String updatePeminjamSubmit(Model model, RedirectAttributes redirectAttributes,
			@ModelAttribute UserModel peminjam) {
		System.out.println(peminjam);
		peminjamService.updateProfilPeminjam(peminjam);
		redirectAttributes.addFlashAttribute("sukses", "Data berhasil dirubah");
		return "redirect:/sipen/EditProfil/" + peminjam.id;
	}

	@RequestMapping("/sipen/LihatBarang")
	public String lihatListBarang(Model model) {
		List<BarangModel> listBarang = barangService.getAllBarangTersedia();
		System.out.println("listBarang " + listBarang);
		model.addAttribute("listBarang", listBarang);
		return "tableBarang";
	}

	@RequestMapping("/sipen/LihatPeminjaman/{id}")
	public String getPeminjamanbyID(Model model, @PathVariable(value = "id") String id) {
		PeminjamanModel peminjaman = peminjamService.getPeminjamanbyID(id);
		if (peminjaman != null) {
			System.out.println("peminjaman ga null " + peminjaman);
			List<BarangDipinjamModel> listBarangDipinjam = peminjaman.listBarangDipinjam;
			List<KonfirmasiPeminjamanModel> listKonfirmasi = peminjaman.listKonfirmasi;
			UserModel yangPinjam = peminjamService.selectUserById(peminjaman.idPeminjam);
			// String ler = peminjaman.tujuanPinjam;
			// System.out.println("ler " + ler);
			System.out.println("yg minjem siapa " + yangPinjam);
			System.out.println("listBarang ga null " + listBarangDipinjam);
			System.out.println("listKonfirm ga null " + listKonfirmasi);
			model.addAttribute("yangPinjam", yangPinjam);
			model.addAttribute("peminjaman", peminjaman);
			model.addAttribute("listBarangDipinjam", listBarangDipinjam);
			model.addAttribute("listKonfirmasi", listKonfirmasi);
			return "peminjaman";
		} else {
			return "redirect:/sipen";
		}
	}

	@RequestMapping("/sipen/TambahPeminjaman")
	public String buatPeminjaman(Model model) {
		return "formBuatPeminjaman";
	}

	@RequestMapping("/sipen/EditPeminjaman/{id}")
	public String editPeminjaman(Model model, @PathVariable(value = "id") String id) {
		PeminjamanModel peminjaman = peminjamService.getPeminjamanbyID(id);
		model.addAttribute("peminjaman", peminjaman);
		return "formEditPeminjaman";
	}

	@PostMapping("/sipen/EditPeminjaman/submit")
	public String editPeminjamanSubmit(Model model, RedirectAttributes redirectAttributes,
			@ModelAttribute PeminjamanModel peminjamanModel) {
		System.out.println("bibip bibip bibip ...  Peminjaman akan diedut");
		System.out.println("peminjaman " + peminjamanModel);
		peminjamService.editPeminjaman(peminjamanModel);
		redirectAttributes.addFlashAttribute("sukses", "Data berhasil dirubah");
		return "redirect:/sipen/EditPeminjaman/" + peminjamanModel.id;
	}

	@RequestMapping("/sipen/TambahkankePinjaman/{id}")
	public String tambahBarang(Model model, @PathVariable(value = "id") String id) {
		PeminjamanModel peminjaman = peminjamService.getPeminjamanbyID(id);
		List<BarangModel> barang = barangService.getAllBarangTersedia();
		model.addAttribute("barang", barang);
		return "formTambahBarangkePeminjaman";
	}
}
