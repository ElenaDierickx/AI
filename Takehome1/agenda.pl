:- dynamic meeting/3.

colleague(joris).
colleague(katja).
colleague(peter).
colleague(kristien).
colleague(yves).

meeting([joris, piet, cindy, jan], date(2021, 12, 10, 15, 30, 0, _, _, _), date(2021, 12, 10, 17, 30, 0, _, _, _)).
meeting([katja, jess, axie, kai], date(2021, 12, 18, 15, 30, 0, _, _, _), date(2021, 12, 18, 17, 30, 0, _, _, _)).
meeting([kristien, yves, cindy, jan], date(2021, 12, 20, 15, 30, 0, _, _, _), date(2021, 12, 20, 17, 30, 0, _, _, _)).
meeting([joris, piet, cindy, jan], date(2021, 12, 12, 15, 30, 0, _, _, _), date(2021, 12, 12, 17, 30, 0, _, _, _)).

print_row([]).
print_row([H|T]) :- write(H), nl, print_row(T).

print_row2([]).
print_row2([H|T]) :- 
    nth0(X, [H|T], H),
    write(X), write(' '), write(H), nl, print_row2(T, [H|T]).
print_row2([], _).
print_row2([H|T], List) :- 
    nth0(X, List, H),
    write(X), write(' '), write(H), nl, print_row2(T, List).  

check_colleague([]).
check_colleague([H|T]) :- 
    colleague(H),
    check_colleague(T).

date_inbetween(StartDate1, EndDate1, StartDate2, EndDate2) :-
    not((StartDate1 @< StartDate2, StartDate2 @< EndDate1)),
    not((StartDate1 @< EndDate2, EndDate2 @< EndDate1)),
    not((StartDate2 @< StartDate1, StartDate1 @< EndDate2)),
    not((StartDate2 @< EndDate1, EndDate1 @< EndDate2)).

checkall(Person, StartDate, EndDate) :-
    meetings_of_person(Person, List),
    not(check_dates(List, StartDate, EndDate)).

meetings_of_person(Person, List) :-
    findall([Persons, Start, End], (meeting(Persons, Start, End), member(Person, Persons)), List).

check_dates([H|_], DateStart, DateEnd) :-
    nth0(1, H, X),
    nth0(2, H, Y),
    not(date_inbetween(X, Y, DateStart, DateEnd)), !.

check_dates([_|T], DateStart, DateEnd) :-
    check_dates(T, DateStart, DateEnd).


check_persons([H|_], StartDate, EndDate) :-
    colleague(H),
    meetings_of_person(H, List),
    check_dates(List, StartDate, EndDate), !.

check_persons([_|T], StartDate, EndDate) :-
    check_persons(T, StartDate, EndDate).

menu :-
nl,
write('>    Voer een keuze in en eindig met een punt.'), nl,
write('>    1. Voeg een meeting toe'), nl,
write('>    2. Geef de volgende afspraken weer van een persoon'), nl,
write('>    3. Past persoon X in tijdslot Y'), nl,
write('>    4. Annuleer een meeting'), nl,
read(Choice),
menu_option(Choice), menu. 

menu_option(1) :-
    write('Aanmaken van een meeting. Voer de datum in van de afspraak in het formaat: DD/MM/JJJJ (bv. 07/12/2021)'), nl,
    read(DD/MM/YYYY),
    write('Aanmaken van een meeting. Voer de starttijd in van de afspraak in het formaat: HH:MM (bv. 14:30)'), nl,
    read(HH:II),
    write('Aanmaken van een meeting. Voer de eindtijd in van de afspraak in het formaat: HH:MM (bv. 15:30)'), nl,
    read(HH2:II2),
    write('Aanmaken van een meeting. Geef een lijst met namen die deelnemen aan de meeting in dit formaat: [naam, naam, naam] (bv. [jan, bert, katja].)'), nl,
    read(Persons),
    not(check_persons(Persons, date(YYYY,MM,DD,HH,II,0,_,_,_), date(YYYY,MM,DD,HH2,II2,0,_,_,_))),
    assert(meeting(Persons, date(YYYY,MM,DD,HH,II,0,_,_,_), date(YYYY,MM,DD,HH2,II2,0,_,_,_))),
    write('De meeting is aangemaakt'), nl.

menu_option(2) :-
    write('Meetings ophalen van een persoon. Geef de naam van de persoon waarvan u de meetings wilt ophalen met een kleine letter en eindig met een . (bv. joris.)'), nl,
    read(Person),
    meetings_of_person(Person, List),
    print_row(List).

menu_option(3) :-
    write('Past persoon X in tijdslot Y. Geef de naam van persoon X en eindig met een . (bv. joris.)'), nl,
    read(Person),
    write('Past persoon X in tijdslot Y. Geef de datum van tijdslot Y in het formaat: DD/MM/JJJJ (bv. 07/12/2021)'), nl,
    read(DD/MM/JJJJ),
    write('Past persoon X in tijdslot Y. Geef de starttijd van tijdslot Y in het formaat: HH:MM (bv. 14:30)'), nl,
    read(HH:II),
    write('Past persoon X in tijdslot Y. Geef de eindtijd van tijdslot Y in het formaat: HH:MM (bv. 15:30)'), nl,
    read(HH2:II2),
    checkall(Person, date(JJJJ,MM,DD,HH,II,0,_,_,_), date(JJJJ,MM,DD,HH2,II2,0,_,_,_)),
    write('Dit persoon past in het opgegeven tijdslot.'), nl.

menu_option(4) :-
    write('Annuleren van een meeting. Geef de persoon van wie je een meeting wilt annuleren met op het einde een . (bv. joris.)'), nl,
    read(Person),
    meetings_of_person(Person, List),
    print_row2(List),
    write('Annuleren van een meeting. Geef het nummer van de meeting die je wilt verwijderen. bv(0.)'), nl,
    read(Index),
    nth0(Index, List, Meeting),
    nth0(0, Meeting, Meeting0),
    nth0(1, Meeting, Meeting1),
    nth0(2, Meeting, Meeting2),
    retract(meeting(Meeting0, Meeting1, Meeting2)),
    write('Annuleren van een meeting. De meeting is succesvol geannuleerd.'), nl.

menu_option(abort) :-
    fail.



