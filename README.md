# project-krajnik-gwizdala
project-krajnik-gwizdala created by GitHub Classroom
Pełna dokumentacja dostępna w pliku: MedClinic dokumentacja.docx, <a href="https://github.com/WMP-INF-Ist-S-2017-18-L2/project-krajnik-gwizdala/blob/master/MedClinic%20dokumentacja.docx">link</a>
<h1>MedClinic – internetowa obsługa przychodni</h1>

<h3>Spis treści:</h3>
<ul>
 <li>1.Autorzy</li>
 <li>2.Opis działania oraz zastosowane technologie</li>
 <li>3.Wymagania</li>
 <li>4.Konfiguracja</li>
 <li>5.Sposób użytkowania</li>
 <li>6.Struktura bazy danych</li>
 <li>7.Możliwe do wystąpienia błędy/problemy</li>
</ul>


<h4>1.	Autorzy<h4>
 <p>Autorami projektu są: Gwizdała Patryk oraz Krajnik Mateusz.</p>
<h4>2.	Opis działania oraz zastosowane technologie</h4>
<p>Aplikacja symuluje działanie wirtualnej przychodni, gdzie pacjenci mogą rejestrować swoje wizyty a doktorzy mają podgląd tych wizyt. Obecny jest też panel administratora do zarządzania całością. Jest to webowa aplikacja uruchamiana w przeglądarce internetowej korzystająca z bazy danych. Zastosowane technologie to język programowania Java, framework webowy Vaadin w wersji 12. Aplikacja oparta jest na platformie Spring z wykorzystaniem SpringBoot. Szczegółowe informacje o zawartych pakietach znajdują się w pliku pom.xml.</p>

<h4>3.	Wymagania:</h4>
<ul>
 <li>Środowisko uruchomieniowe Java w wersji 8.</li>
 </ul>

<h4>4.	Konfiguracja</h4>
<o>Konfiguracja aplikacji (ustawienie dostępu do bazy danych) jest możliwa do dokonania w pliku applicationproperties w katalogu src/main/resources.</p>

<h4>5.	Sposób użytkowania</h4>
<o>Aplikacja wymaga utworzenia konta pacjenta (konto doktora jest dodawane przez administratora). Po zalogowaniu się właściwym adresem email oraz poprawnym dla tego adresu hasłem następuje przekierowanie do panelu pacjenta.</p>

<p> W panelu pacjenta aktualnie zalogowany użytkownik po lewej stronie może zobaczyć swoje dane, w panelu głównym (na środku) istnieje widok zarejestrowanych wizyt oraz elementy umożliwiające zarejestrowanie nowej wizyty.
Po zarejestrowaniu nowej wizyty pojawi się ona w tabeli w panelu pacjenta a także zostanie wysłane powiadomienie na adres e-mail.</p>

<p>Dostępny jest także panel administratora do zarządzania wizytami, kontami pacjentów, lekarzy oraz oddziałami przychodni.</p>

<h4>6.	Baza danych połączona jest pomiędzy tabelami relacjami</h4>

<h4>7.	Możliwe do wystąpienia błędy/problemy</h4>
<ul>
 <li>Jeśli baza danych nie zostanie „podpięta” (oraz wypełniona danymi) do programu, elementy Grid oraz Combobox nie zostaną wypełnione treścią a ni nie będziemy mogli zapisywać danych do bazy.</li>
 <li>Gdy podczas rejestracji podany zostanie niewłaściwy (nieistniejący)  adres e-mail powiadomienie nie zostanie dostarczone.</li>
<li>Podanie numeru PESEL oraz numeru telefonu w innym niż powszechnie znany formacie lub zastosowanie tekstu w tych polach będzie skutkowało błędem.</li>
</ul>

<h2> Wprowadzone poprawki</h2>
Branch: gadzialf
<p><b>18.01.2019 update 1</b></p>
 <ul>
  <li>Poprawnie działające wylogowanie,</li>
 </ul>
