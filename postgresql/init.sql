--Created by Bruno Paz.

CREATE USER sgt WITH PASSWORD 'sgt';
CREATE DATABASE sgt;
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
