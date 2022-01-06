DO @NOME := TRIM(:filtro);
DO @CODIGO := @NOME * 1;
DO @TIPO := :tipo;

DROP TEMPORARY TABLE IF EXISTS T;
CREATE TEMPORARY TABLE T
SELECT C.no                                                         AS codigo,
       C.name                                                       AS nome,
       C.cpf_cgc                                                    AS cpf,
       IF(LENGTH(C3.auxString6) > 5, IFNULL(C3.auxString6, ''), '') AS email,
       (C.s6 & POW(2, 0)) != 0                                      AS flagEntregaTroca,
       (C.s6 & POW(2, 1)) != 0                                      AS flagUsoAsistencia,
       (C.s6 & POW(2, 2)) != 0                                      AS flagHorarioDias,
       (C.s6 & POW(2, 3)) != 0                                      AS flagPromocoesOferta,
       (C.s6 & POW(2, 4)) != 0                                      AS flagPesquisaSatisfacao,
       (C.s6 & POW(2, 5)) != 0                                      AS flagCadastro,
       (C.s6 & POW(2, 6)) != 0                                      AS flagCancelado,
       MAX(ADDDATE(R.date, INTERVAL R.time SECOND))                 AS dataHoraAceite,
       MAX(ADDDATE(RC.date, INTERVAL RC.time SECOND))               AS dataHoraCancelamento
FROM sqldados.custp          AS C
  LEFT JOIN sqldados.ctmore3 AS C3
	      ON C.no = C3.custno
  LEFT JOIN sqldados.ctrmk   AS R
	      ON R.custno = C.no AND R.ctrmktno = 12
  LEFT JOIN sqldados.ctrmk   AS RC
	      ON RC.custno = C.no AND RC.ctrmktno = 13
WHERE C.fjflag = 1
  AND (NAME LIKE CONCAT('%', @NOME, '%') OR @NOME = '' OR C.no = @CODIGO)
  AND CASE @TIPO
	WHEN 'B'
	  THEN TRUE
	WHEN 'T'
	  THEN (C.s6 & POW(2, 5)) = 0 AND (C.s6 & POW(2, 6)) = 0
	WHEN 'A'
	  THEN (C.s6 & POW(2, 5)) != 0
	WHEN 'C'
	  THEN (C.s6 & POW(2, 6)) != 0
	ELSE FALSE
      END
GROUP BY C.no
ORDER BY C.no;

SELECT codigo,
       nome,
       cpf,
       email,
       flagEntregaTroca,
       flagUsoAsistencia,
       flagHorarioDias,
       flagPromocoesOferta,
       flagPesquisaSatisfacao,
       flagCadastro,
       flagCancelado,
       CAST(dataHoraAceite AS DATETIME)       AS dataHoraAceite,
       CAST(dataHoraCancelamento AS DATETIME) AS dataHoraCancelamento
FROM T




