CREATE DATABASE livraria;
USE livraria;

CREATE TABLE enderecos (
	id_endereco INTEGER NOT NULL auto_increment,
	cep VARCHAR(10) NOT NULL,
	bairro VARCHAR(100) NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero VARCHAR(15) NOT NULL,
	complemento VARCHAR(50),
	CONSTRAINT PK_endreco PRIMARY KEY (id_endereco)
);

CREATE TABLE pessoas (
	cpf VARCHAR(11) NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	telefone VARCHAR(100),
	email VARCHAR(100),
	data_nascimento DATE NOT NULL,
	endereco INTEGER NOT NULL,
	CONSTRAINT FK_pessoa_endereco FOREIGN KEY (endereco)
    	REFERENCES enderecos (id_endereco)
);

CREATE TABLE funcionarios (
	cpf VARCHAR(11) NOT NULL,
	codigo INTEGER,
	data_contratacao DATE NOT NULL,
	senha VARCHAR(512) NOT NULL,
	cargo VARCHAR(20) NOT NULL,
	situacao VARCHAR(10) NOT NULL,
	CONSTRAINT PK_funcionario PRIMARY KEY (cpf),
	CONSTRAINT CHEK_funcionario_cargo CHECK (cargo = 'atendente' OR cargo = 'caixa'
    	or cargo = 'gerente'),
	CONSTRAINT CHEK_funcionario_situacao CHECK (situacao = 'ativo'
    	or situacao = 'ferias'
    	or situacao = 'inativo'),
	CONSTRAINT FK_funcionario_pessoa FOREIGN KEY (cpf)
    	REFERENCES pessoas (cpf)
);

CREATE TABLE frequencia (
	cpf_funcionario VARCHAR(11) NOT NULL,
	data_freq DATE NOT NULL,
	hora_entrada TIME NOT NULL,
	hora_saida TIME,
	CONSTRAINT FK_faltas_funcionario FOREIGN KEY (cpf_funcionario)
    	REFERENCES funcionarios (cpf),
	CONSTRAINT CHECK_horas_trabalhadas CHECK (hora_saida > hora_entrada)
);

CREATE TABLE faltas_justificadas (
	cpf_funcionario VARCHAR(11) NOT NULL,
	data_falta TIMESTAMP NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	CONSTRAINT FK_faltas_justificadas_funcionario FOREIGN KEY (cpf_funcionario)
    	REFERENCES funcionarios (cpf)
);

CREATE TABLE clientes (
	cpf VARCHAR(11) NOT NULL,
	data_cadastro DATE NOT NULL,
	CONSTRAINT PK_clientes PRIMARY KEY (cpf),
	CONSTRAINT FK_cliente_pessoa FOREIGN KEY (cpf)
    	REFERENCES pessoas (cpf)
);

CREATE TABLE prateleiras (
	codigo_prateleira INTEGER NOT NULL AUTO_INCREMENT,
	localizacao VARCHAR(50) NOT NULL,
	cpf_funcionario_responsavel VARCHAR(11) NOT NULL,
	descricao VARCHAR(200),
	CONSTRAINT PK_prateleiras PRIMARY KEY (codigo_prateleira),
CONSTRAINT FK_codigo_funcionario_resp FOREIGN KEY (cpf_funcionario_responsavel)
    	REFERENCES funcionarios (cpf)
);

CREATE TABLE fornecedores (
	cnpj VARCHAR(20) NOT NULL,
	nome VARCHAR(200) NOT NULL,
	email VARCHAR(200) NOT NULL,
	id_endereco INTEGER NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	CONSTRAINT PK_fornecedor PRIMARY KEY (cnpj),
	CONSTRAINT FK_fornecedor_endereco FOREIGN KEY (id_endereco)
    	REFERENCES enderecos (id_endereco)
);

CREATE TABLE livros (
	isbn VARCHAR(13) NOT NULL,
	titulo VARCHAR(100) NOT NULL,
	autor VARCHAR(100) NOT NULL,
	preco_custo NUMERIC(8 , 2 ) NOT NULL,
	preco_venda NUMERIC(8 , 2 ) NOT NULL,
	estoque_minimo INTEGER NOT NULL,
	qtd_estoque INTEGER NOT NULL,
	codigo_prateleira INTEGER NOT NULL,
	cnpj_fornecedor VARCHAR(20) NOT NULL,
	CONSTRAINT PK_livros PRIMARY KEY (isbn),
	CONSTRAINT FK_livros_prateleira FOREIGN KEY (codigo_prateleira)
    	REFERENCES prateleiras (codigo_prateleira),
	CONSTRAINT FK_livros_fornecedor FOREIGN KEY (cnpj_fornecedor)
    	REFERENCES fornecedores (cnpj)
);

