-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2019 at 01:22 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sipen`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` int(10) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `tipe` varchar(255) NOT NULL,
  `tahun` smallint(4) NOT NULL,
  `harga_jamin` int(5) NOT NULL,
  `kuantitas` int(2) NOT NULL,
  `is_delete` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `nama_barang`, `tipe`, `tahun`, `harga_jamin`, `kuantitas`, `is_delete`) VALUES
(1, 'Pengukur Curah Hujan 1', 'A1', 2016, 10000, 3, 0),
(2, 'Pengukur Curah Hujan 2', 'A2', 2016, 10000, 3, 0),
(3, 'Pengukur Curah Hujan 3', 'A2', 2014, 15000, 4, 0),
(4, 'PH Indicator', 'Measurement', 2010, 15000, 5, 0),
(5, 'PH Indicator Digital', 'Measurement', 2017, 15000, 1, 0),
(6, 'PH Indicator Digital Tipe A3', 'Measurement', 2010, 15000, 2, 0),
(7, 'PH Indicator Non-Digital B-2603', 'Measurement', 2015, 15000, 2, 0),
(8, 'PH Indicator Tanah Non-Digital ', 'Measurement', 2009, 25000, 1, 0),
(9, 'PH Indicator Tanah Non-Digital B11', 'Measurement', 2007, 25000, 1, 0),
(10, 'Penggaris Besi 50 cm ', 'Measurement', 2017, 5000, 5, 0),
(11, 'Micrometer', 'Measurement', 2006, 25000, 2, 0),
(12, 'Measuring Tape', 'Measurement', 2012, 20000, 2, 0),
(13, 'Angle-lizer Besi', 'Measurement', 2014, 10000, 3, 0),
(14, 'Pengukur Kelembaban', 'Measurement', 2017, 20000, 3, 0),
(15, 'Anemometer Type A', 'A1', 2018, 20000, 2, 0),
(16, 'Anemometer Type B', 'A1', 2009, 15000, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `barang_dipinjam`
--

CREATE TABLE `barang_dipinjam` (
  `id` int(10) NOT NULL,
  `id_barang` int(10) NOT NULL,
  `id_peminjaman` int(10) NOT NULL,
  `kuantitas_dipinjam` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang_dipinjam`
--

INSERT INTO `barang_dipinjam` (`id`, `id_barang`, `id_peminjaman`, `kuantitas_dipinjam`) VALUES
(1, 1, 1, 1),
(2, 13, 1, 1),
(3, 7, 1, 2),
(4, 4, 2, 1),
(5, 10, 2, 1),
(6, 10, 3, 1),
(7, 3, 3, 1),
(8, 6, 3, 1),
(9, 9, 3, 1),
(10, 12, 3, 1),
(11, 15, 4, 1),
(12, 3, 4, 1),
(13, 13, 4, 1),
(14, 5, 5, 1),
(15, 8, 5, 0),
(16, 10, 6, 2),
(17, 9, 6, 1),
(18, 15, 7, 2),
(19, 2, 7, 3),
(20, 9, 8, 3),
(21, 11, 8, 1),
(22, 14, 8, 3),
(23, 10, 8, 3);

-- --------------------------------------------------------

--
-- Table structure for table `konfirmasi_peminjaman`
--

CREATE TABLE `konfirmasi_peminjaman` (
  `id` int(10) NOT NULL,
  `id_petugas` int(2) DEFAULT NULL,
  `id_dosen` int(2) DEFAULT NULL,
  `id_peminjaman` int(10) NOT NULL,
  `status_konfirmasi` enum('Dilaporkan','Disetujui','Ditolak','Selesai_Meminjam','Butuh_Permohonan_Pengantar','Barang_Diserahkan') DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konfirmasi_peminjaman`
--

INSERT INTO `konfirmasi_peminjaman` (`id`, `id_petugas`, `id_dosen`, `id_peminjaman`, `status_konfirmasi`, `keterangan`) VALUES
(9, 1, NULL, 1, 'Disetujui', 'oke'),
(10, NULL, 2, 1, 'Disetujui', 'ok'),
(11, 1, NULL, 2, 'Ditolak', 'barang kosong'),
(12, NULL, 1, 2, 'Ditolak', NULL),
(15, NULL, NULL, 3, 'Dilaporkan', NULL),
(16, NULL, NULL, 3, 'Dilaporkan', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` int(10) NOT NULL,
  `id_peminjam` int(10) NOT NULL,
  `tujuan_pinjam` enum('Kuliah Lapang','Praktikum','Survey TA','Penelitian') NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `tempat_peminjaman` varchar(255) NOT NULL,
  `tanggal_pinjam` date NOT NULL,
  `tanggal_pengembalian` date NOT NULL,
  `tanggal_pengembalian_barang` date DEFAULT NULL,
  `total_harga_jaminan` int(6) NOT NULL,
  `tanggal_perubahan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `id_peminjam`, `tujuan_pinjam`, `deskripsi`, `tempat_peminjaman`, `tanggal_pinjam`, `tanggal_pengembalian`, `tanggal_pengembalian_barang`, `total_harga_jaminan`, `tanggal_perubahan`) VALUES
(1, 1, 'Kuliah Lapang', 'untuk kuliah lapang', 'fasilkom', '2018-12-01', '2018-12-05', NULL, 0, '2018-12-01'),
(2, 2, 'Praktikum', 'untuk praktikum mata kuliah sig', 'rumah', '2018-12-02', '2018-12-07', NULL, 0, '2018-12-02'),
(3, 3, 'Penelitian', 'untuk penelitian', 'lab geo', '2018-12-01', '2018-12-04', NULL, 0, '2018-12-02'),
(4, 1, 'Survey TA', 'untuk survey ta', 'fasilkom gedung baru', '2018-12-11', '2018-12-15', NULL, 10000, '2018-12-01'),
(5, 1, 'Praktikum', 'untuk praktikum bersama teman', 'fasilkom gedung baru', '2018-11-11', '2018-11-15', NULL, 20000, '2018-12-01'),
(6, 4, 'Kuliah Lapang', 'kuliah lapang', 'lapangan', '2019-01-01', '2019-01-09', NULL, 0, '0000-00-00'),
(7, 5, 'Kuliah Lapang', 'kuliah', 'rotunda', '2018-12-03', '2018-12-11', NULL, 0, '0000-00-00'),
(8, 7, 'Praktikum', 'praktikum di teknik', 'praktikum di teknik', '2018-12-10', '2018-12-14', NULL, 0, '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `npm` int(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fakultas` varchar(250) NOT NULL,
  `jurusan` varchar(250) NOT NULL,
  `role` enum('mahasiswa','dosen','petugas_lab','') NOT NULL,
  `instansi` varchar(255) DEFAULT NULL,
  `telepon` varchar(13) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `npm`, `nama`, `username`, `password`, `fakultas`, `jurusan`, `role`, `instansi`, `telepon`, `alamat`) VALUES
(1, 1406623524, 'Iman Alfathan Yudha', 'iman.alfathan', '1234', 'Ilmu Komputer', 'Ilmu Komputer', 'mahasiswa', 'Fakultas Ilmu Komputer Universitas Indonesia', '081383344874', 'Jln Baitussalam No.73 RT 08/RW 04 Beji, Depok'),
(2, 1406533201, 'Alfathan Yuda', 'yudha12', '1234', 'Ilmu Komputer', 'Sistem Informasi', 'mahasiswa', 'Fakultas Geografi UI', '0218600823', 'Rumah'),
(3, 1306433201, 'Iman Yudha', 'ahduy12', '1234', 'Tehnik', 'Tehnik Elektro', 'mahasiswa', 'Fakultas Ilmu Komputer UI', '0217755432', 'Jln Baitussalam. Dekat gapura dan depot aqua'),
(4, 1964, 'Eko Kusratmoko', 'eko', '1234', 'MIPA', 'Geografi', 'dosen', NULL, NULL, NULL),
(5, 1960, 'Supriatna', 'supriatna', '1234', 'MIPA', 'Geografi', 'dosen', NULL, NULL, NULL),
(6, 1980, 'Awal', 'awal', '1234', 'MIPA', 'Geografi', 'petugas_lab', NULL, NULL, NULL),
(7, 19640606, 'Guntoro', 'gun', '1234', 'Fakultas Tehnik', 'Tehnik Elektro', 'petugas_lab', 'Fakultas Tehnik UI', '0812345678', 'Jalan Mawar Raya');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barang_dipinjam`
--
ALTER TABLE `barang_dipinjam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_peminjaman` (`id_peminjaman`);

--
-- Indexes for table `konfirmasi_peminjaman`
--
ALTER TABLE `konfirmasi_peminjaman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_peminjaman` (`id_peminjaman`),
  ADD KEY `konfirmasi_peminjaman_ibfk_1` (`id_dosen`),
  ADD KEY `konfirmasi_peminjaman_ibfk_2` (`id_petugas`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_peminjam` (`id_peminjam`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `npm` (`npm`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `barang_dipinjam`
--
ALTER TABLE `barang_dipinjam`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `konfirmasi_peminjaman`
--
ALTER TABLE `konfirmasi_peminjaman`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang_dipinjam`
--
ALTER TABLE `barang_dipinjam`
  ADD CONSTRAINT `barang_dipinjam_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`),
  ADD CONSTRAINT `barang_dipinjam_ibfk_2` FOREIGN KEY (`id_peminjaman`) REFERENCES `peminjaman` (`id`);

--
-- Constraints for table `konfirmasi_peminjaman`
--
ALTER TABLE `konfirmasi_peminjaman`
  ADD CONSTRAINT `konfirmasi_peminjaman_ibfk_1` FOREIGN KEY (`id_dosen`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `konfirmasi_peminjaman_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `konfirmasi_peminjaman_ibfk_3` FOREIGN KEY (`id_peminjaman`) REFERENCES `peminjaman` (`id`);

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`id_peminjam`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
