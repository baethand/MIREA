{
    subject = "Конфигурационное управление";
    groups = for(1 25 1 "ИКБО-&-20"); 
    student1 = 
    (
        age(19)
        group("ИКБО-13-22")
        name("Руденко А.Д.")
    );
    student2 =
    (
        age(18)
        group("ИКБО-22-22")
        name("Uwu D.D.")
    );
    student3 =
    (
        age(18)
        group("ИКБО-33-22")
        name("Ионовdfghjkiuyt П.Б.")
    );
}

(
    groups(
            &groups
          )
    students(
            &student1 
            &student2 
            &student3
        (
            age(19) group("ИКБО-13-22") name("Нормальни А.А.")
        )
    ) 
subject(&subject)
)