-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2026 at 06:49 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sky_survey_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` bigint(20) NOT NULL,
  `answer_text` text DEFAULT NULL,
  `question_id` bigint(20) NOT NULL,
  `response_id` bigint(20) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `stored_file_name` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `answer_text`, `question_id`, `response_id`, `file_name`, `stored_file_name`, `content_type`, `file_size`) VALUES
(40, 'michael mwangi', 18, 13, NULL, NULL, NULL, NULL),
(41, 'mwangi@gmail.com', 19, 13, NULL, NULL, NULL, NULL),
(42, 'Backend', 20, 13, NULL, NULL, NULL, NULL),
(43, 'neutral', 21, 13, NULL, NULL, NULL, NULL),
(44, 'VS code,Intellij', 22, 13, NULL, NULL, NULL, NULL),
(45, 'internet problems ,installations', 23, 13, NULL, NULL, NULL, NULL),
(46, '', 24, 13, NULL, NULL, NULL, NULL),
(47, 'Ochieng', 25, 14, NULL, NULL, NULL, NULL),
(48, 'ochieng@gmail.com', 26, 14, NULL, NULL, NULL, NULL),
(49, 'Naivas', 27, 14, NULL, NULL, NULL, NULL),
(50, 'Weekly', 28, 14, NULL, NULL, NULL, NULL),
(51, '2026-07-08', 29, 14, NULL, NULL, NULL, NULL),
(52, 'sales', 32, 14, NULL, NULL, NULL, NULL),
(53, 'customers communication and time management', 31, 14, NULL, NULL, NULL, NULL),
(54, '', 30, 14, NULL, NULL, NULL, NULL),
(55, 'mary', 33, 15, NULL, NULL, NULL, NULL),
(56, 'Embu.ac.ke', 34, 15, NULL, NULL, NULL, NULL),
(57, 'Embu', 35, 15, NULL, NULL, NULL, NULL),
(58, 'IT', 36, 15, NULL, NULL, NULL, NULL),
(59, 'software development,java,react and springboot', 37, 15, NULL, NULL, NULL, NULL),
(60, '2026-07-20', 38, 15, NULL, NULL, NULL, NULL),
(61, '', 39, 15, NULL, NULL, NULL, NULL),
(62, 'sheemah', 33, 16, NULL, NULL, NULL, NULL),
(63, 'embu@gmail.com', 34, 16, NULL, NULL, NULL, NULL),
(64, 'embu', 35, 16, NULL, NULL, NULL, NULL),
(65, 'IT', 36, 16, NULL, NULL, NULL, NULL),
(66, 'software development,java,react', 37, 16, NULL, NULL, NULL, NULL),
(67, '2026-07-20', 38, 16, NULL, NULL, NULL, NULL),
(68, '1783872182608_mary.cirtificate.pdf', 39, 16, NULL, NULL, NULL, NULL),
(69, 'danson', 47, 17, NULL, NULL, NULL, NULL),
(70, 'danson@gmail.com', 48, 17, NULL, NULL, NULL, NULL),
(71, '254769887988', 49, 17, NULL, NULL, NULL, NULL),
(72, 'Software Engineer', 50, 17, NULL, NULL, NULL, NULL),
(73, 'Bachelor\'s', 51, 17, NULL, NULL, NULL, NULL),
(74, 'Java,React,Spring Boot', 52, 17, NULL, NULL, NULL, NULL),
(75, '2026-07-22', 53, 17, NULL, NULL, NULL, NULL),
(76, '1783873217613_danson.certificate.pdf', 54, 17, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `certificates`
--

CREATE TABLE `certificates` (
  `id` bigint(20) NOT NULL,
  `response_id` bigint(20) NOT NULL,
  `file_name` varchar(500) NOT NULL,
  `file_path` varchar(1000) NOT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `uploaded_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` bigint(20) NOT NULL,
  `survey_id` bigint(20) NOT NULL,
  `required` tinyint(1) DEFAULT 1,
  `order_number` int(11) NOT NULL,
  `question_text` varchar(255) NOT NULL,
  `question_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `survey_id`, `required`, `order_number`, `question_text`, `question_type`) VALUES
