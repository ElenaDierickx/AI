area(cirkel(Straal, punt(X,Y)), Area) :- Area is 3.14 * Straal * Straal.
area(rectangle(Width, Length, punt(X,Y)), Area) :- Area is Width * Length.

circumference(cirkel(Straal, punt(X,Y)), Circumference) :- Circumference is 3.14 * Straal * 2.
circumference(rectangle(Width, Length, punt(X,Y)), Circumference) :- Circumference is Width * 2 + Length * 2.

