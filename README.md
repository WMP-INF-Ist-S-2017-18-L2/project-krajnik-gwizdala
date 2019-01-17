# project-krajnik-gwizdala
project-krajnik-gwizdala created by GitHub Classroom
MedClinic – internetowa obsługa przychodni

Spis treści: 
1.	Autorzy
2.	Opis działania oraz zastosowane technologie
3.	Wymagania aplikacji 
4.	Konfiguracja
5.	Sposób użytkowania
6.	Struktura bazy danych
7.	Możliwe do wystąpienia błędy/problemy



1.	Autorzy
Autorami projektu są: Gwizdała Patryk oraz Krajnik Mateusz.
2.	Opis działania oraz zastosowane technologie
Aplikacja symuluje działanie wirtualnej przychodni, gdzie pacjenci mogą rejestrować swoje wizyty a doktorzy mają podgląd tych wizyt. Obecny jest też panel administratora do zarządzania całością. Jest to webowa aplikacja uruchamiana w przeglądarce internetowej korzystająca z bazy danych. Zastosowane technologie to język programowania Java, framework webowy Vaadin w wersji 12. Aplikacja oparta jest na platformie Spring z wykorzystaniem SpringBoot. Szczegółowe informacje o zawartych pakietach znajdują się w pliku pom.xml.

3.	Wymagania:
a.	Środowisko uruchomieniowe Java w wersji 8.
4.	Konfiguracja
Konfiguracja aplikacji (ustawienie dostępu do bazy danych) jest możliwa do dokonania w pliku applicationproperties w katalogu src/main/resources.
 

5.	Sposób użytkowania
Aplikacja wymaga utworzenia konta pacjenta (konto doktora jest dodawane przez administratora). Po zalogowaniu się właściwym adresem email oraz poprawnym dla tego adresu hasłem następuje przekierowanie do panelu pacjenta.

 
 

 W niżej pokazanym panelu aktualnie zalogowany użytkownik po lewej stronie może zobaczyć swoje dane, w panelu głównym (na środku) istnieje widok zarejestrowanych wizyt oraz elementy umożliwiające zarejestrowanie nowej wizyty.
Po zarejestrowaniu nowej wizyty pojawi się ona w tabeli w panelu pacjenta a także zostanie wysłane powiadomienie na adres e-mail.







Dostępny jest także panel administratora do zarządzania wizytami, kontami pacjentów, lekarzy oraz oddziałami przychodni.


6.	Baza danych połączona jest pomiędzy tabelami relacjami  

7.	Możliwe do wystąpienia błędy/problemy
Jeśli baza danych nie zostanie „podpięta” (oraz wypełniona danymi) do programu, elementy Grid oraz Combobox nie zostaną wypełnione treścią a ni nie będziemy mogli zapisywać danych do bazy.
Gdy podczas rejestracji podany zostanie niewłaściwy (nieistniejący)  adres e-mail powiadomienie nie zostanie dostarczone.
Podanie numeru PESEL oraz numeru telefonu w innym niż powszechnie znany formacie lub zastosowanie tekstu w tych polach będzie skutkowało błędem.