(18, 11, 1, 1, 'Full Name', 'TEXT'),
(19, 11, 1, 2, 'Email Address', 'EMAIL'),
(20, 11, 1, 3, 'department', 'DROPDOWN'),
(21, 11, 1, 4, 'how satisfied are you', 'RADIO'),
(22, 11, 0, 5, 'Which development tools do you use regularly?', 'CHECKBOX'),
(23, 11, 1, 6, 'What challenges do you face during development?', 'TEXTAREA'),
(24, 11, 1, 7, 'upload your cirtificate', 'FILE_UPLOAD'),
(25, 12, 1, 1, 'Customer Name', 'TEXT'),
(26, 12, 1, 2, 'Email Address', 'EMAIL'),
(27, 12, 1, 3, 'Company Name', 'TEXT'),
(28, 12, 1, 4, 'How often do you use our services?', 'RADIO'),
(29, 12, 1, 5, 'Visit Date', 'DATE'),
(30, 12, 1, 8, 'upload your business certificate', 'FILE_UPLOAD'),
(31, 12, 1, 7, 'What improvements would you like to see?', 'TEXTAREA'),
(32, 12, 1, 6, 'Which services do you use?', 'CHECKBOX'),
(33, 13, 1, 1, 'Student Name', 'TEXT'),
(34, 13, 1, 2, 'University Email', 'EMAIL'),
(35, 13, 1, 3, 'University Name', 'TEXT'),
(36, 13, 1, 4, 'Internship Department', 'CHECKBOX'),
(37, 13, 1, 5, 'Which skills did you improve?', 'TEXTAREA'),
(38, 13, 1, 6, 'When are you ready for internship', 'DATE'),
(39, 13, 1, 7, 'upload your cirtificate', 'FILE_UPLOAD'),
(40, 14, 1, 1, 'Employee Name', 'TEXT'),
(41, 14, 1, 2, 'Work Email', 'EMAIL'),
(42, 14, 1, 3, 'Department', 'DROPDOWN'),
(43, 14, 1, 4, 'Years of Experience', 'NUMBER'),
(44, 14, 1, 5, 'Which training programs interest you?', 'RADIO'),
(45, 14, 1, 6, 'Preferred Training Date', 'DATE'),
(46, 14, 1, 7, 'upload your cirtificate', 'FILE_UPLOAD'),
(47, 15, 1, 1, 'Full Name', 'TEXT'),
(48, 15, 1, 2, 'Email Address', 'EMAIL'),
(49, 15, 0, 3, 'Phone Number', 'NUMBER'),
(50, 15, 1, 4, 'Position Applying For', 'DROPDOWN'),
(51, 15, 1, 5, 'Highest Education Level', 'RADIO'),
(52, 15, 1, 6, 'Technical Skills', 'CHECKBOX'),
(53, 15, 1, 7, 'Available Start Date', 'DATE'),
(54, 15, 1, 8, 'upload your cirtificate', 'FILE_UPLOAD'),
(55, 16, 1, 1, 'Student Name', 'TEXT'),
(56, 16, 1, 2, 'Student Email', 'EMAIL'),
(57, 16, 1, 3, 'Faculty', 'CHECKBOX'),
(58, 16, 1, 4, 'Year of Study', 'RADIO'),
(59, 16, 1, 5, 'Which campus facilities do you use?', 'CHECKBOX'),
(60, 16, 1, 6, 'What improvements would you recommend?', 'TEXTAREA'),
(61, 16, 1, 7, 'University Name', 'TEXT'),
(62, 17, 1, 1, 'Participant Name', 'TEXT'),
(63, 17, 1, 2, 'Email Address', 'EMAIL'),
(64, 17, 1, 3, 'Workshop Attended', 'TEXT'),
(65, 17, 1, 4, 'Was the workshop relevant to your work?', 'RADIO'),
(66, 17, 1, 5, 'Additional comments', 'TEXTAREA'),
(67, 17, 1, 6, 'Workshop Date', 'DATE'),
(68, 18, 1, 1, 'Full Name', 'TEXT'),
(69, 18, 1, 2, 'Email Address', 'EMAIL'),
(70, 18, 0, 3, 'Device Used', 'RADIO'),
(71, 18, 1, 4, 'Which features did you use?', 'CHECKBOX'),
(72, 18, 1, 5, 'How easy was it to navigate the website?', 'RADIO'),
(73, 18, 1, 6, 'Date of Visit', 'TEXTAREA'),
(74, 18, 1, 7, 'Date of Visit', 'DATE');

-- --------------------------------------------------------

