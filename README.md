# Equation-Solver
Napisz aplikacje obliczającą wyrazenia arytmetyczne
np:
java -jar Equation-Solver.jar "2 + 2 * 2"
wynikiem powinno byc 6.
Aplikacja powinna walidowac input, mozliwe wyjatki to:
- InvalidEquationFormatException
- UnknownOperatorException (wspieramy tylko +,-,*,/)
Fajnie by bylo gdyby kazdy operator byl reprezentowany przez jakas konkretna klase (jakis interface i jego implementacje)
Bylo by super, gdyby kazda operacja zapisywala sie do bazy danych
np: jakas historia wykonanych operacji
id|data|wyrazenie
mozesz zrobic budowanie z dwoma profilami, dev i prod.
dev moze korzystac z bazy danych H2
Natomiast PROD z jakiegos zainstalowanego na localhoscie mysql.
Calosc prosze ladnie pokryc testami jednostkowymi.
