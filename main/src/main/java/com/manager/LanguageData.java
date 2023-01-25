//**********************************************************/
//Projet javaFX
//30-09-2022
//Cedric raymond  , 0867477
//Guillaume sauve , 1440441
//Mykhailo Niemtsev,2242977
//**********************************************************/

package com.manager;

public class LanguageData
{
    public enum Languages
    {
        FRENCH,
        ENGLISH,
        RUSSIAN,
        UKRAINIAN
    }

    public static Languages lang = Languages.FRENCH;

    public static void setLang(int l)
    {
        switch(l)
        {
            case 0:
            lang = Languages.FRENCH;
            break;
            case 1:
            lang = Languages.ENGLISH;
            break;
            case 2:
            lang = Languages.RUSSIAN;
            break;
            case 3:
            lang = Languages.UKRAINIAN;
            break;
        }
    }

    public static int getLangId()
    {
        int l = 0;
        switch(lang)
        {
            case FRENCH:
                l= 0;
            break;
            case ENGLISH:
                l= 1;
            break;
            case RUSSIAN:
                l= 2;
            break;
            case UKRAINIAN:
                l= 3;
            break;
        }

        return l;
    }

    private static String[][] frenchData = {
        // 0                                      1                  2                      3                4
        {"Connexion au compte Rosemont"}, {"Nom d'utilisateur"}, {"Mot de passe"}, {"Se connecter"}, {"Paramètre de langue"}, 
        //5                                        6
        {"Connexion à la base de donnée"},{"connecté à la base de donnée"}, 
        //7
        {"Erreur, l'application n'a pas pu se connecter à la base de donnée, veuillez réessayer plus tard ou contacter le support technique"},
        //8                                                                                  9
        {"Gestionnaire des employé du college Rosemont Copyright (2002-2022)"}, {"Nom d'utilisateur ou mot de passe invalide"},
        //10 langue comboBox
        //0            1           2          3
        {"Francais", "Anglais", "Russe","Ukrainien"},
        //11
        //Employe labels & column header
        //0      1        2       3         4             5
        {"ID", "Nom", "Prenom", "Sexe", "Emplois", "Années d'expérience"},
        //12
        //Gender combo box
        //0           1            2
        {"Masculin", "Feminin", "Autre"},
        //13
        //Boutons
        //  0           1          2             3              4                      5
        {"Ajouter", "Modifier", "Effacer", "Suprimer", "Effacer la sélection", "Déconnexion"},
        //14
        {"Système de gestion des employés"},
        //15 error login error box
        //  0                                                   1                                            2
        {"échec de connexion", "La connexion avec la base de donnée n'a pas pu être établi","Si le probleme persiste, contacter votre administrateur réseaux"},
        //16 delete error box
        //0                                        1                             2                                    3
        {"Échec de la suppression", "l'employé n'a pas été trouvé", "Aucun employé identifié par le ID " , " n'a pu être trouvé"},
        //17 invalid employe ID
        //0                           1                                   2
        {"numero Employé", "Le numéro d'employé est invalide","Merci d'entrer uniquement des nombres entiers positif"},
        //18 invalid experience number
        //0                           1                                    2
        {"Expérience", "L'expérience de l'employé est invalide","Merci d'entrer uniquement des nombres entiers"},
        //19 invalid gender data
        //0                     1                                           2
        {"Sexe", "Le sexe entré n'est pas une valeur reconnue","Vérifié votre choix dans la sélection du sexe"},
        //20 nom vide
        //     0                              1
        {"Le nom de l'employé est vide","Merci d'entrer le nom de l'employé"},
        //21 prenom vide
        //   0                                        1
        {"Le prenom de l'employé est vide","Merci d'entrer le prenom de l'employé"},
        //22 emploi vide
        //   0                                       1
        {"L'emploi de l'employé est vide","Merci d'entrer l'emploi de l'employé"},
        //23 empty employe
        //    0            1                                2
        {"Employé", "L'Employé est invalide","Merci de vérifier les champs de texte de l'employé"},
        //24 modification fail
        // 0                                     1                             2                                    3
        {"Échec de la modification", "l'employé n'a pas été trouvé", "Aucun employé identifié par le ID " , " n'a pu être trouvé"},
        //25 ajout modification fail
        //0                        1                                                             2
        {"échec d'ajout", "l'employé na pas pu etre ajouté a la base de donné","Le format d'une des données est invalide ou le ID est déja utilisé par un autre employé"}
    };
    private static String[][] englishData = {
        // 0                                      1                  2                      3                4
        {"Connect to you Rosemont account"}, {"Username"}, {"Password"}, {"Login"}, {"Language setting"}, 
        //5                                        6
        {"Connecting to the database"},{"connected to the database"}, 
        //7
        {"Error, The application could not connect to the database, please try again later or contact the technical support"},
        //8                                                                                  9
        {"Rosemont employee manager Copyright (2002-2022)"}, {"Username or password invalid"},
        //10 langue comboBox
        //0            1           2          3
        {"French", "English", "Russian","Ukrainian"},
        //11
        //Employe labels & column header
        //0      1        2       3         4             5
        {"ID", "Last Name", "Name", "Gender", "Job", "years of experience"},
        //12
        //Gender combo box
        //0           1            2
        {"Male", "Female", "Other"},
        //13
        //Boutons
        //  0           1      2       3          4          5
        {"Add", "Modify", "Erase", "Delete", "Unselect", "Logout"},
        //14
        {"Employee manager system"},
        //15 error login error box
        //  0                                                   1                                            2
        {"Connection fail", "The connection with the database could not be established","If the problem persist, please contact the tech support"},
        //16 delete error box
        //0                                        1                             2                                    3
        {"Deleting error", "The employee could not be found", "No employee with the ID " , " could be found"},
        //17 invalid employe ID
        //0                           1                                   2
        {"Employee ID", "The employee ID is invalid","Please only enter positive integer number"},
        //18 invalid experience number
        //0                           1                                    2
        {"Experience", "The employee experience is invalid","Please only enter positive integer number"},
        //19 invalid gender data
        //0                     1                                           2
        {"Gender", "The gender value is invalid","Please check your choice in the comboBox"},
        //20 nom vide
        //     0                              1
        {"Employee last name is empty","Please enter the employee last name"},
        //21 prenom vide
        //   0                                        1
        {"Employee name is empty","Please enter the employee name"},
        //22 emploi vide
        //   0                                       1
        {"Employee job's is empty","Please enter the employee job"},
        //23 empty employe
        //    0            1                                2
        {"Employee", "The employee is invalid","Please verify the employee text fields"},
        //24 modification fail
        // 0                                     1                             2                                    3
        {"Modification error", "The employee could not be found", "No employee identified by the ID " , " could be found"},
        //25 ajout modification fail
        //0                        1                                                             2
        {"Failed to add", "The employee could not be added to the database","The formatting of the data is invalid or the ID is already used by another employee"}
    };

