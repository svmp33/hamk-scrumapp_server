-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2018 at 04:24 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaprog`
--

-- --------------------------------------------------------

--
-- Table structure for table `temp_bed1`
--

CREATE TABLE `temp_bed1` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `value` decimal(3,1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `temp_bed1`
--

INSERT INTO `temp_bed1` (`id`, `date`, `value`) VALUES
(1, '2018-01-29 18:26:45', '23.0'),
(2, '2018-01-29 18:27:09', '25.1'),
(3, '2018-01-29 18:27:37', '40.2'),
(4, '2018-01-29 18:27:51', '8.2'),
(5, '2018-01-30 15:18:32', '20.1');

-- --------------------------------------------------------

--
-- Table structure for table `temp_bed2`
--

CREATE TABLE `temp_bed2` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `value` decimal(3,1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `temp_bed2`
--

INSERT INTO `temp_bed2` (`id`, `date`, `value`) VALUES
(1, '2018-01-29 18:26:45', '23.0'),
(2, '2018-01-29 18:27:09', '25.1'),
(3, '2018-01-29 18:27:37', '40.2'),
(4, '2018-01-29 18:27:51', '8.2'),
(5, '2018-01-30 15:18:32', '20.1');

-- --------------------------------------------------------

--
-- Table structure for table `temp_hall`
--

CREATE TABLE `temp_hall` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `value` decimal(3,1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `temp_hall`
--

INSERT INTO `temp_hall` (`id`, `date`, `value`) VALUES
(1, '2018-01-29 18:26:45', '23.0'),
(2, '2018-01-29 18:27:09', '25.1'),
(3, '2018-01-29 18:27:37', '40.2'),
(4, '2018-01-29 18:27:51', '8.2'),
(5, '2018-01-30 15:18:32', '20.1');

-- --------------------------------------------------------

--
-- Table structure for table `temp_kitchen`
--

CREATE TABLE `temp_kitchen` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `value` decimal(3,1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `temp_kitchen`
--

INSERT INTO `temp_kitchen` (`id`, `date`, `value`) VALUES
(1, '2018-01-29 18:26:45', '23.0'),
(2, '2018-01-29 18:27:09', '25.1'),
(3, '2018-01-29 18:27:37', '40.2'),
(4, '2018-01-29 18:27:51', '8.2'),
(5, '2018-01-30 15:18:32', '20.1');

-- --------------------------------------------------------

--
-- Table structure for table `temp_living`
--

CREATE TABLE `temp_living` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `value` decimal(3,1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `temp_living`
--

INSERT INTO `temp_living` (`id`, `date`, `value`) VALUES
(1, '2018-01-29 18:26:45', '23.0'),
(2, '2018-01-29 18:27:09', '25.1'),
(3, '2018-01-29 18:27:37', '40.2'),
(4, '2018-01-29 18:27:51', '8.2'),
(5, '2018-01-30 15:18:32', '20.1');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `ID` bigint(20) NOT NULL,
  `user` varchar(12) NOT NULL,
  `pass` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`ID`, `user`, `pass`) VALUES
(1, 'admin', 'trololol'),
(2, 'kentsu', 'trololol');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `temp_bed1`
--
ALTER TABLE `temp_bed1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `temp_bed2`
--
ALTER TABLE `temp_bed2`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `temp_hall`
--
ALTER TABLE `temp_hall`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `temp_kitchen`
--
ALTER TABLE `temp_kitchen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `temp_living`
--
ALTER TABLE `temp_living`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `temp_bed1`
--
ALTER TABLE `temp_bed1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `temp_bed2`
--
ALTER TABLE `temp_bed2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `temp_hall`
--
ALTER TABLE `temp_hall`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `temp_kitchen`
--
ALTER TABLE `temp_kitchen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `temp_living`
--
ALTER TABLE `temp_living`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
