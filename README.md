# VoteSmart - Platformă de Votare Online

## Tema 1 POO

Pentru realizarea acestei teme am implementat clasele:

- `Alegeri`
- `ItemiAlegeri`
- `Human`
- `Votant`
- `Candidat`
- `Circumscriptie`
- `Fraude`

### Structura Aplicației

În clasa `App`, un scanner citește constant de la tastatură comenzile utilizatorului până la întâlnirea comenzii `18`. Comenzile sunt gestionate printr-un `switch`, fiecare comandă apelând o metodă specifică pentru a afișa rezultatul. Dacă o comandă nu este recunoscută, se afișează mesajul **"Invalid command"**.

Majoritatea funcțiilor apelate în `App` sunt definite în clasa `Alegeri`. Aceasta:

- Parsează input-ul primit.
- Verifică erori preliminare (existența ID-ului, stadiul alegerilor etc.).
- Conține un `Map` alcătuit din ID-ul alegerilor și `ItemiAlegeri`.

### Clase și Funcționalități

#### Clasa `ItemiAlegeri`
Aceasta este cea mai mare clasă, conținând:
- Statusul alegerilor.
- Numele și ID-ul alegerilor.
- `Map` cu **candidati**.
- `Map` cu **votanți**.
- `Map` cu **circumscripții**.
- **Coadă de fraude**.

#### Clasa `Human`
- Parametri: `nume`, `varsta`, `CNP`.
- Conține constructor, `getters`, metodă de printare și `comparator`.
- Este clasa părinte pentru `Candidat` și `Votant`.

#### Clasa `Candidat`
Extinde `Human` și conține:
- `Map` unde cheia este **numele unei circumscripții**, iar valoarea este **numărul de voturi** în acea circumscripție.
- Metode:
  - Setters pentru `CNP`, `varsta`, `nume`.
  - Adăugare vot într-o circumscripție.
  - Returnarea numărului total de voturi într-o circumscripție.
  - Returnarea numărului total de voturi pe toate circumscripțiile.

#### Clasa `Votant`
Extinde `Human` și adaugă:
- Parametri: `indemânatic`, `circumscriptie`, flag `aVotat`.
- Metode:
  - Getters și setters.
  - Printarea tuturor votanților (override `Human`).

#### Clasa `Circumscriptie`
- Parametri: `nume` și `regiune`.
- Conține constructor, getters și setters.

#### Clasa `Fraude`
- Ține evidența votanților care au comis fraude.
- Constructor și getters.

### Implementări în `ItemiAlegeri`
- **Adăugare circumscripție:** Se creează un nou obiect `Circumscriptie`, se adaugă în `Map`, se returnează mesajul de succes.
- **Eliminare circumscripție:** Se verifică existența, apoi se elimină sau se returnează eroare.
- **Adăugare candidat:** Se verifică eligibilitatea și stadiul votării, apoi se adaugă în `Map`.
- **Eliminare candidat:** Se caută după `CNP`, dacă există se elimină, altfel se returnează eroare.
- **Adăugare votant:** Se verifică eligibilitatea și stadiul alegerilor, apoi se adaugă în `Map`.
- **Afisare candidați:** Se verifică existența acestora, se sortează lista și se afișează.
- **Afisare votanți:** Se verifică existența circumscripției, se sortează lista și se afișează.
- **Adăugare vot:** Se verifică existența circumscripției, votantului și candidatului. Se validează dacă votantul mai poate vota. Dacă a votat deja sau nu e în circumscripția corectă, se înregistrează ca fraudă. În caz contrar, votul este înregistrat.
- **Raport voturi pe circumscripție:** Se sortează lista de candidați după numărul de voturi și `CNP`, apoi se afișează.
- **Raport voturi național:** Se sortează candidații după numărul total de voturi și `CNP`, apoi se afișează.
- **Analiză voturi pe circumscripție:** Se determină candidatul cu cele mai multe voturi pe o anumită circumscripție și la nivel național.
- **Analiză voturi național:** Se utilizează o clasă internă `InfoRegiune` pentru a menține evidența voturilor pe regiune și a candidatului cu cele mai multe voturi.
- **Gestionare fraude:** Se folosesc structuri FIFO pentru a menține ordinea fraudelor detectate.

## Cazuri Limită Adiționale
Pentru îmbunătățirea aplicației, aș adăuga verificări pentru:
- **Validarea intrărilor incomplete sau incorecte**.
- **Gestionarea CNP-urilor duplicate sau invalide**.
- **Verificarea stărilor invalide ale alegerilor** (ex: încercarea de a porni o alegere deja începută).

