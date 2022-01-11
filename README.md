# BeautySalonServer
Server side of beauty salon application

Paths:

### =====================Мастера============================

**[GET]** `/masters` - выводит всех мастеров в формате:

        "id": 1,
        "name": "Никифоров Денис",
        "tel": "80(29)840-32-32",
        "percent": 0.20,
        "createdAt": "2022-01-08 11:10",
        "updatedAt": "2022-01-08 11:10"
        
**[GET]** `/masters/sorted[?type=percent]` - возвращает всех мастеров, отсортированных по проценту в порядке убывания

**[GET]** `/masters/sorted/?type=date` - возвращает всех мастеров, отсортированных до дате регистрации в порядке убывания (от самого нового до самого старого)
 
        
**[POST]** `/masters` - добавляет нового мастера. В теле запроса надо указать данные по такому шаблону:

        "name": "Никифоров Денис", // не должно быть пустое
        "tel": "80(29)840-32-32", // должно соответствовать шаблону +375(##)###-##-## или 80(##)###-##-## или (+375|80) ## ###-##-##
        "percent": 0.20 // должно быть не меньше 0.00 и не больше 1.00
        
Если валидация прошла неуспешно, то возвращается сообщение об ошибки с кодом ***406 [Not Acceptable]***
     
**[PUT]** `/masters` - изменяет уже существующего мастера. В теле запроса надо указать измененный объект мастера. Напимер:


    "id": 1,
    "name": "Новое имя",
    "tel": "80(29)840-32-32",
    "percent": 0.20
    
**[PUT]** `/masters/{id}` - аналогичен предыдущему запросу, только `id` указывается в пути

**[DELETE]** `/masters` - удаляет мастера. Для удаления в теле запроса достаточно указать `id` мастера. Например:


    "id": 1

**[DELETE]** `/masters/{id}` - удаляет мастера c заданным `id`

**[GET]** `/masters/{id}` - достает мастера с заданным `id`.

### =====================Клиенты============================

**[GET]** `/clients` - выводит всех клиентов в формате:

        "id": 1,
        "name": "Вероника",
        "tel": "+375(29)840-32-32"

        
**[POST]** `/clients` - добавляет нового клиента. В теле запроса надо указать данные по такому шаблону:

        "name": "Вероника", // не должно быть пустое
        "tel": "+375(29)840-32-32" // должно соответствовать шаблону +375(##)###-##-## или 80(##)###-##-## или (+375|80) ## ###-##-##
        
Если валидация прошла неуспешно, то возвращается сообщение об ошибки с кодом ***406 [Not Acceptable]***     

**[PUT]** `/clients` - изменяет уже существующего клиента. В теле запроса надо указать id клиента и тот параметр, который надо изменить. Напимер:


    "id": 3,
    "name": "Новое имя",
    "tel": "+375(29)840-32-32"
    
**[PUT]** `/clients/{id}` - аналогичен предыдущему запросу, только `id` указывается в пути.


**[DELETE]** `/clients` - удаляет клиента. Для удаления в теле запроса достаточно указать id клиента. Например:


    "id": 3

**[DELETE]** `/clients/{id}` - удаляет клиента c заданным `id`

**[GET]** `/clients/{id}` - достает клиента с заданным `id`.


### =====================Заказы============================

**[GET]** `/orders` - выводит все заказы в формате:
```
{
    "id": 7,
    "price": 80.0,
    "area": "Зона бикини",
    "start": "2022-01-15 15:00",
    "finish": "2022-01-15 15:20",
    "addons": [],
    "master": {
        "id": 17,
        "name": "Диана Булавина",
        "tel": "+375(44)781-92-33",
        "percent": 0.11,
        "createdAt": "2022-01-11 12:19",
        "updatedAt": "2022-01-11 19:57"
    },
    "client": {
        "id": 12,
        "name": "Елена Метельская",
        "tel": "+375(44)226-87-99"
    }
}
```
**[GET]** `/orders/sorted` - возвращает все заказы отсортированные по шаблону:

        "title": "15 января",
        "data": [
            {
                "id": 9,
                "price": 110.0,
                "area": "Левая рука",
                "start": "2022-01-15 12:00",
                "finish": "2022-01-15 12:30",
                "addons": [],
                "master": {
                    "id": 18,
                    "name": "Ирина Ларина",
                    "tel": "+375(44)778-22-11",
                    "percent": 0.05,
                    "createdAt": "2022-01-11 12:20",
                    "updatedAt": "2022-01-11 12:20"
                },
                "client": {
                    "id": 13,
                    "name": "Андрей Лохмаков",
                    "tel": "+375(25)694-16-56"
                }
            }]

