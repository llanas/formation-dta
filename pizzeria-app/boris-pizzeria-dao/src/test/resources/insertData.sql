CREATE TABLE `pizza` (
  `id` int(11) NOT NULL,
  `code` varchar(3) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prix` double NOT NULL,
  `type` varchar(255) NOT NULL
)

INSERT INTO `pizza` (`id`, `code`, `nom`, `prix`, `type`) VALUES
(2, 'MAR', 'Test', 14, 'POISSON'),
(3, 'REI', 'La Reine', 11.5, 'VIANDE'),
(4, 'FRO', 'La 4 Fromages', 12, 'SANS_VIANDE'),
(5, 'CAN', 'Cannibale', 12.5, 'VIANDE'),
(6, 'SAV', 'Savoyarde', 13, 'VIANDE'),
(7, 'ORI', 'L''Orientale', 13.5, 'VIANDE'),
(8, 'IND', 'L''Indienne', 14, 'VIANDE'),
(9, 'SAL', 'Salut', 12, 'SANS_VIANDE'),
(11, 'TES', 'test', 12, 'VIANDE'),
(12, 'eza', 'Margherit', 12, 'VIANDE'),
(13, 'PEP', 'Pépéroni', 12.5, 'VIANDE'),
(14, 'SPR', 'spring', 15, 'VIANDE');