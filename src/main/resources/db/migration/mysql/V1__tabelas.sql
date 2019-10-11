-- Criação das tabelas--
CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL,
  `cnpj` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `razao_social` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `qtd_horas_almoco` float DEFAULT NULL,
  `qtd_horas_trabalho_dia` float DEFAULT NULL,
  `senha` varchar(255) NOT NULL,
  `valor_hora` decimal(19,2) DEFAULT NULL,
  `empresa_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL,
  `data` datetime NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `localizacao` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) NOT NULL,
  `funcionario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Index para as tabelas--
alter table empresa
add constraint `empresa_id` 
primary key(id);

alter table funcionario
add constraint `funcionario_id` 
primary key(id);


alter table lancamento
add constraint `lancamento_id` 
primary key(id);

-- Auto incremento para as tabelas --

ALTER TABLE empresa 
MODIFY COLUMN id bigint(20) 
NOT NULL AUTO_INCREMENT;

ALTER TABLE funcionario 
MODIFY COLUMN id bigint(20) 
NOT NULL AUTO_INCREMENT;

ALTER TABLE lancamento 
MODIFY COLUMN id bigint(20) 
NOT NULL AUTO_INCREMENT;

-- Chave Estrangeira para as tabelas --

alter table funcionario
add constraint `fk_empresa` 
foreign key(`empresa_id`)
references empresa(`id`);

alter table lancamento
add constraint `fk_funcionario` 
foreign key(`funcionario_id`)
references funcionario(`id`);