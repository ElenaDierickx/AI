father(jean, simonne).
father(jean, luc).
father(jean, bernard).
father(jean, saar).

mother(anja, simonne).
mother(anja, luc).
mother(anja, bernard).
mother(anja, saar).

father(albert, miranda).
father(albert, vincent).
father(albert, belinda).

mother(simonne, miranda).
mother(simonne, vincent).
mother(simonne, belinda).

father(bernard, tom).
father(bernard, linda).

mother(inge, tom).
mother(inge, linda).

father(karel, koen).
father(karel, jan).

mother(saar, koen).
mother(saar, jan).

male(vincent).
male(tom).
male(koen).
male(jan).
female(miranda).
female(belinda).
female(linda).

parent(Parent,Child) :- mother(Parent,Child).
parent(Parent,Child) :- father(Parent,Child).

son(Son, Parent) :- male(Son), parent(Parent, Son).
daughter(Daughter, Parent) :- female(Daughter), parent(Parent, Daughter).

grandfather(Grandfather, Grandchild) :- father(Grandfather, X), parent(X,Grandchild).
grandparent(Grandparent, Grandchild) :- parent(Grandparent,X), parent(X,Grandchild).

predecessor(Preparent, Child) :- parent(Preparent,Child).
predecessor(Preparent, Child) :- parent(Preparent,X), predecessor(X,Child).

brother(Brother, Sibling) :- sibling(Brother, Sibling), male(Brother).
sibling(Sibling1, Sibling2) :- father(Father, Sibling1), mother(Mother, Sibling1), father(Father, Sibling2), mother(Mother, Sibling2), Sibling1 \= Sibling2.

children(Parent, List) :- findall(Child, parent(Parent, Child), List).

demo() :- write('Van wie wil je de kinderen?'),
    read(Person),
    children(Person, Child),
    write(Child).





/**
father(bernard,tom).
mother(anja,jan).
father(karel,Child).
mother(simonne, Child),male(Child). 

parent(simonne,Child).

alle zonen
findall(Sons, son(Sons,jean), List).
*/