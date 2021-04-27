# BitClass

Scopul aplicatiei BitClass este de a-i ajuta pe studentii de la Facultatea de Automatica si Calculatoare in alegerea cursurilor la facultate si stabilirea orarului pentru materiile optionale.

In lipsa unui sistem bine pus la punct, alegerea intervalelor orare pentru laboratoare poate fi un adevarat stres. Astfel ca BitClass va rezolva aceasta problema.

Va fi o aplicatie Web, realizata in limbajul Python, cu framework-ul Django (atat pentru backend cat si penru frontend), baza de date va fi Realtime Database din Firebase iar hostarea se va face fie pe Heroku, fie pe Google Cloud.

Vom avea 2 tipuri de useri:

•	Studenti

•	Administrator

Fiecare user se lohgeaza in aplicatie cu un username/adresa de mail si o parola, si ajunge la pagina principala. In cazul in care un student nu are cont, acesta isi poate crea unul adaugand detaliile urmatoare:

-nume

-prenume

-username

-adresa

-email (cel de la facultate)

-parola

Confimarea contului se va face prin intermediul adresei de email.

Pe pagina principala vor fi mai multe optiuni in functie de tipul user-ului:

-Usecase student

In cazul in care studentul este inrolat la cursuri, vor aparea numele cursurilor la care acesta este inscris + cate un icon la fiecare + optiunea de a-si selecta orarul pt laboratoarele materiilor optionale. Daca acesta nu este inca inscris la niciun curs, va fi afisat mesajul “No available courses” si astfel va trebui sa se inscrie la cursuri si apoi sa-si aleaga orele laboratoarelor la materiile optionale.

Pe pagina in care studentul isi alege materia la care doreste sa se inscrie, langa fiecare materie va aparea si un procent care reprezinta probabilitatea ca el sa intre la cursul respectiv bazat pe media pe care o are (daca este un curs la care ultima medie de intrare a fost mai mare ca media lui curenta, probabilitatea va fi mai mica). De asemenea, studentul nu se va putea inscrie la anumite cursuri daca nu a promovat cursuri precedente celui la care doreste sa aplice. Aceste cursuri vor avea culoarea titlului gri pentru a marca acest lucru.

Pentru selectarea orarului, studentului ii vor aparea locurile disponibile la laboratoare in fiecare interval orar dupa ce ceilalti studenti cu prioritate mai mare si-au ales deja intervalul dorit. Toti studentii vor avea obligatia sa-si aleaga intervalele orare in termen de 48 de ore. In cazul in care studentul nu isi alege intervalul dorit in termenul stabilit, ii vor fi alocate aleatoriu slot-urile ramase disponibile.
Studentul va primi un mail prin care va fi notificat in momentul in care cel cu prioritatea imediat mai mare ca a lui si-a ales intervalul orar, si astfel isi va putea alege si el. 

De asemenea, studentul va avea optiunea de a intra pe pagina MyProfile si a-si vedea numele, prenumele, adresa de email si media generala. De asemenea, tot pe aceasta pagina va avea posibilitatea de a-si schimba emailul sau parola.

-Usecase administrator

-a adauga,sterge si edita cursuri. De asemenea, va avea optiunea de a modifica si adauga slot-uri in orarul laboratoarelor.
- setarea numarului de locuri la fiecare curs
- o pagina de introducere, validare si modificare a mediilor studentilor. Pot exista mai multe medii care sa fie folosite ca si criterii, de exemplu media multianuala, media ultimului an absolvit, mediile la anumite materii considerate importante pentru cursurile care sunt alese. In pagina ar trebui sa apara cel putin un tabel cu studentii, mediile fiecaruia si o posibilitate simpla de a modifica mediile pentru fiecare, fara prea multe click-uri: de exemplu intrand direct in campul cu media si modificand-o -- campul cu media trebuie sa apara initial read-only, dar sa existe un buton global "Modifica" si respectiv unul  "Save". Apasand pe "Modifica", campurile cu note devin modificabile, iar Save salveaza modificarile si le trece apoi iar in starea read-only.
- o pagina de unde sa porneasca algoritmul de distribuire a studentilor la cursuri. Rezultatul va fi un set de tabele, exportabile ca PDF sau HTML, cu cursurile si studentii alocati.
- o pagina de configurare a algoritmului de distribuire. Algoritmul va fi un element central al aplicatiei si pentru el se vor putea configura elemente, in primul rand criteriile de departajare
(ordinea alfabetica, medii) si ordinea in care aceste criterii se aplica (prima data se ia media multianuala, apoi cea din ultimul an in caz ca exista studenti cu medii egale etc).