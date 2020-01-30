INSERT INTO `empresa` (`id`, `cnpj`, `data_atualizacao`, `data_criacao`, `razao_social`)
VALUES (NULL, '83815138000176', CURRENT_DATE(), CURRENT_DATE(), 'Marcel Philippe Softwares');

INSERT INTO `funcionario` (`id`, `cpf`, `data_atualizacao`, `data_criacao`, `email`, `nome`,
`perfil`, `qtd_horas_almoco`, `qtd_horas_trabalho_dia`, `senha`, `valor_hora`, `empresa_id`)
VALUES (NULL, '42737504066', CURRENT_DATE(), CURRENT_DATE(), 'marcelpaa@hotmail.com', 'ADMIN',
'ROLE_ADMIN', NULL, NULL, '$2a$10$F/pKY0JNCdk.FHolzmsbmOYU.Q3hpEqKG4RdPMCrcFUf3mZJjvcce', NULL,
(SELECT `id` FROM `empresa` WHERE `cnpj` = '12576531000106'));