CREATE TABLE compras (
	codigo INTEGER NOT NULL auto_increment,
	data_compra DATE NOT NULL,
	situacao VARCHAR(20) NOT NULL default 'aberta',
	data_cancelamento DATE,
	cpf VARCHAR(11) NOT NULL,
	CONSTRAINT PK_compra PRIMARY KEY (codigo),
	CONSTRAINT FK_compras_pesso FOREIGN KEY (cpf)
    	REFERENCES clientes (cpf),
	CONSTRAINT CHECK_situacao CHECK (situacao = 'aberta'
    	OR situacao = 'finalizada'
    	OR situacao = 'cancelada'),
	CONSTRAINT CHECK_cancelamento CHECK (data_cadastro <> NULL
    	AND situacao = 'cancelada')
);

CREATE TABLE itens_compras (
	codigo_compra INTEGER NOT NULL,
	isbn VARCHAR(13) NOT NULL,
	preco_vendido NUMERIC(8 , 2 ) NOT NULL,
	quantidade INTEGER NOT NULL,
	CONSTRAINT FK_itens_compra FOREIGN KEY (codigo_compra)
    	REFERENCES compras (codigo),
	CONSTRAINT FK_itens_livro FOREIGN KEY (isbn)
    	REFERENCES livros (isbn),
	primary key (codigo_compra,isbn),
	CONSTRAINT CHECK_itens_quantidade CHECK (quantidade > 0)
);

CREATE VIEW view_dados_cliente AS
	SELECT
    	p.cpf,p.nome,p.telefone,p.email,e.cep,e.logradouro,e.numero,e.bairro,e.complemento,c.data_cadastro
	FROM
    	pessoas p
        	INNER JOIN
    	enderecos e ON p.endereco = e.id_endereco
        	INNER JOIN
    	clientes c ON p.cpf = c.cpf;

CREATE VIEW view_dados_funcionario AS
	SELECT
    	f.codigo,p.cpf,	p.nome,	p.telefone,	p.email,f.situacao,	f.data_contratacao,	e.cep,	e.logradouro,  	e.numero,  	e.bairro,  	e.complemento
	FROM
    	pessoas p
        	INNER JOIN
    	enderecos e ON p.endereco = e.id_endereco
        	INNER JOIN
    	funcionarios f ON p.cpf = f.cpf;

SELECT
	*
FROM
	view_dados_cliente;

#TRIGGER: 3
DELIMITER ..        

CREATE TRIGGER tg_compra AFTER UPDATE
	ON compras
	FOR EACH ROW
	BEGIN
    	if (old.situacao = 'aberta' and new.situacao = 'finalizada')
    	then
        update livros l, itens_compras i
        set l.qtd_estoque = l.qtd_estoque - i.quantidade
        where (l.isbn = i.isbn and i.codigo_compra = new.codigo);
    	end if;
	END ..
    
CREATE TRIGGER tg_cancela_compra AFTER UPDATE
	ON compras
	FOR EACH ROW
	BEGIN
    	if (old.situacao = 'finalizada' and new.situacao = 'cancelada')
    	then
		update livros l, itens_compras i
        set l.qtd_estoque = l.qtd_estoque + i.quantidade
        where (l.isbn = i.isbn and i.codigo_compra = new.codigo);
    	end if;
	END ..

create trigger tg_codigo_funcionario before insert on funcionarios
for each row
begin
	select max(codigo) into @maior from funcionarios;
    if @maior!= null then
		set new.codigo = (@maior+1);
	else 
		set new.codigo = 1;
    end if;
end..

#function: 3
CREATE FUNCTION fc_altera_preco_venda
	(isbn_livro VARCHAR(13), novo_preco DECIMAL(8,2))
	RETURNS INT
	begin
    	UPDATE livros SET preco_venda = novo_preco WHERE livros.isbn = isbn_livro;
    	return null;
	end ..
    
CREATE FUNCTION fc_registrar_novo_cliente(cpf VARCHAR(11), nome VARCHAR(100), telefone VARCHAR(100),
	email VARCHAR(100),	data_nascimento DATE, cep VARCHAR(10), bairro VARCHAR(100),
	logradouro VARCHAR(100), numero VARCHAR(10), complemento VARCHAR(50))
	RETURNS INT
	begin
		INSERT INTO enderecos(cep, bairro, logradouro, numero, complemento)
		VALUES('''+@cep+''', '''+@bairro+''', '''+@logradouro+''', '''+@numero+''', '''+@complemento+''');
        
		select auto_increment into @id_endereco from information_schema.tables where table_name = 'enderecos';
        
		INSERT INTO pessoas(cpf, nome, telefone, email, data_nascimento, endereco)
        VALUES('''+@cpf+''', '''+@nome+''', '''+@telefone+''', '''+@email+''', '''+@data_nascimento+''','''+@id_endereco+''');
        
		INSERT INTO clientes(cpf, data_cadastro)VALUES('''+@cpf+''', curdate());
    	return null;
	end ..
    
