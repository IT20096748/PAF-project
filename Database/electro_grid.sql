-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 08:00 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electro_grid`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billID` varchar(20) NOT NULL,
  `electricityAccountNo` int(11) NOT NULL,
  `meterID` int(11) NOT NULL,
  `month` varchar(20) NOT NULL,
  `issuedDate` varchar(40) NOT NULL,
  `unitsConsumed` int(11) NOT NULL,
  `chargeForUnitsconsumed` double NOT NULL,
  `fixedCharge` double NOT NULL,
  `totalCostOfSupply` decimal(10,0) NOT NULL,
  `startedUnits` int(11) NOT NULL,
  `endedUnits` int(11) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billID`, `electricityAccountNo`, `meterID`, `month`, `issuedDate`, `unitsConsumed`, `chargeForUnitsconsumed`, `fixedCharge`, `totalCostOfSupply`, `startedUnits`, `endedUnits`, `status`) VALUES
('220426224337', 236578654, 45635, 'Apr-2022', '26-Apr-2022 22:43:37', 500, 17461, 480, '17941', 700, 1200, 'Not Paid');

-- --------------------------------------------------------

--
-- Table structure for table `consumer`
--

CREATE TABLE `consumer` (
  `consumerno` int(11) NOT NULL,
  `accountNo` int(11) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `occupation` varchar(10) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `province` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `street` varchar(20) NOT NULL,
  `postalCode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumer`
--

INSERT INTO `consumer` (`consumerno`, `accountNo`, `firstName`, `lastName`, `gender`, `occupation`, `phoneNumber`, `email`, `password`, `province`, `city`, `street`, `postalCode`) VALUES
(1, 236578654, 'W.A.K.B. Dasanayaka', 'malki', 'female', 'teacher', 769903902, 'Shanaya123@gmail.com', '12345', 'Western', 'colombo', 'mawatha', 10);

-- --------------------------------------------------------

--
-- Table structure for table `interrupts`
--

CREATE TABLE `interrupts` (
  `interruptID` int(11) NOT NULL,
  `interruptArea` varchar(50) NOT NULL,
  `interruptStartTime` varchar(50) NOT NULL,
  `interruptEndTime` varchar(50) NOT NULL,
  `interruptHours` varchar(50) NOT NULL,
  `interruptMessage` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interrupts`
--

INSERT INTO `interrupts` (`interruptID`, `interruptArea`, `interruptStartTime`, `interruptEndTime`, `interruptHours`, `interruptMessage`) VALUES
(1, 'Colombo', '10:00 a.m', '13:00 p.m', '3 hours', 'Due to an essensial work in this area'),
(3, 'Kurunegala', '11:00 a.m', '13:00 p.m', '2 hours', 'Due to less amount of petrol'),
(4, 'Kadana', '11:30 a.m', '15:30 p.m', '6 hours', 'Due to less amount of raining'),
(5, 'Matara', '07:30 a.m', '10:00 a.m', '2.5 hours', 'Due to less amount of water');

-- --------------------------------------------------------

--
-- Table structure for table `meter`
--

CREATE TABLE `meter` (
  `meterID` int(11) NOT NULL,
  `meterCode` varchar(10) NOT NULL,
  `premisesID` varchar(7) NOT NULL,
  `electricityAccountNo` int(10) NOT NULL,
  `manufactureDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `meter`
--

INSERT INTO `meter` (`meterID`, `meterCode`, `premisesID`, `electricityAccountNo`, `manufactureDate`) VALUES
(45635, '76348', 'PGH4326', 236578654, '2008-04-04');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` varchar(12) NOT NULL,
  `electricityAccountNo` int(10) NOT NULL,
  `billID` int(11) NOT NULL,
  `paymentDateTime` varchar(30) NOT NULL,
  `paymentAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `consumer`
--
ALTER TABLE `consumer`
  ADD PRIMARY KEY (`consumerno`);

--
-- Indexes for table `interrupts`
--
ALTER TABLE `interrupts`
  ADD PRIMARY KEY (`interruptID`);

--
-- Indexes for table `meter`
--
ALTER TABLE `meter`
  ADD PRIMARY KEY (`meterID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consumer`
--
ALTER TABLE `consumer`
  MODIFY `consumerno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `interrupts`
--
ALTER TABLE `interrupts`
  MODIFY `interruptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
