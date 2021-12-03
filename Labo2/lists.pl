my_member(Element, [Element |_]).
my_member(Element, [_ |T]) :- my_member(Element,T).

/**
Zit 5 in lijst?
my_member(5,[1,2,3,4,5]). True
my_member(5,[1,2,3,4]). False
*/

my_length([], 0).
my_length([_], 1).
my_length([_|T], Length) :- my_length(T,L), Length is L + 1. 

/**
my_length([1,2,3,4], Length). 
Length = 4.
*/

print_row([]).
print_row([H|T]) :- write(H), tab(2), print_row(T).

print_table([]).
print_table([H|T]) :- print_row(H), nl, print_table(T).

/**
print_table([[1,2,3],[4,5,6]]).
1  2  3  
4  5  6
*/