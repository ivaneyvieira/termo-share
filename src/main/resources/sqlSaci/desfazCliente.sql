USE sqldados;

UPDATE sqldados.custp AS C
SET C.s6 = POW(2, 6)
WHERE no = :custno;

DO @RMKNO := IFNULL((SELECT MAX(rmkno)
		     FROM sqldados.ctrmk
		     WHERE custno = :custno), 0);

INSERT INTO sqldados.ctrmk(custno, date, time, auxLong1, auxLong2, auxLong3, auxLong4, auxMy1,
			   auxMy2, rmkno, userno, ctrmktno, bits, bits2, bits3, bits4, bits5, rmk,
			   auxString)
SELECT no                                              AS custno,
       CURRENT_DATE * 1                                AS date,
       TIME_TO_SEC(CURRENT_TIME) + 10                  AS time,
       0                                               AS auxLong1,
       0                                               AS auxLong2,
       0                                               AS auxLong3,
       0                                               AS auxLong4,
       0                                               AS auxMy1,
       0                                               AS auxMy2,
       @RMKNO := @RMKNO + 1                            AS rmkno,
       1                                               AS userno,
       13 /*Tipo de observação*/                       AS ctrmktno,
       0                                               AS bits,
       0                                               AS bits2,
       0                                               AS bits3,
       0                                               AS bits4,
       0                                               AS bits5,
       'LGPD - CANCELAMENTO DO TERMO DE CONSENTIMENTO' AS rmk,
       ''                                              AS auxString
FROM sqldados.custp AS C
WHERE no = :custno