**[POST]** `/orders` - добавляет новый заказ. В теле запроса надо указать данные по такому шаблону:

        "price": 200.00, // не должно быть меньше нуля
        "area": "Место", // не должно быть пустое
        "start": "2021-12-25T14:00", // должно содержать будущую или текущую дату
        "finish": "2021-12-25T14:40", // должно содержать будущую или текущую дату
        "masterId": 1,
        "clientId": 3
        
Если валидация прошла неуспешно, то возвращается сообщение об ошибки с кодом ***406 [Not Acceptable]***
     
**[PUT]** `/orders` - изменяет уже существующий заказ. В теле запроса надо указать измененный объект. Напимер:

        "id": 1,
        "price": 200.00, // не должно быть меньше нуля
        "area": "Место", // не должно быть пустое
        "start": "2021-12-25T14:00", // должно содержать будущую или текущую дату
        "finish": "2021-12-25T14:40", // должно содержать будущую или текущую дату
        "masterId": 1,
        "clientId": 3
        
**[PUT]** `/orders/{id}` - аналогичен предыдущему запросу, только `id` указывается в пути.


**[DELETE]** `/orders` - удаляет заказ. Для удаления в теле запроса достаточно указать id заказа. Например:


    "id": 2

**[DELETE]** `/clients/{id}` - удаляет клиента c заданным `id`


**[GET]** `/orders/{id}` - достает заказ с заданным id.



### =====================Статистика============================


**[GET]** `/statistics/masters` - возвращает статистику всех мастеров, отсортированных по имени (в алфавитном порядке):
     
     {
        "id": 3,
        "name": "Бенгальский Федор",
        "tel": "80(29)379-28-01",
        "percent": 0.30,
        "todayNetIncome": 0.00,
        "monthNetIncome": 60.00
    },
    {
        "id": 4,
        "name": "Елена Метельская",
        "tel": "375(44)998-99-99",
        "percent": 0.08,
        "todayNetIncome": 0.00,
        "monthNetIncome": 0.00
    },
    {
        "id": 2,
        "name": "Лохмаков Максим",
        "tel": "80(29)345-99-18",
        "percent": 0.25,
        "todayNetIncome": 0.00,
        "monthNetIncome": 20.00
    }
    
**[GET]** `/statistics/masters/{id}` - возвращает статистику мастера с id=`{id}`:

    "id": 3,
    "name": "Бенгальский Федор",
    "tel": "80(29)379-28-01",
    "percent": 0.30,
    "todayNetIncome": 0.00,
    "monthNetIncome": 60.00
    
**[GET]** `/statistics/day` - возвращает общую сумму заработка салона за день и статистику мастеров, отсортированных по заработку за день (в порядке убывания):

       "income": 0.0,
       "masters": [
        {
            "id": 1,
            "name": "Никифоров Денис",
            "tel": "80(29)840-32-32",
            "percent": 0.02,
            "todayNetIncome": 0.0,
            "monthNetIncome": 4.8
        },
        {
            "id": 2,
            "name": "Лохмаков Максим",
            "tel": "80(29)345-99-18",
            "percent": 0.25,
            "todayNetIncome": 0.0,
            "monthNetIncome": 20.0
        }]
       
**[GET]** `/statistics/month` - возвращает общую сумму заработка салона за месяц и статистику мастеров, отсортированных по заработку за месяц (в порядке убывания):       

       "income": 520.0,
       "masters": [
        {
            "id": 3,
            "name": "Бенгальский Федор",
            "tel": "80(29)379-28-01",
            "percent": 0.30,
            "todayNetIncome": 0.0,
            "monthNetIncome": 60.0
        },
        {
            "id": 2,
            "name": "Лохмаков Максим",
            "tel": "80(29)345-99-18",
            "percent": 0.25,
            "todayNetIncome": 0.0,
            "monthNetIncome": 20.0
        },
        {
            "id": 1,
            "name": "Никифоров Денис",
            "tel": "80(29)840-32-32",
            "percent": 0.02,
            "todayNetIncome": 0.0,
            "monthNetIncome": 4.8
        }]