    private static String[][] russianData = {
        // 0 1 2 3 4
        { "Подключиться к вашему аккаунту Rosemont" }, { "Имя пользователя" }, { "Пароль" }, { "Вход" },
        { "Языковые настройки" },
        // 5 6
        { "Подключение к базе данных" }, { "Подключен к базе данных" },
        // 7
        { "Ошибка,не удалось подключиться к базе данных, повторите попытку позже или обратитесь в техподдержку" },
        // 8 9
        { "Руководитель сотрудников Колледжа Роузмонт авторского права (2002–2022 гг.)" }, { "Неверное имя пользователя или пароль" },
        // 10 langue comboBox
        // 0 1 2 3
        { "Французский", "Английский", "Русский", "Украинский" },
        // 11
        // Employe labels & column header
        // 0 1 2 3 4 5
        { "ID", "Фамилия", "Имя", "Пол", "Работа", "Годы опыта" },
        // 12
        // Gender combo box
        // 0 1 2
        { "Мужской", "Женский", "Другой" },
        // 13
        // Boutons
        // 0 1 2 3 4 5
        { "Добавить", "Редактировать", "Стереть", "Удалить", "Отменить выбор", "Выйти" },
        // 14
        { "Система управления персоналом" },
        // 15 error login error box
        // 0 1 2
        { "Ошибка подключения", "Не удалось установить соединение с базой данных",
                "Если проблема не устранена, обратитесь к сетевому администратору." },
        // 16 delete error box
        // 0 1 2 3
        { "Удалить не удалось", "работник не найден", "Ни один сотрудник не идентифицирован по идентификатору ",
                " невозможно найти" },
        // 17 invalid employe ID
        // 0 1 2
        { "идентификационный номер сотрудника", "Номер сотрудника недействителен",
                "Пожалуйста, вводите только положительные целые числа" },
        // 18 invalid experience number
        // 0 1 2
        { "Опыт", "Стаж работника недействителен", "Пожалуйста, вводите только целые числа" },
        // 19 invalid gender data
        // 0 1 2
        { "Пол", "Введенный пол не является распознаваемым значением",
                "Проверил ваш выбор в выборе пола" },
        // 20 nom vide
        // 0 1
        { "Фамилия сотрудника пусто", "Пожалуйста, введите имя сотрудника" },
        // 21 prenom vide
        // 0 1
        { "Имя сотрудника пусто", "Пожалуйста, введите Фамилию сотрудника" },
        // 22 emploi vide
        // 0 1
        { "Вакансия сотрудника пуста", "Пожалуйста, введите должность сотрудника" },
        // 23 empty employe
        // 0 1 2
        { "Работник", "Сотрудник не существует", "Пожалуйста, проверьте текстовые поля сотрудника" },
        // 24 modification fail
        // 0 1 2 3
        { "Не удалось изменить", "работник не найден", "Ни один сотрудник не идентифицирован по идентификатору ",
                "невозможно найти" },
        //25 ajout modification fail
        //0                        1                                                             2
        {"добавить не удалось", "сотрудник не может быть добавлен в базу данных","Неверный формат одного из данных или идентификатор уже используется другим сотрудником"}
};
private static String[][] ukrainianData = {
        // 0 1 2 3 4
        { "Підключення до облікового запису Rosemont" }, { "ім'я користувача" }, { "Пароль" },
        { "Щоб увійти" },
        { "Налаштування мови" },
        // 5 6
        { "Підключення до бази даних" }, { "підключений до бази даних" },
        // 7
        { "Помилка, програмі не вдалося підключитися до бази даних, спробуйте пізніше або зверніться до технічної підтримки" },
        // 8 9
        { "Менеджер співробітників Rosemont College Авторські права (2002-2022)" },
        { "Неправильне ім'я користувача або пароль" },
        // 10 langue comboBox
        // 0 1 2 3
        { "французька", "англійська", "російський", "українська" },
        // 11
        // Employe labels & column header
        // 0 1 2 3 4 5
        { "ID", "Прізвище", "Ім'я", "секс", "Вакансії", "Роки досвіду" },
        // 12
        // Gender combo box
        // 0 1 2
        { "Чоловік", "Жіночий", "Інший" },
        // 13
        // Boutons
        // 0 1 2 3 4 5
        { "Додати", "Редагувати", "Стерти", "Видалити", "Очистити вибір", "Вийти" },
        // 14
        { "Система управління персоналом" },
        // 15 error login error box
        // 0 1 2
        { "Помилка підключення", "Не вдалося встановити з’єднання з базою даних",
                "Якщо проблема не зникає, зверніться до адміністратора мережі" },
        // 16 delete error box
        // 0 1 2 3
        { "Не вдалося видалити", "працівника не знайшли",
                "Немає співробітників, ідентифікованих за ідентифікатором ",
                " не вдалося знайти" },
        // 17 invalid employe ID
        // 0 1 2
        { "номер працівника", "Номер працівника недійсний",
                "Будь ласка, вводьте лише додатні цілі числа" },
        // 18 invalid experience number
        // 0 1 2
        { "Досвід", "Стаж працівника недійсний", "Вводьте лише цілі числа" },
        // 19 invalid gender data
        // 0 1 2
        { "секс", "Введена стать не є розпізнаним значенням", "Перевірив свій вибір у виборі статі" },
        // 20 nom vide
        // 0 1
        { "Прізвище співробітника пусте", "Будь ласка, введіть ім'я працівника" },
        // 21 prenom vide
        // 0 1
        { "Ім'я працівника порожнє", "Merci d'entrer le prenom de l'employé" },
        // 22 emploi vide
        // 0 1
        { "Робота працівника порожня", "Будь ласка, введіть посаду працівника" },
        // 23 empty employe
        // 0 1 2
        { "Співробітник", "Працівник інвалід", "Будь ласка, перевірте текстові поля працівника" },
        // 24 modification fail
        // 0 1 2 3
        { "Помилка редагування", "працівника не знайшли",
                "Немає співробітників, ідентифікованих за ідентифікатором",
                "не вдалося знайти" },
        //25 ajout modification fail
        //0                        1                                                             2
        {"не вдалося додати", "працівника не вдалося додати до бази даних","Формат одного з даних недійсний або ID вже використовується іншим співробітником"}
};

    public static String getText(int textId, int textPart)
    {
        String text = "";
        switch(lang)
        {
            case FRENCH:
            text = frenchData[textId][textPart];
            break;
            case ENGLISH:
            text = englishData[textId][textPart];
            break;
            case RUSSIAN:
            text = russianData[textId][textPart];
            break;
            case UKRAINIAN:
            text = ukrainianData[textId][textPart];
            break;
        }
        return text;
    }


    public static String[] getTextArray(int textId)
    {
        String[] text = new String[0];
        switch(lang)
        {
            case FRENCH:
            text = frenchData[textId];
            break;
            case ENGLISH:
            text = englishData[textId];
            break;
            case RUSSIAN:
            text = russianData[textId];
            break;
            case UKRAINIAN:
            text = ukrainianData[textId];
            break;
        }
        return text;
    }
}