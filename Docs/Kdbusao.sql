-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 07, 2016 at 04:54 PM
-- Server version: 5.7.13-0ubuntu0.16.04.2
-- PHP Version: 7.0.8-0ubuntu0.16.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Kdbusao`
--

-- --------------------------------------------------------

--
-- Table structure for table `horarioSaida`
--

CREATE TABLE `horarioSaida` (
  `idhorarioSaida` int(11) NOT NULL,
  `idlinha` int(11) NOT NULL,
  `localdePartida` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `idintervaloSaida` int(11) NOT NULL,
  `horarioInicio` timestamp NULL DEFAULT NULL,
  `horarioFim` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `intervaloSaida`
--

CREATE TABLE `intervaloSaida` (
  `idintervaloSaida` int(11) NOT NULL,
  `intervaloMinuto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `linha`
--

CREATE TABLE `linha` (
  `idlinha` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `linha`
--

INSERT INTO `linha` (`idlinha`, `nome`) VALUES
(1, 'T131');

-- --------------------------------------------------------

--
-- Table structure for table `onibus`
--

CREATE TABLE `onibus` (
  `idonibus` int(11) NOT NULL,
  `idlinha` int(11) NOT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `dataHora` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `onibus`
--

INSERT INTO `onibus` (`idonibus`, `idlinha`, `latitude`, `longitude`, `dataHora`) VALUES
(2510, 1, -18.9154, -48.2746, NULL),
(2511, 1, -18.9159, -48.2698, NULL),
(2512, 1, -18.9123, -48.2655, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pontoParada`
--

CREATE TABLE `pontoParada` (
  `idpontoParada` int(11) NOT NULL,
  `idlinha` int(11) NOT NULL,
  `latitude` varchar(25) NOT NULL,
  `longitude` varchar(25) NOT NULL,
  `endereco` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pontoParada`
--

INSERT INTO `pontoParada` (`idpontoParada`, `idlinha`, `latitude`, `longitude`, `endereco`) VALUES
(1, 1, '-18.913655', '-48.275567', 'Terminal Central'),
(2, 1, '-18.916333', '-48.271684', 'Estação 1'),
(3, 1, '-18.912611', '-48.266037', 'Estação 2'),
(4, 1, '-18.912083', '-48.265281', 'Estação 3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `horarioSaida`
--
ALTER TABLE `horarioSaida`
  ADD PRIMARY KEY (`idhorarioSaida`,`idlinha`),
  ADD KEY `idintervaloSaida` (`idintervaloSaida`),
  ADD KEY `idlinha` (`idlinha`);

--
-- Indexes for table `intervaloSaida`
--
ALTER TABLE `intervaloSaida`
  ADD PRIMARY KEY (`idintervaloSaida`);

--
-- Indexes for table `linha`
--
ALTER TABLE `linha`
  ADD PRIMARY KEY (`idlinha`);

--
-- Indexes for table `onibus`
--
ALTER TABLE `onibus`
  ADD PRIMARY KEY (`idonibus`,`idlinha`),
  ADD KEY `idlinha_idx` (`idlinha`);

--
-- Indexes for table `pontoParada`
--
ALTER TABLE `pontoParada`
  ADD PRIMARY KEY (`idpontoParada`,`idlinha`),
  ADD KEY `idlinha` (`idlinha`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `horarioSaida`
--
ALTER TABLE `horarioSaida`
  MODIFY `idhorarioSaida` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `intervaloSaida`
--
ALTER TABLE `intervaloSaida`
  MODIFY `idintervaloSaida` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `linha`
--
ALTER TABLE `linha`
  MODIFY `idlinha` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `pontoParada`
--
ALTER TABLE `pontoParada`
  MODIFY `idpontoParada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `horarioSaida`
--
ALTER TABLE `horarioSaida`
  ADD CONSTRAINT `horarioSaida_ibfk_1` FOREIGN KEY (`idintervaloSaida`) REFERENCES `intervaloSaida` (`idintervaloSaida`),
  ADD CONSTRAINT `horarioSaida_ibfk_2` FOREIGN KEY (`idlinha`) REFERENCES `linha` (`idlinha`);

--
-- Constraints for table `onibus`
--
ALTER TABLE `onibus`
  ADD CONSTRAINT `idlinha` FOREIGN KEY (`idlinha`) REFERENCES `linha` (`idlinha`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pontoParada`
--
ALTER TABLE `pontoParada`
  ADD CONSTRAINT `pontoParada_ibfk_1` FOREIGN KEY (`idlinha`) REFERENCES `linha` (`idlinha`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
