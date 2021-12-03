--Created by Bruno Paz.

CREATE USER sgt WITH PASSWORD 'sgt';
CREATE DATABASE sgt TEMPLATE template0;
--UPDATE pg_database SET ENCODING = pg_char_to_encoding('LATIN1') WHERE datname = 'sgt';
GRANT ALL PRIVILEGES ON DATABASE sgt TO sgt;

\c sgt; 

CREATE TABLE IF NOT EXISTS public.tb_assuntos (
	cd_assunto_trf character varying(30) NOT NULL,
    cd_assunto_trf_superior character varying(30) NULL,    
	ds_assunto_trf character varying(200) NOT NULL,
	ds_assunto_completo text NOT NULL,
	ds_norma character varying(200) NULL,
	ds_lei_artigo text NULL,
    ds_assunto_trf_glossario text NULL,
    ds_alteracoes character varying(200) NULL,
	in_riscado bool NOT NULL DEFAULT false,
	in_possui_filhos bool NOT NULL DEFAULT false,
	in_importado bool NOT NULL DEFAULT false,
	CONSTRAINT tb_assuntos_pkey PRIMARY KEY (cd_assunto_trf)
);
CREATE INDEX idx_cd_assunto_superior ON public.tb_assuntos USING btree (cd_assunto_trf_superior);

GRANT SELECT ON TABLE public.tb_assuntos TO sgt;
GRANT INSERT ON TABLE public.tb_assuntos TO sgt;
GRANT UPDATE ON TABLE public.tb_assuntos TO sgt;
GRANT DELETE ON TABLE public.tb_assuntos TO sgt;
GRANT TRUNCATE ON TABLE public.tb_assuntos TO sgt;

CREATE TABLE IF NOT EXISTS public.tb_assuntos_avaliados (
	cd_assunto_trf character varying(30) NOT NULL,
	ds_assunto_trf character varying(200) NOT NULL,
    ds_abrangencia character varying(200) NULL,
	CONSTRAINT tb_assuntos_avaliados_pkey PRIMARY KEY (cd_assunto_trf)
);

GRANT SELECT ON TABLE public.tb_assuntos_avaliados TO sgt;
GRANT INSERT ON TABLE public.tb_assuntos_avaliados TO sgt;
GRANT UPDATE ON TABLE public.tb_assuntos_avaliados TO sgt;
GRANT DELETE ON TABLE public.tb_assuntos_avaliados TO sgt;
GRANT TRUNCATE ON TABLE public.tb_assuntos_avaliados TO sgt;

CREATE TABLE IF NOT EXISTS public.tb_assuntos_nao_avaliados (
	cd_assunto_trf character varying(30) NOT NULL,
    cd_assunto_trf_superior character varying(30) NULL,
	ds_assunto_trf character varying(200) NOT NULL,
	ds_assunto_completo text NOT NULL,
	ds_norma character varying(200) NULL,
	ds_lei_artigo text NULL,
    ds_assunto_trf_glossario text NULL,
    ds_alteracoes character varying(200) NULL,
	num_grau integer NOT NULL,
	CONSTRAINT tb_assuntos_nao_avaliados_pkey PRIMARY KEY (cd_assunto_trf)
);

GRANT SELECT ON TABLE public.tb_assuntos_nao_avaliados TO sgt;
GRANT INSERT ON TABLE public.tb_assuntos_nao_avaliados TO sgt;
GRANT UPDATE ON TABLE public.tb_assuntos_nao_avaliados TO sgt;
GRANT DELETE ON TABLE public.tb_assuntos_nao_avaliados TO sgt;
GRANT TRUNCATE ON TABLE public.tb_assuntos_nao_avaliados TO sgt;

CREATE TABLE IF NOT EXISTS public.tb_assuntos_carga (
	cd_assunto_trf character varying(30) NOT NULL,
    cd_assunto_trf_superior character varying(30) NULL,    
	ds_assunto_trf character varying(200) NOT NULL,
	ds_assunto_completo text NOT NULL,
	ds_norma character varying(200) NULL,
	ds_lei_artigo text NULL,
    ds_assunto_trf_glossario text NULL,
    ds_alteracoes character varying(200) NULL,
	num_grau integer NOT NULL,
	CONSTRAINT tb_assuntos_carga_pkey PRIMARY KEY (cd_assunto_trf)
);

GRANT SELECT ON TABLE public.tb_assuntos_carga TO sgt;
GRANT INSERT ON TABLE public.tb_assuntos_carga TO sgt;
GRANT UPDATE ON TABLE public.tb_assuntos_carga TO sgt;
GRANT DELETE ON TABLE public.tb_assuntos_carga TO sgt;
GRANT TRUNCATE ON TABLE public.tb_assuntos_carga TO sgt;