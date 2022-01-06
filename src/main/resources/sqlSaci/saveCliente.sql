USE sqldados;


UPDATE sqldados.custp AS C
SET C.s6 = POW(2, 0) * :flagEntregaTroca + POW(2, 1) * :flagUsoAsistencia +
	   POW(2, 2) * :flagHorarioDias + POW(2, 3) * :flagPromocoesOferta +
	   POW(2, 4) * :flagPesquisaSatisfacao + POW(2, 5) * :flagCadastro
WHERE no = :custno;

DO @RMKNO := IFNULL((SELECT MAX(rmkno)
		     FROM sqldados.ctrmk
		     WHERE custno = :custno), 0);

DROP TEMPORARY TABLE IF EXISTS T_TERMOS;
CREATE TEMPORARY TABLE T_TERMOS (
  PRIMARY KEY (custno, num)
)
SELECT 2                                               AS num,
       :custno                                         AS custno,
       'LGPD - TERMO DE CONSENTIMENTO (ENTREGA/TROCA)' AS termoOpt
FROM DUAL
WHERE :flagEntregaTroca != 0
UNION
SELECT 3                                                 AS num,
       :custno                                           AS custno,
       'LGPD - TERMO DE CONSENTIMENTO (USO/ASSISTENCIA)' AS termoOpt
FROM DUAL
WHERE :flagUsoAsistencia != 0
UNION
SELECT 4                                               AS num,
       :custno                                         AS custno,
       'LGPD - TERMO DE CONSENTIMENTO (HORARIOS/DIAS)' AS termoOpt
FROM DUAL
WHERE :flagHorarioDias != 0
UNION
SELECT 5                                                   AS num,
       :custno                                             AS custno,
       'LGPD - TERMO DE CONSENTIMENTO (PROMOCOES/OFERTAS)' AS termoOpt
FROM DUAL
WHERE :flagPromocoesOferta != 0
UNION
SELECT 6                                                     AS num,
       :custno                                               AS custno,
       'LGPD - TERMO DE CONSENTIMENTO (PESQUISA/SATISFACAO)' AS termoOpt
FROM DUAL
WHERE :flagPesquisaSatisfacao != 0
UNION
SELECT 1                                          AS num,
       :custno                                    AS custno,
       'LGPD - TERMO DE CONSENTIMENTO (CADASTRO)' AS termoOpt
FROM DUAL
WHERE :flagCadastro != 0;


INSERT INTO sqldados.ctrmk(custno, date, time, auxLong1, auxLong2, auxLong3, auxLong4, auxMy1,
			   auxMy2, rmkno, userno, ctrmktno, bits, bits2, bits3, bits4, bits5, rmk,
			   auxString)
SELECT no                              AS custno,
       CURRENT_DATE * 1                AS date,
       TIME_TO_SEC(CURRENT_TIME) + num AS time,
       0                               AS auxLong1,
       0                               AS auxLong2,
       0                               AS auxLong3,
       0                               AS auxLong4,
       0                               AS auxMy1,
       0                               AS auxMy2,
       @RMKNO := @RMKNO + 1            AS rmkno,
       1                               AS userno,
       12 /*Tipo de observação*/       AS ctrmktno,
       0                               AS bits,
       0                               AS bits2,
       0                               AS bits3,
       0                               AS bits4,
       0                               AS bits5,
       T.termoOpt                      AS rmk,
       ''                              AS auxString
FROM sqldados.custp   AS C
  INNER JOIN T_TERMOS AS T
	       ON T.custno = C.no
WHERE no = :custno
ORDER BY T.num

