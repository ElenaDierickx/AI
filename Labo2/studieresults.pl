studenten(lab1, [freek, jana, yentl, arnout]).
studenten(lab2, [marthe, roel, stef]).

scores(lab1, javafund, [12, 14.5, 4, 9]).
scores(lab2, javafund, [18.5, 17, 12]).
scores(lab1, wmintro, [14, 17.5, 8, 13]).
scores(lab2, wmintro, [19, 10, 11]).

max_java :- 
scores(lab1, javafund, List),
max_member(Max, List),
write(Max).

max_student :-
scores(lab1, javafund, List),
studenten(lab1, Students),
max_member(Max, List),
nth0(Index, List, Max),
nth0(Index, Students, Student),
write(Student).

average_score(Lab, Course, Average) :-
scores(Lab, Course, List),
sum_list(List, Sum),
length(List, Length),
Average is Sum / Length.

namen_en_punten :- 
findall(CijferJava, (scores(_, javafund, CijfersJava), member(CijferJava, CijfersJava)), LJavaFund),
findall(Cijferwm, (scores(_, javafund, Cijferswm), member(Cijferwm, Cijferswm)), Lwmintro),
findall(Studenten, studenten(_,Studenten), Lnamen), flatten(Lnamen, Namen),
transpose([["Name" | Namen], ["cijferj" | LJavaFund], ["cijferWM"|Lwmintro]], Ts),
print_table(Ts).

stapfunctie(X,0) :- X < 0, !.
stapfunctie(X,1) :- X < 5, !.
stapfunctie(_,2).


/**

Maximumscore javafund in lab1:
max_java.
14.5

Wie behaalde deze maximumscore?:
max_student
jana

Wat is de gemiddelde score voor wmintro in lab1?:
average_score(lab1, wmintro, Average).
Average = 13.125.

Lijst alle gemiddelde scores per score-feit op:
findall(res(Lab, Course, Average), average_score(Lab, Course, Average), List).


Verzamel een (1-dimensionale) lijst met álle resultaten voor javafund:
findall(Cijfers, scores(_,javafund,Cijfers), List), flatten(List, Punten).

Print m.b.v. print_table/1 een mooie tabel met alle namen van de studenten en alle resultaten:
bibliotheek laden:
use_module(library(clpfd)).
namen_en_punten.

*/