# SWAGTRAVEL: super optimaalne lennureisi planeerimisportaal
![SWAGTRAVEL](https://github.com/user-attachments/assets/61dfcc88-b51c-4428-b2cb-37c6bd7f6fcf)

## KÄIVITAMINE
* Backend:
    1. Liigu kausta src/main/java/org/example/lennuplaneerija/
    2. Käivita LennuplaneerijaApplication.java IDE kaudu
    3. **VÕI**, olles projektikaustas (LENNUPLANEERIJA), jooksuta käsku `./gradlew bootRun`
      
 * Frontend:
    1. Olles projektikaustas (LENNUPLANEERIJA) liigu käsurea kaudu kasuta "frontend" `cd src/frontend`
    2. Käivita frontend käsuga `npm run serve`
    3. **NB! Vajadusel tõmba alla puuduvaid pakette (jälgi käsurea teavitusi)**

## TAGASIVAADE TEHTUD TÖÖLE

### AJA JAOTUS
- Olen varem veebirakendustega kokku puutunud, kuid Spring Booti raamistikku kasutasin esimest korda.
Seetõttu kulus alguses aega, et aru saada, milline backend-struktuur välja nägema peaks.
- Kahjuks kulus **kaugelt kõige rohkem** aega välise lennuotsimise API leidmisele ning implementeerimisele.
Sain liiga hilja aru, et valitud API sisend-parameetrid ei ole hästi dokumenteeritud ning tegelikult siia projekti ei sobi.
Vaatamata sellele, oli see päris hea õppimiskogemus:
    1. Kindlasti ei tohi oma API key-d koodi hardcode'ida kuna see läheb GitHubi üles :)
    2. Enne API valikut tasub mitu korda veenduda, et selle sisend ja väljund vastab sinu ootustele
    3. Tasuta pakettides on enamasti karmid piirangud sellele, mitu API-calli teha tohib. Tihti ei ole need API'd isegi kõige kvaliteetsemad,
  seega aja olemasolul võiks kaaluda lihtsalt ise veebikoorimise skripti kirjutamist
- Kõik ülejäänud tegevused võtsid väga proportsionaalselt aega: seda on üldiselt näha ka commit-ajaloost. Jagasin projekti väiksemateks osadeks
ning püüdsin iga päev mingi uue funktsionaalsuse sisse tuua.

### PROBLEEMSED KOHAD
Massiivseid raskuseid õnneks ei olnud. Üldjuhul tekkisid:
1. väiksemat sorti küsimused front ja backendi ühendamise kohta -> abi sai YouTube'ist või tehisintellektilt (kohad märgitud koodis)
2. frontendis info salvestamise ning uuendamise kohta -> abi sai internetist või tehisintellektilt (kohad märgitud koodis)
3. syntaxi küsimused -> abi internetist

### OLEKS TAHTNUD PAREMINI, AGA EI JÕUDNUD
- Ei ole 100% rahul istumissoovitus algoritmiga. Hetkene lahendus töötab "lennufirma kasuks" ning prioritiseerib lennuki proportsionaalset täitumist ehk
kui kasutajal on mingi eelistus, siis see võetakse arvesse vähemalt 1 istme puhul (kui on piisavalt vabu kohti). Edasiarendusena saaks rohkem arvestada reisijaga
ning soovitada kohti eelkõige kõrvuti.
- Kindlasti on istmete soovitamiseks välja mõeldud väga kenasid ning optimaalseid lahendusi ning täiustusena võiks ka neid uurida ning enda versiooni kohandada. Enda lahenduses
ei jõudnud mõelda kõigi erinevate andmestruktuuride peale, mis oleks saanud tööd kiirendada.
- Lendude filtreerimine pole praegu max kasutajasõbralik
- Frontend failid saaks lüüa palju väiksemateks komponentideks, et suurendada loetavust