--
-- Table structure for table `question_options`
--

CREATE TABLE `question_options` (
  `id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `option_value` varchar(255) NOT NULL,
  `option_text` varchar(255) NOT NULL,
  `order_number` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question_options`
--

INSERT INTO `question_options` (`id`, `question_id`, `option_value`, `option_text`, `order_number`) VALUES
(7, 20, 'Frontend', 'Frontend', 1),
(8, 20, 'Backend', 'Backend', 2),
(9, 21, 'very satisfied', 'very satisfied', 1),
(10, 21, 'unsatisfied', 'unsatisfied', 2),
(11, 21, 'neutral', 'neutral', 3),
(12, 22, 'Git', 'Git', 1),
(13, 22, 'VS code', 'VS code', 2),
(14, 22, 'Intellij', 'Intellij', 3),
(18, 28, 'Daily', 'Daily', 1),
(19, 28, 'Weekly', 'Weekly', 2),
(20, 28, 'Monthly', 'Monthly', 3),
(24, 32, 'consulting', 'consulting', 1),
(25, 32, 'sales', 'sales', 2),
(26, 32, 'support', 'support', 3),
(27, 36, 'IT', 'IT', 1),
(28, 36, 'Engineering', 'Engineering', 2),
(29, 36, 'Marketing', 'Marketing', 3),
(30, 42, 'HR', 'HR', 1),
(31, 42, 'IT', 'IT', 2),
(32, 42, 'SALES', 'SALES', 3),
(33, 44, 'Leadership', 'Leadership', 1),
(34, 44, 'Cybersecurity', 'Cybersecurity', 2),
(35, 44, 'AI', 'AI', 3),
(39, 51, 'Diploma', 'Diploma', 1),
(40, 51, 'Bachelor\'s', 'Bachelor\'s', 2),
(41, 51, 'Master\'s', 'Master\'s', 3),
(42, 50, 'Software Engineer', 'Software Engineer', 1),
(43, 50, 'Data Analyst', 'Data Analyst', 2),
(44, 50, 'Project Manager', 'Project Manager', 3),
(45, 52, 'Java', 'Java', 1),
(46, 52, 'React', 'React', 2),
(47, 52, 'Spring Boot', 'Spring Boot', 3),
(48, 57, 'Computing', 'Computing', 1),
(49, 57, 'Education', 'Education', 2),
(50, 57, 'Engineering,', 'Engineering,', 3),
(51, 58, '1st', '1st', 1),
(52, 58, '2nd', '2nd', 2),
(53, 58, '3rd', '3rd', 3),
(54, 58, '4th', '4th', 4),
(55, 59, 'Library', 'Library', 1),
(56, 59, 'Wi-Fi', 'Wi-Fi', 2),
(59, 65, 'YES', 'YES', 1),
(60, 65, 'NO', 'NO', 2),
(64, 70, 'Laptop', 'Laptop', 1),
(65, 70, 'Desktop', 'Desktop', 2),
(66, 70, 'Mobile', 'Mobile', 3),
(70, 71, 'Login', 'Login', 1),
(71, 71, 'Contact Form', 'Contact Form', 2),
(72, 71, 'Reports', 'Reports', 3),
(73, 72, 'easy', 'easy', 1),
(74, 72, 'difficult', 'difficult', 2);

-- --------------------------------------------------------

--
-- Table structure for table `responses`
--

CREATE TABLE `responses` (
  `id` bigint(20) NOT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  `survey_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `responses`
--

INSERT INTO `responses` (`id`, `submitted_at`, `survey_id`, `user_id`) VALUES
(13, '2026-07-12 18:04:47.000000', 11, 1),
(14, '2026-07-12 18:09:21.000000', 12, 3),
(15, '2026-07-12 18:35:57.000000', 13, 5),
(16, '2026-07-12 19:03:02.000000', 13, 10),
(17, '2026-07-12 19:20:17.000000', 15, 11);

-- --------------------------------------------------------

--
-- Table structure for table `response_answers`
--

CREATE TABLE `response_answers` (
  `id` bigint(20) NOT NULL,
  `response_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `answer_text` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `surveys`
--

CREATE TABLE `surveys` (
  `id` bigint(20) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `title` varchar(255) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `surveys`
--

INSERT INTO `surveys` (`id`, `description`, `created_at`, `updated_at`, `title`, `created_by`) VALUES
(11, 'give feedback as software developer regarding the development process, tools, collaboration, and project delivery.', '2026-07-12 13:29:33', '2026-07-12 13:29:33', 'Software Development Team Feedback Survey', 2),
(12, 'Help us improve our products and services by sharing your experience.', '2026-07-12 13:46:13', '2026-07-12 13:46:13', 'Customer Business Satisfaction', 2),
(13, 'This survey helps evaluate internship programs and improve the learning experience.', '2026-07-12 14:02:37', '2026-07-12 14:02:37', 'Internship Experience Survey', 2),
(14, 'This survey identifies employee training needs and career development opportunities.', '2026-07-12 14:16:55', '2026-07-12 14:16:55', 'Employee Development and Training Survey', 9),
(15, 'Please complete this survey before submitting your application.', '2026-07-12 14:25:25', '2026-07-12 14:25:25', 'Job Application Pre-Screening Survey', 9),
(16, 'This survey measures student satisfaction with academic services and campus facilities.', '2026-07-12 14:36:37', '2026-07-12 14:36:37', 'University Student Satisfaction Survey', 9),
(17, 'Your feedback helps improve future workshops and training sessions.', '2026-07-12 14:45:22', '2026-07-12 14:45:22', 'Training Workshop Evaluation Survey', 9),
(18, 'Help us improve our website by sharing your experience.', '2026-07-12 14:51:43', '2026-07-12 14:51:43', 'Website User Experience Survey', 9);

-- --------------------------------------------------------

--
-- Table structure for table `survey_responses`
--

CREATE TABLE `survey_responses` (
  `id` bigint(20) NOT NULL,
  `survey_id` bigint(20) NOT NULL,
  `respondent_email` varchar(255) NOT NULL,
  `submitted_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `email`, `enabled`, `first_name`, `last_name`, `password`, `role`, `updated_at`) VALUES
(1, '2026-07-09 13:23:01.000000', 'ndegwa@gmail.com', b'1', 'Michael ', 'Mwangi', '$2a$10$XNyPUKJxz3Xe0pBrgpL7Jur9HvncR4FtMOhDfYMBvy99.Fy22f/6y', 'ROLE_USER', '2026-07-09 13:23:01.000000'),
(2, '2026-07-09 13:50:26.000000', 'muturi@gmail.com', b'1', 'hosea', 'muturi', '$2a$10$mT19ETsj3N.ZAmf0wGUKA.oXlC6Vv5jTBSLhdTWtfJYRSqFrSF5eG', 'ROLE_ADMIN', '2026-07-09 13:50:26.000000'),
(3, '2026-07-11 22:32:25.000000', 'ochieng@gmail.com', b'1', 'Peter', 'Ochieng', '$2a$10$EicfNaD5HUe4AQXIaWs4Tu0kn2jWPKv.LVAJdQ0P9xcpUz4pYeHzO', 'ROLE_USER', '2026-07-11 22:32:25.000000'),
(4, '2026-07-12 10:53:55.000000', 'john@gmail.com', b'1', 'John', 'Doe', '$2a$10$GLvwJSoQoXAkVHlceLgtte3qffRvtAHXeSzfkRR7llwrDsaJHGgA.', 'ROLE_ADMIN', '2026-07-12 10:53:55.000000'),
(5, '2026-07-12 16:14:09.000000', 'mary@gmail.com', b'1', 'Mary ', 'Awinja', '$2a$10$PS7bjc/Ly/oyFoDvbqDNDecCsYDnw33jfiac2i4EZVzws2K0Dn/SG', 'ROLE_USER', '2026-07-12 16:14:09.000000'),
(6, '2026-07-12 16:15:16.000000', 'wanja@gmail.com', b'1', 'Leah', 'wanja', '$2a$10$rYn97JLcj7szwglcWojc5O5i6VY.iuW5yJT3A0HdcG4JF9zMpxJWm', 'ROLE_USER', '2026-07-12 16:15:16.000000'),
(7, '2026-07-12 16:17:06.000000', 'kinja@gmail.com', b'1', 'Harun', 'Kinja', '$2a$10$v6uV465krayQeCBZDcNqnu7hSsMJZl.fm1vIdAojGYRA6VtNQYco2', 'ROLE_USER', '2026-07-12 16:17:06.000000'),
(8, '2026-07-12 16:18:33.000000', 'kiroka@gmail.com', b'1', 'Lilian', 'kiroka', '$2a$10$KjwlQLuW.2wifiOdXYVxvuZgNXJKF4PugliP2yckHsH42Lfj11MPq', 'ROLE_USER', '2026-07-12 16:18:33.000000'),
(9, '2026-07-12 17:14:16.000000', 'kioko@gmail.com', b'1', 'Samwel', 'kioko', '$2a$10$70lv2jQQhXBFjnEyQQbMxuwdjQisL9v7oB4vOsYJcQL/CSfMVOsym', 'ROLE_ADMIN', '2026-07-12 17:14:16.000000'),
(10, '2026-07-12 18:46:49.000000', 'annmary@gmail.com', b'1', 'sheemah', 'mary', '$2a$10$XlPgpyU14gESxSvFxYNEhO8MsrSHGJBNZ7lwdCVLxJsS2T2WpnaM.', 'ROLE_USER', '2026-07-12 18:46:49.000000'),
(11, '2026-07-12 19:07:27.000000', 'danson@gmail.com', b'1', 'stephen', 'danson', '$2a$10$PBQ7pNJdEMTgws2ZxRp1xOFDH2WU2wx34CHEx.b4Z5w/whyBoH04S', 'ROLE_USER', '2026-07-12 19:07:27.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3erw1a3t0r78st8ty27x6v3g1` (`question_id`),
  ADD KEY `FKdvrc91061f1tdfkl8wv77k4t3` (`response_id`);

--
-- Indexes for table `certificates`
--
ALTER TABLE `certificates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_certificates_response` (`response_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `survey_id` (`survey_id`);

--
-- Indexes for table `question_options`
--
ALTER TABLE `question_options`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `responses`
--
ALTER TABLE `responses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKemug20qu5fygiy69wj7sel8hc` (`survey_id`),
  ADD KEY `FKqf8rt9h0wd5pmaxouhxqsoeuq` (`user_id`);

--
-- Indexes for table `response_answers`
--
ALTER TABLE `response_answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`),
  ADD KEY `idx_response_answers_response` (`response_id`);

--
-- Indexes for table `surveys`
--
ALTER TABLE `surveys`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9smsu8m83blnmkyocfs2e272a` (`created_by`);

--
-- Indexes for table `survey_responses`
--
ALTER TABLE `survey_responses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `survey_id` (`survey_id`),
  ADD KEY `idx_survey_responses_email` (`respondent_email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `certificates`
--
ALTER TABLE `certificates`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `question_options`
--
ALTER TABLE `question_options`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `responses`
--
ALTER TABLE `responses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `response_answers`
--
ALTER TABLE `response_answers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `surveys`
--
ALTER TABLE `surveys`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `survey_responses`
--
ALTER TABLE `survey_responses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `FK3erw1a3t0r78st8ty27x6v3g1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`),
  ADD CONSTRAINT `FKdvrc91061f1tdfkl8wv77k4t3` FOREIGN KEY (`response_id`) REFERENCES `responses` (`id`);

--
-- Constraints for table `certificates`
--
ALTER TABLE `certificates`
  ADD CONSTRAINT `certificates_ibfk_1` FOREIGN KEY (`response_id`) REFERENCES `survey_responses` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`survey_id`) REFERENCES `surveys` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `question_options`
--
ALTER TABLE `question_options`
  ADD CONSTRAINT `question_options_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `responses`
--
ALTER TABLE `responses`
  ADD CONSTRAINT `FKemug20qu5fygiy69wj7sel8hc` FOREIGN KEY (`survey_id`) REFERENCES `surveys` (`id`),
  ADD CONSTRAINT `FKqf8rt9h0wd5pmaxouhxqsoeuq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `response_answers`
--
ALTER TABLE `response_answers`
  ADD CONSTRAINT `response_answers_ibfk_1` FOREIGN KEY (`response_id`) REFERENCES `survey_responses` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `response_answers_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `surveys`
--
ALTER TABLE `surveys`
  ADD CONSTRAINT `FK9smsu8m83blnmkyocfs2e272a` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`);

--
-- Constraints for table `survey_responses`
--
ALTER TABLE `survey_responses`
  ADD CONSTRAINT `survey_responses_ibfk_1` FOREIGN KEY (`survey_id`) REFERENCES `surveys` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
