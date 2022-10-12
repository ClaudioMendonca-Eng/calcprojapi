CREATE TABLE empresasistema (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  empresa VARCHAR(255) NOT NULL,
  cnpj VARCHAR(255),
  endereco VARCHAR(255),
  cep VARCHAR(255),
  email VARCHAR(255),
  logotipo VARCHAR(255),
  responsavel VARCHAR(255),
  telefone VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO empresasistema (empresa, cnpj, endereco, cep, email, logotipo, responsavel, telefone) values ('Asterisco Designer Associados S/A', '99.000.000/008-99', 'Av 22 de Maio 900', '24893-000', 'contato@asterisco.com', 'logo.jpg', 'Carlos Mendes', '552133335500');

CREATE TABLE cliente (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  nome VARCHAR(255) NOT NULL,
  cnpj VARCHAR(255),
  endereco VARCHAR(255),
  cep VARCHAR(255),
  email VARCHAR(255),
  logotipo VARCHAR(255),
  telefone VARCHAR(255),
  responsavel VARCHAR(255),
  ativo BOOLEAN NOT NULL,
  empresasistema_id BIGINT(20) NOT NULL,
  FOREIGN KEY (empresasistema_id) REFERENCES empresasistema(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cliente (nome, cnpj, endereco, cep, email, logotipo, telefone, responsavel, ativo, empresasistema_id) values ('Bosano LTDA', '99.000.000/008-99', 'Rua Cabo Frio', '24237-000', 'contato@bosano.com.br', 'logo.jpg', '55219999999', 'Roberto Marques', true, 1);
INSERT INTO cliente (nome, cnpj, endereco, cep, email, logotipo, telefone, responsavel, ativo, empresasistema_id) values ('Broker Trader', '99.000.000/008-99', 'Rua Marmota', '24345-000', 'contact@brokertrade.com', 'logo.jpg', '55219999999', 'Michel Filizatti', true, 1);
INSERT INTO cliente (nome, cnpj, endereco, cep, email, logotipo, telefone, responsavel, ativo, empresasistema_id) values ('Monarky Ind', '99.000.000/008-99', 'Rua Figueredo', '24000-000', 'contato@monarky.com.br', 'logo.jpg', '55219999999', 'Bruno Silva', true, 1);
INSERT INTO cliente (nome, cnpj, endereco, cep, email, logotipo, telefone, responsavel, ativo, empresasistema_id) values ('Thays Moda', '99.000.000/008-99', 'Rua Boassu', '24378-000', 'contato@ThaysModa.com.br', 'logo.jpg', '55219999999', 'Wanessa Brando', true, 1);

CREATE TABLE usuario (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  nome VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  funcao VARCHAR(255) NOT NULL,
  ativaadmin BOOLEAN NOT NULL,
  empresasistema_id BIGINT(20) NOT NULL,
  FOREIGN KEY (empresasistema_id) REFERENCES empresasistema(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, senha, email, funcao, ativaadmin, empresasistema_id) values ('Carlos Mendes', 'senha', 'mendes.carlos@asterisco.com', 'Supervisor de Orçamentos', true, 1);
INSERT INTO usuario (nome, senha, email, funcao, ativaadmin, empresasistema_id) values ('Priscila Ramos', 'senha', 'ramos.priscila@asterisco.com', 'Administração', false, 1);
INSERT INTO usuario (nome, senha, email, funcao, ativaadmin, empresasistema_id) values ('Carolina Barros', 'senha', 'barros.carolina@asterisco.com', 'Dev. FrontEnd - Mobile Pleno', false, 1);
INSERT INTO usuario (nome, senha, email, funcao, ativaadmin, empresasistema_id) values ('Laerte Macena', 'senha', 'macena.laerte@asterisco.com', 'Dev. FrontEnd - Pleno Java/Angular', false, 1);

CREATE TABLE projeto (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  nome VARCHAR(255) NOT NULL,
  resumo VARCHAR(255),
  descricao VARCHAR(255),
  tags VARCHAR(255),
  cliente_id BIGINT(20),
  empresasistema_id BIGINT(20),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (empresasistema_id) REFERENCES empresasistema(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO projeto (nome, resumo, descricao, tags, cliente_id, empresasistema_id) values ('PROJETO DO APP VIABOSANO', 'Aplicativo para sistema Android e IOS.', 'descrição', 'tags', 1, 1);
INSERT INTO projeto (nome, resumo, descricao, tags, cliente_id, empresasistema_id) values ('PROJETO DO APP TRADER FORCE', 'Aplicativo para sistema Android e IOS.', 'descrição', 'tags', 2, 1);
INSERT INTO projeto (nome, resumo, descricao, tags, cliente_id, empresasistema_id) values ('PROJETO DO SAC WEBSITE', 'WebSite p/ atendimento aos clientes.', 'descrição', 'tags', 3, 1);
INSERT INTO projeto (nome, resumo, descricao, tags, cliente_id, empresasistema_id) values ('PROJETO E-COMMERCE LITE', 'E-COMMERCE lite para vendas de produtos.', 'descrição', 'tags', 4, 1);

CREATE TABLE orcamento (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  versao VARCHAR(255) NOT NULL,
  status_orcamento VARCHAR(255) NOT NULL,
  comissao DECIMAL(10,2),
  margem_lucro DECIMAL(10,2),
  fiscal DECIMAL(10,2),
  data_vencimento DATE  NOT NULL,
  investimento VARCHAR(255),
  prazo VARCHAR(255),
  projeto_id BIGINT(20),
  FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO orcamento (versao, status_orcamento, comissao, margem_lucro, fiscal, data_vencimento, investimento, prazo, projeto_id) values ('versao', 'AGUARDANDO', '10.0', '10.0', '10.0', '2022-02-22', 'investimento', 'prazo', 1);

CREATE TABLE clientecomentario (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  comentario VARCHAR(255),
  cliente_id BIGINT(20),
  orcamento_id BIGINT(20),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (orcamento_id) REFERENCES orcamento(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO clientecomentario (comentario, cliente_id, orcamento_id) values ('comentario cliente 1', 1, 1);
INSERT INTO clientecomentario (comentario, cliente_id, orcamento_id) values ('comentario cliente 2', 2, 1);

CREATE TABLE comentarioprivado (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  comentario VARCHAR(255),
  projeto_id BIGINT(20),
  usuario_id BIGINT(20),
  FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO comentarioprivado (comentario, projeto_id, usuario_id) values ('comentario privado 1', 1, 1);
INSERT INTO comentarioprivado (comentario, projeto_id, usuario_id) values ('comentario privado 2', 2, 1);

CREATE TABLE tipo (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  nome VARCHAR(255),
  tipo VARCHAR(255),
  contratotipo VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tipo (nome, tipo, contratotipo) values ('CAMEO -  Impressora de corte silhouette', 'equipamento', null);
INSERT INTO tipo (nome, tipo, contratotipo) values ('Enel - Energia', 'servico', null);
INSERT INTO tipo (nome, tipo, contratotipo) values ('Claro - Internet', 'servico', null);
INSERT INTO tipo (nome, tipo, contratotipo) values ('Supervisor de Orçamentos', 'prestado', 'CLT');
INSERT INTO tipo (nome, tipo, contratotipo) values ('Administração', 'prestado', 'CLT');
INSERT INTO tipo (nome, tipo, contratotipo) values ('Dev. FrontEnd - Pleno Java/Angular (CLT)', 'prestado', 'CLT');
INSERT INTO tipo (nome, tipo, contratotipo) values ('Dev. FrontEnd - Mobile Pleno IOS/Android', 'prestado', 'CLT');

CREATE TABLE recurso (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  nome VARCHAR(255),
  custohora DECIMAL(10,2),
  equipamentovida INT, 
  empresasistema_id BIGINT(20),
  tipo_id BIGINT(20),
  FOREIGN KEY (empresasistema_id) REFERENCES empresasistema(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (tipo_id) REFERENCES tipo(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', '13', 1, 1);
INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', null, 1, 2);
INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', null, 1, 3);
INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', null, 1, 4);
INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', null, 1, 5);
INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', null, 1, 6);
INSERT INTO recurso (nome, custohora, equipamentovida, empresasistema_id, tipo_id) values ('nome', '10.0', null, 1, 7);

CREATE TABLE cotacaorecurso (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  quantidade DECIMAL(10,2),
  recurso_id BIGINT(20),
  orcamento_id BIGINT(20),
  FOREIGN KEY (recurso_id) REFERENCES recurso(id),
  FOREIGN KEY (orcamento_id) REFERENCES orcamento(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cotacaorecurso (quantidade, recurso_id, orcamento_id) values ('1.0', 1, 1);

CREATE TABLE compartilhar (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  email VARCHAR(255),
  enviado VARCHAR(255),
  orcamento_id BIGINT(20),
  FOREIGN KEY (orcamento_id) REFERENCES orcamento(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO compartilhar (email, enviado, orcamento_id) values ('email@dominio.com', true, 1);

CREATE TABLE comentariocompartilhado (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  comentario VARCHAR(255),
  compartilhar_id BIGINT(20),
  FOREIGN KEY (compartilhar_id) REFERENCES compartilhar(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO comentariocompartilhado (comentario, compartilhar_id) values ('Comentario compartilhado 01', 1);

CREATE TABLE visao (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  compartilhar_id BIGINT(20),
  FOREIGN KEY (compartilhar_id) REFERENCES compartilhar(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO visao (compartilhar_id) values ( 1);


