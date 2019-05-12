-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 12-05-2019 a las 18:23:18
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `SanJorge`
--
CREATE DATABASE IF NOT EXISTS `SanJorge` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `SanJorge`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `applications`
--
-- Creación: 11-05-2019 a las 21:22:10
--

DROP TABLE IF EXISTS `applications`;
CREATE TABLE `applications` (
  `users_id_user` int(11) NOT NULL,
  `job_offers_id_job_offer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--
-- Creación: 11-05-2019 a las 21:22:09
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`category_id`, `name`) VALUES
(1, 'Ventas'),
(2, 'Almacen / Logistica / transporte'),
(3, 'Call Center / Telemercadeo'),
(4, 'Produccion / Operarios / Manufactura'),
(5, 'Administracion / oficina'),
(6, 'Atencion a clientes'),
(7, 'Mantenimiento y Reparaciones'),
(8, 'Informatica y telecomunicaciones'),
(9, 'Medicina / Salud'),
(10, 'Servicios generales / Aseo / Seguridad'),
(11, 'Contabilidad / Finanzas'),
(12, 'Construccion y obra'),
(13, 'Hoteleria / Turismo'),
(14, 'Ingenieria'),
(15, 'Recursos Humanos'),
(16, 'Mercadotecnia / Publicidad'),
(17, 'Comunicacion'),
(18, 'Docencia'),
(19, 'Investigacion y calidad'),
(20, 'Diseño y artes graficas'),
(21, 'Compras / Comercio Exterior'),
(22, 'Legal / Asesoria'),
(23, 'Dirección / Gerencia'),
(24, 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `companies`
--
-- Creación: 11-05-2019 a las 21:22:09
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE `companies` (
  `id_company` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `description` text COLLATE utf8_spanish_ci,
  `webpage` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone_number` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `nit` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `identification_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `education`
--
-- Creación: 11-05-2019 a las 21:22:11
--

DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `id_education` int(11) NOT NULL,
  `institution` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `educational_levels_id_educational_level` int(11) NOT NULL,
  `users_id_user` int(11) NOT NULL,
  `status` enum('culminado','cursando','abandonado') COLLATE utf8_spanish_ci NOT NULL,
  `entry_date` date NOT NULL,
  `departure_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `educational_levels`
--
-- Creación: 11-05-2019 a las 21:22:11
--

DROP TABLE IF EXISTS `educational_levels`;
CREATE TABLE `educational_levels` (
  `id_educational_level` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `educational_levels`
--

INSERT INTO `educational_levels` (`id_educational_level`, `name`) VALUES
(1, 'Educación Basica Primaria'),
(2, 'Educación Basica Secundaria'),
(3, 'Bachillerato / Educacion Media'),
(4, 'Universidad / Carrera Técnica'),
(5, 'Universidad / Carrera Tecnológica'),
(6, 'Universidad / Carrera Profesional'),
(7, 'Postgrado / Especialización'),
(8, 'Postgrado / Maestria'),
(9, 'Postgrado / Doctorado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `job_offers`
--
-- Creación: 11-05-2019 a las 21:22:10
--

DROP TABLE IF EXISTS `job_offers`;
CREATE TABLE `job_offers` (
  `id_job_offer` int(11) NOT NULL,
  `category_category_id` int(11) NOT NULL,
  `companies_id_company` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `working_day` enum('tiempo completo','tiempo parcial','por horas','desde casa') COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--
-- Creación: 12-05-2019 a las 00:11:40
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `first_name` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `second_name` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `first_surname` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `second_surname` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `indentification_number` int(11) NOT NULL,
  `gender` enum('woman','man') COLLATE utf8_spanish_ci NOT NULL,
  `birthdate` date NOT NULL,
  `civil_status` enum('single','married','divorced','widower') COLLATE utf8_spanish_ci DEFAULT NULL,
  `address` varchar(245) COLLATE utf8_spanish_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `photo` blob,
  `profile` longtext COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `work_experincie`
--
-- Creación: 11-05-2019 a las 21:22:11
--

DROP TABLE IF EXISTS `work_experincie`;
CREATE TABLE `work_experincie` (
  `id_work_experincie` int(11) NOT NULL,
  `company` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `cargo` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `category_category_id` int(11) NOT NULL,
  `users_id_user` int(11) NOT NULL,
  `entry_date` date NOT NULL,
  `departure_date` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`users_id_user`,`job_offers_id_job_offer`),
  ADD KEY `fk_users_has_job_offers_job_offers1_idx` (`job_offers_id_job_offer`),
  ADD KEY `fk_users_has_job_offers_users1_idx` (`users_id_user`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indices de la tabla `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id_company`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indices de la tabla `education`
--
ALTER TABLE `education`
  ADD PRIMARY KEY (`id_education`,`educational_levels_id_educational_level`,`users_id_user`),
  ADD KEY `fk_education_educational_levels1_idx` (`educational_levels_id_educational_level`),
  ADD KEY `fk_education_users1_idx` (`users_id_user`);

--
-- Indices de la tabla `educational_levels`
--
ALTER TABLE `educational_levels`
  ADD PRIMARY KEY (`id_educational_level`);

--
-- Indices de la tabla `job_offers`
--
ALTER TABLE `job_offers`
  ADD PRIMARY KEY (`id_job_offer`,`category_category_id`,`companies_id_company`),
  ADD KEY `fk_job_offers_category_idx` (`category_category_id`),
  ADD KEY `fk_job_offers_companies1_idx` (`companies_id_company`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indices de la tabla `work_experincie`
--
ALTER TABLE `work_experincie`
  ADD PRIMARY KEY (`id_work_experincie`,`category_category_id`,`users_id_user`),
  ADD KEY `fk_work_experincie_category1_idx` (`category_category_id`),
  ADD KEY `fk_work_experincie_users1_idx` (`users_id_user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `applications`
--
ALTER TABLE `applications`
  MODIFY `users_id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `companies`
--
ALTER TABLE `companies`
  MODIFY `id_company` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `education`
--
ALTER TABLE `education`
  MODIFY `id_education` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `educational_levels`
--
ALTER TABLE `educational_levels`
  MODIFY `id_educational_level` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `job_offers`
--
ALTER TABLE `job_offers`
  MODIFY `id_job_offer` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `work_experincie`
--
ALTER TABLE `work_experincie`
  MODIFY `id_work_experincie` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `fk_users_has_job_offers_job_offers1` FOREIGN KEY (`job_offers_id_job_offer`) REFERENCES `job_offers` (`id_job_offer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_has_job_offers_users1` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `education`
--
ALTER TABLE `education`
  ADD CONSTRAINT `fk_education_educational_levels1` FOREIGN KEY (`educational_levels_id_educational_level`) REFERENCES `educational_levels` (`id_educational_level`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_education_users1` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `job_offers`
--
ALTER TABLE `job_offers`
  ADD CONSTRAINT `fk_job_offers_category` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_job_offers_companies1` FOREIGN KEY (`companies_id_company`) REFERENCES `companies` (`id_company`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `work_experincie`
--
ALTER TABLE `work_experincie`
  ADD CONSTRAINT `fk_work_experincie_category1` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_work_experincie_users1` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
