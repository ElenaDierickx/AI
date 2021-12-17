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



% check_meetings(Colleague, StartDate, EndDate) :-
    

check_colleague([]).
check_colleague([H|T]) :- 
    colleague(H),
    check_colleague(T).

menu :-
nl,
write('>    Voer een keuze in en eindig met een punt.'), nl,
write('>    1. Voeg een meeting toe'), nl,
write('>    2. Geef de volgende afspraken weer van een persoon'), nl,
write('>    3. Past persoon X in tijdslot Y'), nl,
write('>    4. Annuleer een afspraak'), nl,
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
    assert(meeting(Persons, date(YYYY,MM,DD,HH,II,0,_,_,_), date(YYYY,MM,DD,HH2,II2,0,_,_,_))),
    write('De meeting is aangemaakt'), nl.

menu_option(5) :-
forall(meeting(Persons, StampStart, StampEnd), (write('Personen: '), write(Persons), write(' StartDate: '), write(StampStart), write(' EndDate: '), writeln(StampEnd))).


datetest :-
    write('enter date as `YYYY-MM-DD.`:'),
    read(YYYY-MM-DD),
    D1 is DD+20,
    date_time_stamp(date(YYYY,MM,D1,15,20,0,_,_,_),Stamp),
    stamp_date_time(Stamp,D,local),
    write(D),
    date_time_value(date,D,DV),
    format('result date:~w', [DV]).

my_member(Element, [Element |_]).
my_member(Element, [_ |T]) :- my_member(Element,T).

meetings_of_person(Colleague, List) :-
    findall([Colleague, Start, End], (meeting(Colleagues, Start, End), member(Colleague, Colleagues)), List).



