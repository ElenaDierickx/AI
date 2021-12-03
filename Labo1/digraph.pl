arrow(1,2).
arrow(2,3).
arrow(2,4).
arrow(2,5).
arrow(4,5).
arrow(4,6).
arrow(6,3).
is_path(A,B) :- arrow(A,B).
is_path(A,B) :- arrow(A,C), is_path(C,B).

/**
is_path(1,3).
is_path(X,2).
is_path(2,X).
is_path(X,Y).
arrow(Start, Middle),arrow(Middle,3).

*/