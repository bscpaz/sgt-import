--Created by Bruno Paz.

CREATE USER sgt WITH PASSWORD 'sgt';
CREATE DATABASE sgt;
GRANT ALL PRIVILEGES ON DATABASE sgt TO sgt;

\c sgt; 

CREATE TABLE IF NOT EXISTS public.tb_assunto (
	cd_assunto_trf character varying(30) NOT NULL,
    cd_assunto_trf_superior character varying(30) NULL,    
	ds_assunto_trf character varying(150) NOT NULL,
	ds_norma character varying(200) NULL,
	ds_lei_artigo text NULL,
    ds_assunto_trf_glossario text NULL,
    ds_alteracoes character varying(200) NULL,
	in_ativo bool NOT NULL DEFAULT true,
	CONSTRAINT tb_assunto_pkey PRIMARY KEY (cd_assunto_trf)
);
CREATE INDEX idx_cd_assunto_superior ON public.tb_assunto USING btree (cd_assunto_trf_superior);