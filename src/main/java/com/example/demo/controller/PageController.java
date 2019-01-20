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

import com.example.demo.model.UserModel;
import com.example.demo.model.BarangDipinjamModel;
import com.example.demo.model.BarangModel;
import com.example.demo.model.KonfirmasiPeminjamanModel;
import com.example.demo.model.PeminjamanModel;
import com.example.demo.service.BarangServiceDatabase;
import com.example.demo.service.PeminjamServiceDatabase;

@Controller
public class PageController {
	@Autowired
	PeminjamServiceDatabase peminjamService;

	@Autowired
	BarangServiceDatabase barangService;

	@RequestMapping("/login")
	public String login(Model model) {
		// erModel usermodel =
		// peminjamService.selectUserByUsername(principal.getName());
		// if (principal != null) {
		// System.out.println("principal tidak null "+principal);
		// return "redirect:/";
		// }
		// System.out.println("principal apa isinya, ler "+principal);
		return "login";
	}

	@RequestMapping("/sipen")
	public String index(Model model) {
		// UserModel usermodel =
		// peminjamService.selectUserByUsername(principal.getName());
		// if(usermodel == null) {
		// return "redirect:/logout";
		// }
		List<PeminjamanModel> listPeminjaman = peminjamService.getAllPeminjaman();
		System.out.println("listPeminjaman ada" + listPeminjaman);
		// model.addAttribute("usermodel", usermodel);
		model.addAttribute("listPeminjaman", listPeminjaman);
		return "index";
	}

	@RequestMapping("/sipen/adm")
	public String indexNonPeminjam(Model model) {
		List<PeminjamanModel> listPeminjaman = peminjamService.getAllPeminjaman();
		System.out.println("tes tes " + listPeminjaman);
		model.addAttribute("listPeminjaman", listPeminjaman);
		return "index2";
	}

	@RequestMapping("/sipen/TambahBarang")
	public String tambahBarang(Model model) {
		BarangModel barangModel = new BarangModel();
		model.addAttribute("barangModel", barangModel);
		return "formTambahBarang";
	}

	@PostMapping("/sipen/TambahBarang/submit")
	public String tambahBarangSubmit(Model model, @ModelAttribute BarangModel barangModel) {
		System.out.println("barang " + barangModel);
		barangService.addBarang(barangModel);
		return "redirect:/sipen/LihatBarang";
	}

	@RequestMapping("/sipen/EditBarang/{id}")
	public String editBarang(Model model, @PathVariable(value = "id") String id) {
		BarangModel barang = barangService.getBarangById(id);
		System.out.println("tes tes barnag" + barang);
		model.addAttribute("barang", barang);
		return "formEditBarang";
	}

	@PostMapping("/sipen/EditBarang/submit")
	public String editBarangSubmit(Model model, RedirectAttributes redirectAttributes,
			@ModelAttribute BarangModel barangModel) {
		System.out.println("barang " + barangModel);
		barangService.editBarang(barangModel);
		redirectAttributes.addFlashAttribute("sukses", "Data berhasil dirubah");
		return "redirect:/sipen/EditBarang/" + barangModel.id;
	}

	@RequestMapping("/sipen/HapusBarang/{id}")
	public String hapusBarang(Model model, @PathVariable(value = "id") String id) {
		BarangModel barang = barangService.getBarangById(id);
		System.out.println("barang yang mau diapus " + barang);
		if (barang != null) {
			System.out.println("if barang ga null " + barang);
			barangService.deleteBarang(barang);
			return "redirect:/sipen/LihatBarang";
		}
		return "redirect:/sipen";
	}

	@RequestMapping("/sipen/KonfirmasiPeminjaman/{id}")
	public String getPeminjamanbyIDAdm(Model model, @PathVariable(value = "id") String id) {
		// PeminjamanModel peminjaman = peminjamService.getPeminjamanbyID(id);
		KonfirmasiPeminjamanModel konfirmasiPeminjaman = peminjamService.getKonfirmasiPeminjamanbyID(id);
		if (konfirmasiPeminjaman != null) {
			System.out.println("ga null " + konfirmasiPeminjaman);
			model.addAttribute("konfirmasiPeminjaman", konfirmasiPeminjaman);
			model.addAttribute("yangPinjam", konfirmasiPeminjaman.peminjaman.userPeminjam);
			model.addAttribute("listBarangDipinjam", konfirmasiPeminjaman.peminjaman.listBarangDipinjam);
			model.addAttribute("peminjaman", konfirmasiPeminjaman.peminjaman);
			return "formUbahStatusPeminjaman";
		} else {
			return "redirect:/sipen";
		}
	}

	@PostMapping("/sipen/KonfirmasiPeminjaman/submit")
	public String editKonfirmasiPeminjamanSubmit(Model model, RedirectAttributes redirectAttributes,
			@ModelAttribute KonfirmasiPeminjamanModel konfirmasiPeminjaman) {
		peminjamService.editKonfirmasi(konfirmasiPeminjaman);
		System.out.println("konfirmasi " + konfirmasiPeminjaman.id + ": "
				+ peminjamService.getKonfirmasiPeminjamanbyID(konfirmasiPeminjaman.id.toString()));
		redirectAttributes.addFlashAttribute("sukses", "Data berhasil dirubah");
		return "redirect:/sipen/adm/";
	}

}