CREATE FUNCTION fc_registrar_novo_funcionario(cpf VARCHAR(11), data_contratacao DATE,
	senha VARCHAR(512), cargo VARCHAR(20), situacao VARCHAR(10), nome VARCHAR(100), 
	telefone VARCHAR(100), email VARCHAR(100), data_nascimento DATE, cep VARCHAR(10), 
    bairro VARCHAR(100), logradouro VARCHAR(100), numero VARCHAR(10), complemento VARCHAR(50))
	RETURNS INT
	begin
		INSERT INTO enderecos(cep, bairro, logradouro, numero, complemento)
		VALUES('''+@cep+''', '''+@bairro+''', '''+@logradouro+''', '''+@numero+''', '''+@complemento+''');
        
		select auto_increment into @id_endereco from information_schema.tables where table_name = 'enderecos';
        
		INSERT INTO pessoas(cpf, nome, telefone, email, data_nascimento, endereco)
        VALUES('''+@cpf+''', '''+@nome+''', '''+@telefone+''', '''+@email+''','''+@data_nascimento+''','''+@id_endereco+''');
        
		INSERT INTO funcionarios (cpf ,data_contratacao ,senha ,cargo ,	situacao) 
        values ('''+@cpf+''', '''+@data_contratacao+''', '''+@senha+''', '''+@cargo+''', '''+@situacao+''');

    	return null;
	end ..
    
#procedure: quase 2
CREATE PROCEDURE proc_pagar_compra(IN codigo_compra INT)
BEGIN
	SELECT situacao INTO @estado_venda FROM compras WHERE (codigo = codigo_compra);
	IF @estado_venda = 'aberta' THEN
    	UPDATE compras
        	SET situacao = 'finalizada'
        	WHERE compras.codigo = codigo_compra;
	END IF;
END..
CREATE PROCEDURE proc_cancelar_compra(IN codigo_compra INT)
BEGIN
	SELECT situacao INTO @estado_venda FROM compras WHERE (codigo = codigo_compra);
	IF @estado_venda = 'finalizada' THEN
    	UPDATE compras
        	SET situacao = 'cancelada', data_cancelamento = curdate()
        	WHERE compras.codigo = codigo_compra;
	END IF;
END..
DELIMITER ;

#INSERTs: 6
INSERT INTO enderecos(cep, bairro, logradouro, numero, complemento)
VALUES('1111-111', 'bairro das flores', 'rua das lápides', 'X27', 'Em qualquer lugar'),
('2222-222', 'ah', 'bh', '13', NULL);

INSERT INTO pessoas(cpf, nome, data_nascimento, endereco)VALUES('33333333333', 'P. Lúcia', '1990-01-01', 01);
INSERT INTO pessoas(cpf, nome, data_nascimento, endereco)VALUES('12345678900', 'A. Freldo', '1970-10-11', 02);

INSERT INTO funcionarios (cpf ,data_contratacao ,senha ,cargo ,	situacao) values ('12345678900', '2000-10-12', '123', 'caixa', 'ativo');

INSERT INTO clientes(cpf, data_cadastro)VALUES('33333333333', curdate());

INSERT INTO enderecos(cep, bairro, logradouro, numero, complemento)
VALUES('1111-111', 'bairro das flores', 'rua das lápides', 'X27', 'Em qualquer lugar'),
('2222-222', 'ah', 'bh', '13', NULL);

INSERT INTO pessoas(cpf, nome, data_nascimento, endereco)VALUES('33333333333', 'P. Lúcia', '1990-01-01', 01);
INSERT INTO pessoas(cpf, nome, data_nascimento, endereco)VALUES('12345678900', 'A. Freldo', '1970-10-11', 02);

INSERT INTO funcionarios (cpf ,data_contratacao ,senha ,cargo ,	situacao) values ('12345678900', '2000-10-12', '123', 'caixa', 'ativo');

INSERT INTO clientes(cpf, data_cadastro)VALUES('33333333333', curdate());

select * from funcionarios;


INSERT INTO prateleiras(localizacao, descricao, cpf_funcionario_responsavel)VALUES('Térreo, terceiro corredor', null, '12345678900');

INSERT INTO fornecedores(cnpj, nome, id_endereco, telefone, email)
VALUES('12312312344', 'Nós fornecemos', 2, '+77 61 6263-6465', 'nf@nf.com');

INSERT INTO livros(isbn, titulo, autor, preco_custo, preco_venda, estoque_minimo, qtd_estoque,
 codigo_prateleira, cnpj_fornecedor)
VALUES
('1234567890123', 'The livro', 'O Author', 100.00, 400.00, 50, 167, 01, '12312312344'),
('1234567890333', 'Lirvo', 'O Escrito', 222.00, 333.00, 50, 200, 01, '12312312344');

UPDATE livros
SET
	titulo = 'O Livro'
WHERE
	(isbn = '1234567890123');
