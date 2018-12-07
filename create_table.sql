-- Create syntax for TABLE 'Agenda'
CREATE TABLE `Agenda` (
  `idAgenda` int(11) NOT NULL,
  `DiaSemana` date DEFAULT NULL,
  `HoraInicio` time DEFAULT NULL,
  `HoraFim` time DEFAULT NULL,
  PRIMARY KEY (`idAgenda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Consulta_Diagnostico'
CREATE TABLE `Consulta_Diagnostico` (
  `idConsulta` int(11) NOT NULL,
  `Data` date DEFAULT NULL,
  `Pagou` tinyint(1) DEFAULT NULL,
  `ValorPago` double DEFAULT NULL,
  `FormaPagamento` varchar(10) DEFAULT NULL,
  `HoraInicio` time DEFAULT NULL,
  `MinutosInic` time DEFAULT NULL,
  `HoraFim` time DEFAULT NULL,
  `TratamentoRecomendado` varchar(60) DEFAULT NULL,
  `idDiagnostico` int(11) NOT NULL,
  `RemediosReceitados` varchar(500) DEFAULT NULL,
  `Observacoes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idConsulta`,`idDiagnostico`),
  KEY `FK_Doença_2` (`idDiagnostico`),
  CONSTRAINT `FK_Doença_2` FOREIGN KEY (`idDiagnostico`) REFERENCES `Doença` (`idDoenca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Doença'
CREATE TABLE `Doença` (
  `idDoenca` int(11) NOT NULL,
  `Nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idDoenca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Especialidade'
CREATE TABLE `Especialidade` (
  `Codigo` int(11) NOT NULL,
  `Indice` int(11) DEFAULT NULL,
  `Nome` varchar(200) DEFAULT NULL,
  `fk_Taxa_Taxa` double DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `FK_Especialidade_2` (`fk_Taxa_Taxa`),
  CONSTRAINT `FK_Especialidade_2` FOREIGN KEY (`fk_Taxa_Taxa`) REFERENCES `Taxa` (`Taxa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Especifica'
CREATE TABLE `Especifica` (
  `fk_Consulta_Diagnostico_idConsulta` int(11) DEFAULT NULL,
  `fk_Consulta_Diagnostico_idDiagnostico` int(11) DEFAULT NULL,
  `fk_Especialidade_Codigo` int(11) DEFAULT NULL,
  KEY `FK_Especifica_2` (`fk_Especialidade_Codigo`),
  KEY `FK_Especifica_1` (`fk_Consulta_Diagnostico_idConsulta`,`fk_Consulta_Diagnostico_idDiagnostico`),
  CONSTRAINT `FK_Especifica_1` FOREIGN KEY (`fk_Consulta_Diagnostico_idConsulta`, `fk_Consulta_Diagnostico_idDiagnostico`) REFERENCES `Consulta_Diagnostico` (`idConsulta`, `idDiagnostico`),
  CONSTRAINT `FK_Especifica_2` FOREIGN KEY (`fk_Especialidade_Codigo`) REFERENCES `Especialidade` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Exerce'
CREATE TABLE `Exerce` (
  `fk_Medico_CRM` int(11) DEFAULT NULL,
  `fk_Especialidade_Codigo` int(11) DEFAULT NULL,
  KEY `FK_Exerce_1` (`fk_Medico_CRM`),
  KEY `FK_Exerce_2` (`fk_Especialidade_Codigo`),
  CONSTRAINT `FK_Exerce_1` FOREIGN KEY (`fk_Medico_CRM`) REFERENCES `Medico` (`CRM`),
  CONSTRAINT `FK_Exerce_2` FOREIGN KEY (`fk_Especialidade_Codigo`) REFERENCES `Especialidade` (`Codigo`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Medico'
CREATE TABLE `Medico` (
  `CRM` int(11) NOT NULL,
  `Nome` varchar(200) DEFAULT NULL,
  `Telefone` decimal(10,0) DEFAULT NULL,
  `fk_Agenda_idAgenda` int(11) DEFAULT NULL,
  `fk_Consulta_Diagnostico_idConsulta` int(11) DEFAULT NULL,
  `fk_Consulta_Diagnostico_idDiagnostico` int(11) DEFAULT NULL,
  PRIMARY KEY (`CRM`),
  KEY `FK_Medico_2` (`fk_Agenda_idAgenda`),
  KEY `FK_Medico_3` (`fk_Consulta_Diagnostico_idConsulta`,`fk_Consulta_Diagnostico_idDiagnostico`),
  CONSTRAINT `FK_Medico_2` FOREIGN KEY (`fk_Agenda_idAgenda`) REFERENCES `Agenda` (`idAgenda`),
  CONSTRAINT `FK_Medico_3` FOREIGN KEY (`fk_Consulta_Diagnostico_idConsulta`, `fk_Consulta_Diagnostico_idDiagnostico`) REFERENCES `Consulta_Diagnostico` (`idConsulta`, `idDiagnostico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Paciente'
CREATE TABLE `Paciente` (
  `CodigoPaciente` int(11) NOT NULL,
  `CPF` decimal(10,0) DEFAULT NULL,
  `Nome` varchar(200) DEFAULT NULL,
  `Telefone` decimal(10,0) DEFAULT NULL,
  `Sexo` tinyint(1) DEFAULT NULL,
  `Endereco` varchar(200) DEFAULT NULL,
  `Idade` int(11) DEFAULT NULL,
  `fk_Consulta_Diagnostico_idConsulta` int(11) DEFAULT NULL,
  `fk_Consulta_Diagnostico_idDiagnostico` int(11) DEFAULT NULL,
  PRIMARY KEY (`CodigoPaciente`),
  KEY `FK_Paciente_2` (`fk_Consulta_Diagnostico_idConsulta`,`fk_Consulta_Diagnostico_idDiagnostico`),
  CONSTRAINT `FK_Paciente_2` FOREIGN KEY (`fk_Consulta_Diagnostico_idConsulta`, `fk_Consulta_Diagnostico_idDiagnostico`) REFERENCES `Consulta_Diagnostico` (`idConsulta`, `idDiagnostico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'Taxa'
CREATE TABLE `Taxa` (
  `Taxa` double NOT NULL,
  `Ano` date DEFAULT NULL,
  `Mes` date DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  PRIMARY KEY (`Taxa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
